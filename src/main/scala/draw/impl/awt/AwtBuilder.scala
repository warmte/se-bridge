package draw.impl.awt

import cats.data.State
import draw.{Context, GraphApp, DrawingBuilderApi}

import java.awt.Shape

case class AwtBuilder(width: Int, height: Int) extends DrawingBuilderApi {

  override def context(): Context = AwtContext(Seq())

  override def start(): State[Context, GraphApp] =
    for {
      seq <- State.inspect[Context, Seq[Shape]] {
        case AwtContext(seq) => seq
        case _ => Seq()
      }
    } yield new AwtApp(seq, width, height)
}