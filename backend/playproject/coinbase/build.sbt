name := """coinbase"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.7"

crossScalaVersions := Seq("2.11.12", "2.12.7")

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

libraryDependencies ++= Seq(
  jdbc,
  "mysql" % "mysql-connector-java" % "8.0.11",
  "org.playframework.anorm" %% "anorm" % "2.6.2"
)

import play.sbt.PlayImport.PlayKeys._

routesImport += "libs.PathBinders._"
routesImport += "java.util.Date"