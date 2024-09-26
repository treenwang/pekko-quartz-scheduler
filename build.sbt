import xerial.sbt.Sonatype.*

name := "pekko-quartz-scheduler"

organization := "com.github.treenwang"

version := "1.9.3-pekko-2.6.x"

val Scala212Version = "2.12.13"
val Scala213Version = "2.13.8"
val Scala3Version = "3.1.3"
val PekkoVersion = "1.1.1"

ThisBuild / scalaVersion := Scala213Version
ThisBuild / crossScalaVersions := Seq(Scala212Version, Scala213Version, Scala3Version)
ThisBuild / scalacOptions ++= Seq("-language:postfixOps")

libraryDependencies ++= Seq(
  "org.apache.pekko"     %% "pekko-actor"       % PekkoVersion % "provided",
  "org.apache.pekko"     %% "pekko-actor-typed" % PekkoVersion % "provided",
  "org.quartz-scheduler" % "quartz"           % "2.3.2"
    exclude ("com.zaxxer", "HikariCP-java7"),
  "org.apache.pekko" %% "pekko-testkit"             % PekkoVersion % Test,
  "org.apache.pekko" %% "pekko-actor-testkit-typed" % PekkoVersion % Test,
  "org.specs2"        %% "specs2-core"              % "4.15.0" % Test,
  "org.specs2"        %% "specs2-junit"             % "4.15.0" % Test,
  "junit"              % "junit"                    % "4.12"   % Test,
  "org.slf4j"          % "slf4j-api"                % "1.7.21" % Test,
  "org.slf4j"          % "slf4j-jcl"                % "1.7.21" % Test,
  "org.scalatest"     %% "scalatest"                % "3.2.12" % Test
)

// Sonatype release settings
pomIncludeRepository := { _ => false }
sonatypeCredentialHost := "s01.oss.sonatype.org"
publishTo := sonatypePublishToBundle.value
sonatypeProjectHosting := Some(
  GitHubHosting(user = "treenwang", repository = "pekko-quartz-scheduler", email = "wanglinchuan@126.com")
)
// Metadata referrsing to licenses, website, and SCM (source code management)
licenses := Seq("APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
sonatypeProfileName := "io.github.treenwang"
publishMavenStyle := true
scmInfo := Some(
  ScmInfo(
    url("https://github.com/treenwang/pekko-quartz-scheduler"),
    "scm:git@github.com:treenwang/pekko-quartz-scheduler.git"
  )
)

ThisBuild / githubWorkflowBuild := Seq(WorkflowStep.Sbt(List("test")))

ThisBuild / githubWorkflowTargetTags ++= Seq("*.*.*")
ThisBuild / githubWorkflowPublishTargetBranches := Seq.empty
ThisBuild / githubWorkflowPublish := Seq.empty

ThisBuild / githubWorkflowOSes := Seq("ubuntu-latest")

ThisBuild / githubWorkflowJavaVersions := Seq(
  JavaSpec.temurin("11")
)