import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.{SaveMode, SparkSession}
import java.util.logging.{Level, Logger}

object run {

  def main(args: Array[String]): Unit = {

    val logger = Logger.getLogger(getClass.getName)

    val p = new Prop()

    val nbrCluster =   p.getProp("NB_CLUSTER").toInt // number of cluster.

    val spark = SparkSession
      .builder()
      .getOrCreate() // initialising SparkSession dataFrame API to local
    logger.log(Level.INFO,"Spark Session Create",spark.sparkContext.appName)

    val dataBikes = spark.read.json ( p.getProp("DATA_INPUT_PATH")   ) // importing Json File

    val dataBikesLocation = dataBikes.select("longitude" , "latitude")
    dataBikesLocation.persist()

    val assembler = new VectorAssembler().setInputCols(  Array("longitude" , "latitude")).setOutputCol("features") // assembler les variables dans  feature = [ longitude , lattitude dans un tableau ]
    val intermediaireDF = assembler.transform(dataBikesLocation)
    val kmeans = new KMeans().setK(nbrCluster).setSeed(1L)
    val model = kmeans.fit(intermediaireDF)
    val dff = model.transform(intermediaireDF)
    dff.show()


    dff.select("longitude" , "latitude" , "prediction" ) //avg by class
      .groupBy("prediction").avg()
      .show() // mean of each  group


    dff.groupBy("prediction").count().show() // groub by count group


    dff.createOrReplaceTempView("dfTemperView")

    spark.sql("SELECT  stddev(longitude) , stddev(latitude) , prediction FROM dfTemperView  GROUP BY prediction " ).show()

    println(p.getProp("OUTPUT_PATH"))

    dff.drop("features")
      .repartition(1)
      .write
      .mode(SaveMode.Overwrite)
      .format("csv")
      .save(p.getProp("OUTPUT_PATH"))

    spark.close()

  }

}
