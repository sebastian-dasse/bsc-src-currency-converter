enablePlugins(ScalaJSPlugin)            // turn this project into a Scala.js project by importing these settings

workbenchSettings

name := "currency-converter"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint"
)

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.8.0",
  "com.lihaoyi" %%% "scalatags" % "0.5.2",
  "org.scala-lang.modules" %% "scala-async" % "0.9.5"
)

//skip in packageJSDependencies := false    // collect all JavaScript dependencies in one file

scalaJSStage in Global := FastOptStage    // to use Node.js or PhantomJS for tests

jsDependencies in Test += RuntimeDOM      // to use PhantomJS for tests

bootSnippet := "showcase.App().main();"    // for the workbench

updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)
//refreshBrowsers <<= refreshBrowsers.triggeredBy(fastOptJS in Compile)

EclipseKeys.withSource := true
