import java.net.URL
import scala.xml._
import sbtrelease._

//
// Environment variables used by the build:
// JAR_BUILT_BY      - Name to be added to Jar metadata field "Built-By" (defaults to System.getProperty("user.name")
//

name := "scalatestfx-build"

val projectInfo = Seq(
  organization := "io.scalatestfx",
  homepage := Some(url("https://github.com/haraldmaida/ScalaTestFX")),
  startYear := Some(2016),
  licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
)

// ScalaTestFX project
lazy val scalatestfx = Project(
  id = "scalatestfx",
  base = file("scalatestfx"),
  settings = commonSettings ++ Seq(
    description := "The ScalaTestFX Framework",
    publishArtifact := true,
    fork in run := true,
    libraryDependencies ++= Seq(
      scalatest,
      testfxCore,
      scalafx
    )
  )
)

// ScalaTestFX Demos project
lazy val scalatestfxDemos = Project(
  id = "scalatestfx-demos",
  base = file("scalatestfx-demos"),
  settings = commonSettings ++ Seq(
    description := "The ScalaTestFX Demonstrations",
    publishArtifact := false,
    fork in run := true,
    javaOptions ++= Seq(
      "-Xmx512M",
      "-Djavafx.verbose"
    ),
    libraryDependencies ++= Seq(
      scalafx
    )
  )
) dependsOn (scalatestfx % "compile;test->test")

//
// Dependencies
//
lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.0-RC2"
lazy val testfxCore = "org.testfx" % "testfx-core" % "4.0.4-alpha"
lazy val scalafx = "org.scalafx" %% "scalafx" % "8.0.92-R10"

//
// Resolvers
//
lazy val sonatypeNexusSnapshots = "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
lazy val sonatypeNexusStaging = "Sonatype Nexus Staging" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"

// Add snapshots to root project to enable compilation with Scala SNAPSHOT compiler,
// e.g., 2.11.0-SNAPSHOT
resolvers += sonatypeNexusSnapshots

// Root project is never published
publishArtifact := false

//
// Plugins
//
enablePlugins(
  GitBranchPrompt,
  GitVersioning
)

// Common settings
lazy val commonSettings = Seq(
//  version := buildVersion,
  crossScalaVersions := Seq("2.11.8", "2.12.0-M4"),
  scalaVersion <<= crossScalaVersions { versions => versions.head },
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xcheckinit", "-encoding", "utf8", "-feature"),
//  scalacOptions in(Compile, doc) ++= Opts.doc.title("ScalaTestFX API"),
//  scalacOptions in(Compile, doc) ++= Opts.doc.version(buildVersion),
//  scalacOptions in(Compile, doc) += s"-doc-external-doc:${scalaInstance.value.libraryJar}#http://www.scala-lang.org/api/${scalaVersion.value}/",
//  scalacOptions in(Compile, doc) ++= Seq("-doc-footer", s"ScalaTestFX API v.$buildVersion"),
  javacOptions ++= Seq(
    "-target", "1.8",
    "-source", "1.8",
    "-Xlint:deprecation"
  ),
  autoAPIMappings := true,
  fork in Test := true,
  parallelExecution in Test := false,
  manifestSetting,
  resolvers += sonatypeNexusSnapshots
//  shellPrompt in ThisBuild := { state => "sbt:" + Project.extract(state).currentRef.project + "> " }
) ++ mavenCentralSettings ++ bintraySettings

lazy val manifestSetting = packageOptions <+= (name, version, organization) map {
  (title, version, vendor) =>
    Package.ManifestAttributes(
      "Created-By" -> "Simple Build Tool",
      "Built-By" -> Option(System.getenv("JAR_BUILT_BY")).getOrElse(System.getProperty("user.name")),
      "Build-Jdk" -> System.getProperty("java.version"),
      "Specification-Title" -> title,
      "Specification-Version" -> version,
      "Specification-Vendor" -> vendor,
      "Implementation-Title" -> title,
      "Implementation-Version" -> version,
      "Implementation-Vendor-Id" -> vendor,
      "Implementation-Vendor" -> vendor
    )
}

//
// Git Versioning
//
git.baseVersion := "0.0.0-alpha"
val VersionTagRegex = "^v([0-9]+.[0-9]+.[0-9]+)(-.*)?$".r
git.gitTagToVersionNumber := {
  case VersionTagRegex(v,"") => Some(v)
  case VersionTagRegex(v,s) => Some(s"$v$s")  
  case _ => None
}
git.useGitDescribe := true

//
// Release Process
//
releaseCrossBuild := true
releaseProcess := ReleaseProcess.steps 
releasePublishArtifactsAction := PgpKeys.publishSigned.value
// use next version instead of current developer version
releaseVersion <<= (releaseVersionBump)(bumper => {
    ver => Version(ver).map(_.withoutQualifier).map(_.bump(bumper).string).getOrElse(versionFormatError)
})

//
// Publishing
//
lazy val bintraySettings = Seq(
  publishMavenStyle := true,
  bintrayReleaseOnPublish := false,
  bintrayOrganization in bintray := None
)

// Metadata needed by Maven Central
// See also http://maven.apache.org/pom.html#Developers
lazy val mavenCentralSettings = projectInfo ++ Seq(
  pomExtra <<= (pomExtra, name, description) {
    (pom, name, desc) => pom ++ Group(
      <scm>
        <url>https://github.com/haraldmaida/ScalaTestFX</url>
        <connection>scm:git:https://github.com/haraldmaida/ScalaTestFX.git</connection>
      </scm>
      <developers>
        <developer>
          <id>haraldmaida</id>
          <name>Harald Maida</name>
          <url>https://github.com/haraldmaida</url>
        </developer>
      </developers>
    )
  }
)
