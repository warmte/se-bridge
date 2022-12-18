package draw.impl.awt

import draw.GraphApp

import java.awt._
import javax.swing.{JFrame, WindowConstants}

class AwtApp(seq: Seq[Shape], width: Int, height: Int)
  extends JFrame
    with GraphApp {
  setTitle("AWT Graph")
  setSize(new Dimension(width, height))
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  setResizable(false)
  setLocationRelativeTo(null)
  setVisible(true)
  toFront()

  override def paint(graphics: Graphics): Unit =
    graphics match {
      case d: Graphics2D =>
        d.setColor(Color.GRAY)
        getContentPane.getHeight
        seq.foreach(d.fill)
        seq.foreach(d.draw)
    }
}
