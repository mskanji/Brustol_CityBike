@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@    Projet : Bustrol city Bike  Data Visualisation                                                @
@     Auteur : Moez Skanji                                                                         @
@     Version 5.3.2                                                                                @
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


library(readr)
library(leaflet)
library(leaflet)
library(leaflet.extras)
library(htmlwidgets)
library(htmltools)
library(webshot)



 clusters<- read_csv("dataclusters", col_names = FALSE)


colnames(clusters)<- c("longitude" , "latitude" , "groupe")



#function to choose cluster color
getColor <- function(clusters) {
  sapply(clusters$groupe, function(cluster) {
    if(cluster == 1) {
      "green"
    } else if(cluster == 2) {
      "orange"
    } else {
      "red"
    } })
}

#icon colors
icons <- awesomeIcons(
  icon = 'ios-close',
  iconColor = 'black',
  library = 'ion',
  markerColor = getColor(clusters)
)

#Map plot

map_leaflet = leaflet(clusters) %>% addTiles() %>%
  addAwesomeMarkers(~longitude, ~latitude, icon=icons, label=~as.character(groupe))%>%
  addLegend("bottomright",
            colors =c("Green",  "Orange", "Red"),
            labels= c("cluster1", "cluster2","cluster3"),
            title= "Cluster colors",
            opacity = 1)%>%
  addControl("Clusterd Data based on Location", position = "topleft")

#saving Map plot in html
saveWidget(map_leaflet, file="Map.html")

#saving Map in pnj
webshot("Map.html", file = "Map.png", cliprect = "viewport")


