/*
 * Copyright (c) 2016, Innoave.com
 * All rights reserved.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL INNOAVE.COM OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import sbtrelease._

//
// Environment variables used by the build:
// BUILT_BY      - Name to be added to Jar metadata field "Built-By" (defaults to System.getProperty("user.name")
//

name := "scalatestfx-build"

//
// Project Modules
//

lazy val scalatestfx = Project(
  id = "scalatestfx",
  base = file("scalatestfx"),
  settings = commonSettings ++ ghpages.settings ++ Seq(
    description := "The ScalaTestFX Framework",
    publishArtifact := true,
    fork in run := true,
    libraryDependencies ++= Seq(
      scalatest,
      testfxCore,
      scalafx
    )
  )
).enablePlugins(
  SiteScaladocPlugin
)

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
) dependsOn (
  scalatestfx % "compile;test->test"
)

//
// Dependencies
//
lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.1"
lazy val testfxCore = "org.testfx" % "testfx-core" % "4.0.4-alpha"
lazy val scalafx = "org.scalafx" %% "scalafx" % "8.0.102-R11"
//lazy val scalaJava8Compat = "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0"

//
// Plugins
//
enablePlugins(
  GitBranchPrompt
//  JekyllPlugin
)

commonSettings
publishArtifact := false

//
// Common Settings
//
lazy val commonSettings = projectSettings ++ buildSettings

//
// Project Settings
//

lazy val projectSettings = Seq(
  organization := "io.scalatestfx",
  homepage := Some(url("https://github.com/haraldmaida/ScalaTestFX")),
  startYear := Some(2016),
  licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  scmInfo := Some(ScmInfo(
    url("https://github.com/haraldmaida/ScalaTestFX"),
      "scm:git:git@github.com:haraldmaida/ScalaTestFX.git")
  ),
  git.remoteRepo := "git@github.com:haraldmaida/ScalaTestFX.git"
)

//
// Build Settings
//
lazy val buildSettings = Seq(
  crossScalaVersions := Seq("2.12.1", "2.11.10"),
  scalaVersion := crossScalaVersions.value.head,
  scalacOptions ++= Seq(
    "-target:jvm-1.8",
    "-encoding", "utf8",
    "-unchecked",
    "-deprecation",
//    "-optimise",
    "-feature",
    "-language:_",
    "-Xfatal-warnings",
    "-Xlint:_",
//    "-Yinline-warnings",      // seems to be not supported in 2.12
    "-Ypartial-unification",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",      // N.B. doesn't work well with the ??? hole
    "-Ywarn-numeric-widen",
    "-Ywarn-unused-import",    // 2.11+ only
//    "-Ywarn-value-discard",
    "-Ywarn-unused"
  ),
  javacOptions ++= Seq(
    "-target", "1.8",
    "-source", "1.8",
    "-xlint:deprecation"
  ),
  javaVersionPrefix in javaVersionCheck := Some("1.8"),
  sourcesInBase := false,
  parallelExecution := false,
  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots"),
    Resolver.bintrayRepo("haraldmaida", "maven")
  ),
  manifestSetting
)

lazy val manifestSetting = packageOptions += {
  Package.ManifestAttributes(
    "Created-By" -> "Simple Build Tool",
    "Built-By" -> Option(System.getenv("BUILT_BY")).getOrElse(System.getProperty("user.name")),
    "Build-Jdk" -> System.getProperty("java.version"),
    "Specification-Title" -> name.value,
    "Specification-Version" -> version.value,
    "Specification-Vendor" -> organization.value,
    "Implementation-Title" -> name.value,
    "Implementation-Version" -> version.value,
    "Implementation-Vendor-Id" -> organization.value,
    "Implementation-Vendor" -> organization.value
  )
}

//
// Release Process
//
releaseCrossBuild := true
//releaseProcess := ReleaseProcess.steps
releasePublishArtifactsAction := PgpKeys.publishSigned.value
// use next version instead of current developer version
releaseVersion := {
    ver => Version(ver).map(_.withoutQualifier).map(_.bump(releaseVersionBump.value).string).getOrElse(versionFormatError)
}

//
// Publishing
//
lazy val bintraySettings = Seq(
  publishMavenStyle := true,
  bintrayReleaseOnPublish := false,
  bintrayOrganization in bintray := None
)

lazy val publishSettings = bintraySettings ++ Seq(
  publishArtifact := true,
  publishArtifact in Test := false,
  // Metadata needed by Maven Central
  // See also http://maven.apache.org/pom.html#Developers
  pomExtra := (
    <developers>
      <developer>
        <id>haraldmaida</id>
        <name>Harald Maida</name>
        <url>https://github.com/haraldmaida</url>
      </developer>
    </developers>
    )
)

//
// Project website
//
//sourceDirectory in Jekyll := baseDirectory.value / "website"
