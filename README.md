Knoldus ScalaJs_Weather_Report
======================

Weather Information System- Get the mood of your city on one click  This is simple project using Scala.js.

![weather](src/main/resources/images/weather.png)


*************************************************************************************************************
###Getting Started with Code  : 

## IDE integration

`$ sbt eclipse`

## Run the application

To run this application, we need to generate JavaScript file out of our compiled code. There are two ways to generate JavaScript: ` fastOptJS` or ` fullOptJS`
```shell
$ sbt  ~fastOptJS
```
    Go to browser and run http://localhost:12345/target/scala-2.11/classes/weather-2.11-fastopt.html

