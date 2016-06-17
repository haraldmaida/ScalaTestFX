import sbt._
import sbt.Keys._
import sbt.Process._
import sbtrelease._
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleasePlugin.autoImport.ReleaseKeys._
import sbtrelease.ReleaseStateTransformations._

//
//defines the release process for the sbt-release plugin
//
object ReleaseProcess {
  import sbtrelease.ReleaseStateTransformations.{setReleaseVersion=>_,_}

  // we hide the existing definition for setReleaseVersion to replace it with our own
  lazy val setReleaseVersion: ReleaseStep = ReleaseProcess.setVersionOnly(_._1)

  def setVersionOnly(selectVersion: Versions => String): ReleaseStep =  { state: State =>
    val vs = state.get(ReleaseKeys.versions).getOrElse(
        sys.error("No versions are set! Was this release part executed before inquireVersions?"))
    val selected = selectVersion(vs)

    state.log.info("Setting version to '%s'." format selected)
    val useGlobal = Project.extract(state).get(releaseUseGlobalVersion)
    val versionStr = (if (useGlobal) globalVersionString else versionString) format selected

    reapply(Seq(
      if (useGlobal)
        version in ThisBuild := selected
      else
        version := selected
      ), state
    )
  }

  val steps = Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      setReleaseVersion,
      runClean,
      runTest,
//      commitReleaseVersion,
      tagRelease,
      publishArtifacts,
	  ReleaseStep(releaseStepCommand("makeSite")),
	  ReleaseStep(releaseStepCommand("ghpagesPushSite")),
//      setNextVersion,
//      commitNextVersion,
      pushChanges
    )

}
