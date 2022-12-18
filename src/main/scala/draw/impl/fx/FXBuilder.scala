package draw.impl.fx

import cats.data.State
import draw.{Context, GraphApp, DrawingBuilderApi}
import scalafx.scene.Node

case class FXBuilder(width: Int, height: Int) extends DrawingBuilderApi {

  override def context(): Context = FXContext(Seq())

  override def start(): State[Context, GraphApp] =
    for {
      seq <- State.inspect[Context, Seq[Node]] {
        case FXContext(seq) => seq
        case _ => Seq()
      }
    } yield new FXApp(seq, width, height)
}