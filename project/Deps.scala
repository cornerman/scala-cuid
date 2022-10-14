import sbt._
import Keys._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._

object Deps {
  // hack to expand %%% in settings, needs .value in build.sbt
  import Def.{setting => dep}

  val scalaTest = dep("org.scalatest" %%% "scalatest" % "3.2.14")
  val cuidJava = dep("cool.graph" % "cuid-java" % "0.1.1")
}
