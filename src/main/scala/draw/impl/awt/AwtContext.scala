package draw.impl.awt

import draw.Context
import graph.drawable.DrawableVertex

import java.awt.Shape
import java.awt.geom.{Ellipse2D, Line2D}

case class AwtContext(seq: Seq[Shape]) extends Context {
  override def drawLine(from: DrawableVertex, to: DrawableVertex): Context =
    copy(seq = new Line2D.Double(from.x, from.y, to.x, to.y) +: seq)

  override def drawCircle(center: DrawableVertex, radius: Double): Context =
    copy(seq = new Ellipse2D.Double(center.x - radius, center.y - radius, radius * 2, radius * 2) +: seq)
}
