name := """google-support-articles"""
organization := "co.uk.redpixel"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  guice,
  "net.ruippeixotog"       %% "scala-scraper"      % "2.2.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0"  % Test,
  "com.github.tomakehurst" %  "wiremock-jre8"      % "2.27.2" % Test
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-explaintypes",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:existentials",
  "-feature",
//  "-Xfatal-warnings",
//  "-Ywarn-dead-code",
//  "-Ywarn-extra-implicit",
//  "-Ywarn-unused:implicits",
//  "-Ywarn-unused:imports",
//  "-Ywarn-unused:locals",
//  "-Ywarn-unused:params",
//  "-Ywarn-unused:privates",
//  "-Ywarn-value-discard"
)
