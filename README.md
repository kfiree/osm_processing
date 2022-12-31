
## RideShare
![Build Status](https://github.com/openstreetmap/osmosis/actions/workflows/continuous-integration.yml/badge.svg)

Java application for processing Open Street Map data.
The application convert OSM files to road-map `Graph` class while keep the data structure simplify and fully connected.

It get achieved by removing data that irrelevant to the road map and while keeping the graph fully connected and ready for navigation algorithms.



## Author [Kfir Ettinger.](https://github.com/kfiree)

# Tech
- [Osmosis] - Java application for processing OSM data.


## Run Locally

Clone the project

```bash
  git clone https://github.com/kfiree/osm_processing.git 
```

Go to the project directory

```bash
  cd osm_processing
```

Install dependencies

```bash
  mvn clean # always good to clean before install
  mvn install
```


**Enjoy!**


[Osmosis]: <https://wiki.openstreetmap.org/wiki/Osmosis>
