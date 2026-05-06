name := "Dawkins Weasel"

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.17"

lazy val root = (project in file("."))
  .settings(
    name := "bi-weekly-hackery"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.20" % Test