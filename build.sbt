enablePlugins(ScalaJSPlugin)

name := "Knoldus-Scala.js"

version := "0.1"

scalaVersion := "2.11.7"

resolvers ++= Seq(
    Resolver.bintrayRepo("websudos", "oss-releases"),
    "Sonatype releases"                at "https://oss.sonatype.org/content/repositories/releases")


libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.0"
libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.0"

skip in packageJSDependencies := false
jsDependencies +=
  "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"

jsDependencies += RuntimeDOM

// uTest settings
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % "test"
testFrameworks += new TestFramework("utest.runner.Framework")

persistLauncher in Compile := true
persistLauncher in Test := false