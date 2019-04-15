#!/bin/sh

spark-submit --class run  --master yarn-cluster --files /home/moez/Brustol_CityBike/Config/application.properties /home/moez/Brustol_CityBike/lib/jars/citybikers_2.11-0.1.jar
