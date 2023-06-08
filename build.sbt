inThisBuild(Seq(
  organization := "com.github.cornerman",

  scalaVersion := "2.12.18",
  crossScalaVersions := Seq("2.11.12", "2.12.18", "2.13.10", "3.2.0"),

  licenses := Seq("MIT License" -> url("https://opensource.org/licenses/MIT")),

  homepage := Some(url("https://github.com/cornerman/scala-cuid")),

  scmInfo := Some(ScmInfo(
    url("https://github.com/cornerman/scala-cuid"),
    "scm:git:git@github.com:cornerman/scala-cuid.git",
    Some("scm:git:git@github.com:cornerman/scala-cuid.git"))
  ),

  pomExtra :=
    <developers>
      <developer>
        <id>jkaroff</id>
        <name>Johannes Karoff</name>
        <url>https://github.com/cornerman</url>
      </developer>
    </developers>
))

lazy val commonSettings = Seq(
  scalacOptions --= Seq("-Xfatal-warnings")
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
