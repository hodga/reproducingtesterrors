name := "reproducingtesterrors"

version := "0.1"

scalaVersion := "2.12.3"


lazy val reproducingtesterrors = project.in(file("."))
  .configs(IntegrationTest extend Test)
  .settings(Defaults.itSettings: _*)


val specs2Version = "4.0.0"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % specs2Version % "it,test",
  "org.specs2" %% "specs2-matcher-extra" % specs2Version % "it,test",
  "org.specs2" %% "specs2-mock" % specs2Version % "it,test")

parallelExecution in Test := false