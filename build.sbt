lazy val commonSettings = Seq(
  crossPaths := false,
  // don't need scala anymore
  EclipseKeys.projectFlavor := EclipseProjectFlavor.Java,
  EclipseKeys.withBundledScalaContainers := false,
  // still want sources
  EclipseKeys.withSource := true,
  // java 21
  EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE21),
  // avoid formatting settings slipping in to eclipse files
  EclipseKeys.jdtMode := EclipseJDTMode.Overwrite,
  // If unset below adds a /bin folder to the project ¯\_(ツ)_/¯ (eclipse default).
  // Select a folder distinct from the standard build folder,
  // that avoids Eclipse fighting with e.g. the sbt build. (ls: language server)
  Compile / EclipseKeys.eclipseOutput := Some("target/javals/classes"),
  Test / EclipseKeys.eclipseOutput := Some("target/javals/test-classes")
)

lazy val no = project
  .in(file("scala-no"))
  .settings(commonSettings)
  .settings(autoScalaLibrary := false)

lazy val si = project
  .in(file("scala-si"))
  .settings(commonSettings)


lazy val root = (project
  .in(file(".")))
  .aggregate(
    no,
    si
  )
