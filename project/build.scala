import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object SolarrBuild extends Build {
  val Organization = "com.torre"
  val Name = "solarr"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.7"
  val ScalatraVersion = "2.4.+"

  lazy val project = Project (
    "solarr",
    file("."),
    settings = ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
      resolvers += "Scalaz Bintray" at "http://dl.bintray.com/scalaz/releases",
      libraryDependencies ++= Seq(
        "org.json4s"   %% "json4s-native" % "3.3.0",
        "org.scalatra" %% "scalatra-swagger"  % ScalatraVersion,
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.3" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.1.5.v20140505" % "container",
        "org.eclipse.jetty" % "jetty-plus" % "9.1.5.v20140505" % "container",
        "javax.servlet" % "javax.servlet-api" % "3.1.0" % "container;provided;test" 
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq.empty,  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}
