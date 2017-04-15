
// [https://github.com/sbt/sbt-javaversioncheck]
addSbtPlugin("com.typesafe.sbt" % "sbt-javaversioncheck" % "0.1.0")

// [https://github.com/sbt/sbt-git]
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.5")

// [https://github.com/sbt/sbt-pgp]
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.1")

// [https://github.com/sbt/sbt-release]
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.3")

// [https://github.com/softprops/bintray-sbt]
addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")

// [https://github.com/sbt/sbt-site]
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.2.0")

// [https://github.com/sbt/sbt-ghpages]
resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"
addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.5.4")
