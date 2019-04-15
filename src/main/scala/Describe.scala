import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.sql
import org.apache.spark.sql.types.{DoubleType, FloatType, IntegerType}
import org.apache.spark.sql.functions._
import org.apache.spark.ml.feature.VectorAssembler


class Describe (df : sql.DataFrame) {

  def listeOfName(): Array[String] = { // list of name for  quatitative variable from an given dataFrame.
    val colu = df.schema.fields.filter(x => x.dataType == IntegerType || x.dataType == DoubleType || x.dataType == FloatType)
    val listeOfNames = colu.map(x => x.name)
    listeOfNames
  }

  def filterNumeric(): sql.DataFrame = { // filter df with only quantitative Value : extract numeric variable
    df.select(this.listeOfName().head, this.listeOfName().tail: _*)
  }

  def summarise(): Unit  = { // mean , std , missingValue of a given Data Frame

    this.filterNumeric().select(this.filterNumeric().columns.map(mean(_)): _*).show()
    this.filterNumeric().select(this.filterNumeric().columns.map(stddev(_)): _*).show()
    this.filterNumeric().select(this.filterNumeric().columns.map(c => sum(col(c).isNull.cast("int")).alias(c)): _*).show()
  }

  def kmeansClustering( k : Int  ): sql.DataFrame = { // performing Kmeans On cleaned Data Frame.

    val assembler = new VectorAssembler().setInputCols(this.listeOfName()).setOutputCol("features") // assembler les variables dans  feature = [ longitude , lattitude dans un tableau ]
    val intermediaireDF = assembler.transform(this.filterNumeric())
    val kmeans = new KMeans().setK(k).setSeed(1L)
    val model = kmeans.fit(intermediaireDF)
    val predictions = model.transform(intermediaireDF)
    predictions
  }


}

