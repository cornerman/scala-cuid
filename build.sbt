import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val commonSettings = Seq(
  organization := "com.github.cornerman",
  version      := "0.1.0-SNAPSHOT",

  scalaVersion := "2.12.13",
  crossScalaVersions := Seq("2.11.12", "2.12.13", "2.13.0"),

  scalacOptions ++=
    "-encoding" :: "UTF-8" ::
    "-unchecked" ::
    "-deprecation" ::
    "-explaintypes" ::
    "-feature" ::
    "-language:_" ::
    "-Xlint" ::
    "-Ywarn-value-discard" ::
    "-Ywarn-unused" ::
    Nil,

  scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 12) | (2, 13)) =>
        "-Ywarn-extra-implicit" ::
        Nil
      case Some((2, 11) | (2, 12)) =>
        "-Xfuture" ::
        "-Ywarn-nullary-override" ::
        "-Ywarn-nullary-unit" ::
        "-Ywarn-infer-any" ::
        "-Yno-adapted-args" ::
        "-Ypartial-unification" ::
        Nil
      case _ =>
        Nil
    }
  },

  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")
)

lazy val root = (project in file("."))
  .aggregate(cuidJS, cuidJVM)
  .settings(
    skip in publish := true
  )

lazy val cuid = crossProject(JSPlatform, JVMPlatform)
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
