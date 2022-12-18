package draw.impl.fx

import draw.GraphApp
import scalafx.application.JFXApp3
import scalafx.scene.{Group, Node, Scene}

class FXApp(seq: Seq[Node], w: Int, h: Int) extends JFXApp3 with GraphApp {
  main(Array())

  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage {
      title = "FX Graph"
      width = w
      height = h
      resizable = false
      scene = new Scene {
        root = new Group() {
          children = seq
        }
      }
    }
}
