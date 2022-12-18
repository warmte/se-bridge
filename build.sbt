scalaVersion := "2.13.6"
libraryDependencies ++= Seq(
  "org.typelevel"   %% "cats-effect"       % "3.4.1",
  "org.scalafx"     %% "scalafx"           % "16.0.0-R25"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

fork := true
libraryDependencies ++= {
  lazy val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux") => "linux"
    case n if n.startsWith("Mac") => "mac"
    case n if n.startsWith("Windows") => "win"
    case _ => throw new Exception("Unknown platform!")
  }
  Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName)
}