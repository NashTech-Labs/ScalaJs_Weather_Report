// Turn this project into a Scala.js project by importing these settings
scalaJSSettings

name := "Knoldus-Scala.js"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.2"

ScalaJSKeys.persistLauncher := true

ScalaJSKeys.persistLauncher in Test := false

libraryDependencies ++= Seq(
    "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
    "org.scala-lang.modules.scalajs" %% "scalajs-jasmine-test-framework" % scalaJSVersion % "test",
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1"    
)
