lazy val commonSettings = Seq(
  organization := "com.github.cornerman",
  version      := "0.1.0-SNAPSHOT",

  scalaVersion := "2.12.4",
  crossScalaVersions := Seq("2.11.12", "2.12.4"),

  scalacOptions ++=
    "-encoding" :: "UTF-8" ::
    "-unchecked" ::
    "-deprecation" ::
    "-explaintypes" ::
    "-feature" ::
    "-language:_" ::
    "-Xfuture" ::
    "-Xlint" ::
    "-Ypartial-unification" ::
    "-Yno-adapted-args" ::
    "-Ywarn-infer-any" ::
    "-Ywarn-value-discard" ::
    "-Ywarn-nullary-override" ::
    "-Ywarn-nullary-unit" ::
    "-Ywarn-unused" ::
    Nil,

  scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 12)) =>
        "-Ywarn-extra-implicit" ::
        Nil
      case _ =>
        Nil
    }
  },

  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")
)

lazy val root = (project in file("."))
  .aggregate(cuidJS, cuidJVM)
  .settings(commonSettings)

lazy val cuid = crossProject
  .settings(commonSettings)
  .settings(
    name := "scala-cuid",
    libraryDependencies ++=
      Deps.scalaTest.value % Test ::
      Nil
  )
  .jvmSettings(
    libraryDependencies ++=
      Deps.cuidJava.value ::
      Nil
  )
  .jsSettings(
    npmDependencies in Compile ++=
      "cuid" -> "1.3.8" :: //TODO update to 2.0.1
      Nil
  )

lazy val cuidJS = cuid.js.enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
lazy val cuidJVM = cuid.jvm
