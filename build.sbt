name := "tdd_fluent-calculator"
version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.2",
  "org.scalatest" %% "scalatest" % "3.2.2" % Test,
  "org.scalamock" %% "scalamock" % "4.4.0" % Test
)
