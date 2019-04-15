name := "CityBikers"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.1.0",
  "org.vegas-viz" %% "vegas" % "0.3.9",
  "org.vegas-viz" %% "vegas-spark" % "0.3.9",
  "org.apache.spark" %% "spark-mllib" % "2.1.0"
)

// for accessing files from S3 or HDFS
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.7.0" exclude("com.google.guava", "guava")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}