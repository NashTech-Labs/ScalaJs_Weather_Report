// Turn this project into a Scala.js project by importing these settings
scalaJSSettings

name := "Knoldus-Scala.js"

version := "0.1"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
    "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6")
