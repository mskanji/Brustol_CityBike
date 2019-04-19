# Brustol_CityBike

The main goal of this project is to propose a clustoring of Brustol city Bike  according to the location bike stations.
In production, this code should be launched daily on 10 GB of data, the choice of the platform being up to you to decide that it can be run on a Spark cluster or a cluster Kubernetes or Azure.

## Requirements

* [Java 8](https://www.java.com/fr/download/faq/java8.xml)
* [Scala 2.11.7](https://www.scala-lang.org/download/2.11.7.html)
* [SBT 1.2.8](https://piccolo.link/sbt-1.2.8.zip)
* [Spark 2.1.0](https://spark.apache.org/releases/spark-release-2-1-0.html)

## Steps

* Initializing Spark Session
* Importing Data
* Building the Pipeline
  * Assembling Vectors
  * Training Model
* Applying model to data
* Saving Clustered Data

## Configuration

The program needs somme properties to be executed. These properties are saved in ***config/application.properties***.

    inputData=
    inputClusterNumber=
    master=
    appName=
    nameOfColumnCluster=
    outputData=
    outputFileName=
    outputFileFormat=

* **inputData** : Path of the data to be clustered.
* **inputClusterNumber** : Cluster number to use in cluster.
* **master** : The master URL for the cluster.
* **appName** : Application name.
* **nameOfColumnCluster** : Name of the new column wich contains the clusters.
* **outputData** : Path where to save the clustered data.
* **outputFileName** : Name of the clustered data.
* **outputFileFormat** : Format of the clustered data to be saved
* **outPutData** is the path where clustered Data will be saved.
* **fileOutPutName** is the name chosen for outputData.

## Running project on YARN

To build the project, run : 

    sbt assembly
    
This will produce a jar containing the compiled project

Then you can submit the job using **spark-submit** in the **shell file**:
   
    cd DataClustering/scripts/shell
    chmod +x spark-submit.sh
    ./spark-submit.sh

##Project architecture

  DataClustering/DataInput                                                                    
  DataClustering/DataOutput                                                                   
  DataClustering/config                                                                       
  DataClustering/scripts   

## Results

In this project, we've chosen to cluster our data in 3 clusters. The plot provided will show how the work was done ! 

This is how the result would look like.

***Data with Clusters***

![Data Clustered](https://github.com/nackachy/DataClustering/blob/master/dataWithClusters.PNG)

***Data Plot***

This plot is developped with an R code ***DataViz.R*** wich exists in ***DataClustering/scripts/R_Script***

![Data Plot](https://github.com/nackachy/DataClustering/blob/master/Map.png)

