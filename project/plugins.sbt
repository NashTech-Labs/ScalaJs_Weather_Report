// Comment to get more information during initialization
logLevel := Level.Warn

// Resolvers
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.0")

// Scala-js  plugins
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.7")

// plugin for gzip compressing web assets.
addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.0")

// Play-scala.js Integration plugin
addSbtPlugin("com.vmunier" % "sbt-play-scalajs" % "0.3.0")

// Plugins for improving code quality

// Plugins to check scala style warnings
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.8.0")

// Plugin to format scala code while compilation
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")

// Plugin for source code statistics and analytics
addSbtPlugin("com.orrsella" % "sbt-stats" % "1.0.5")

// Plugin for copy paste detector
addSbtPlugin("de.johoop" % "cpd4sbt" % "1.1.5")

resolvers += Resolver.sonatypeRepo("releases")

addSbtPlugin("org.brianmckenna" % "sbt-wartremover" % "0.14")

addSbtPlugin("com.orrsella" % "sbt-stats" % "1.0.5")
