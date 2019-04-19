# Brustol_CityBike

The main goal of this project is to propose a clustoring of Brustol city Bike  according to the location bike stations.
In production, this code should be launched daily on 10 GB of data. 

## Requirements

* [Java 8](https://www.java.com/fr/download/faq/java8.xml)
* [Scala 2.11.8](https://www.scala-lang.org/download/2.11.7.html)
* [SBT 1.2.8](https://piccolo.link/sbt-1.2.8.zip)
* [Spark 2.4.0](https://spark.apache.org/releases/spark-release-2-1-0.html)

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

   DATA_INPUT_PATH=
   OUTPUT_PATH=
   NB_CLUSTER=
   
* **DATA_INPUT_PATH** : Path of the data to be clustered.
* **OUTPUT_PATH** : Path where to save the clustered data.
* **NB_CLUSTER** : Number of clusters.

## Running project on YARN

To build the project, run : 

    sbt assembly
    
This will produce a jar containing the compiled project

Then you can submit the job using **spark-submit** in the **shell file**:
   
    cd Brustol_CityBike/lib/shell
    chmod +x spark-submit.sh
    ./spark-submit.sh

##Project architecture

  Brustol_CityBike/Output                                                                   
  Brustol_CityBike/Config                                                                       
  Brustol_CityBike/lib   

## Results

In this project, we've chosen to cluster our data in 3 clusters. The plot provided will show how the work was done ! 

This is how the result would look like.

***Data with Clusters***

![Data Clustered](https://github.com/mskanji/Brustol_CityBike/blob/master/Clustered%20Data.png)

***Data Plot***

This plot is developped with an R code ***DataViz.R*** wich exists in ***DataClustering/scripts/R_Script***

![Data Plot](https://github.com/mskanji/Brustol_CityBike/blob/master/Map.png)

