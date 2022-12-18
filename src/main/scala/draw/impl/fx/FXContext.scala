package draw.impl.fx

import draw.Context
import graph.drawable.DrawableVertex
import scalafx.scene.Node
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Line}

case class FXContext(seq: Seq[Node]) extends Context {
  override def drawLine(from: DrawableVertex, to: DrawableVertex): Context =
    copy(seq = new Line {
      fill = Color.Gray
      stroke = Color.Gray
      startX = from.x
      startY = from.y
      endX = to.x
      endY = to.y
      smooth = true
    } +: seq)

  override def drawCircle(center: DrawableVertex, r: Double): Context = {
    copy(seq = new Circle {
      fill = Color.Gray
      stroke = Color.Gray
      centerX = center.x
      centerY = center.y
      radius = r
      smooth = true
    } +: seq)
  }
}