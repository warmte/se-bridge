package graph.drawable

import cats.data.State.pure
import cats.implicits._
import draw.{Context, GraphApp, DrawingBuilderApi}
import graph.{Edge, Graph}

trait DrawableGraph extends Graph {
  val verticesNumber: Int

  def edgesList: Iterable[Edge[Int]]

  val radius: Int = drawingApi.width / 4

  private val vertices: IndexedSeq[DrawableVertex] = {
    val alpha: Double = 2 * math.Pi.toFloat / verticesNumber
    (0 until verticesNumber)
      .map(x => DrawableVertex(drawingApi.width / 2 + radius * math.cos(x * alpha), drawingApi.height / 2 + radius * math.sin(x * alpha)))
  }

  val edges: Iterable[Edge[DrawableVertex]] =
    edgesList.toSeq.map(x => Edge[DrawableVertex](vertices(x.from), vertices(x.to)))

  override def drawGraph(): GraphApp = {
    val app = for {
      _ <- vertices
        .map(drawingApi.drawCircle(_, 2))
        .fold(pure[Context, Unit](()))((x, y) => x.flatMap(_ => y))

      _ <- edges.flatMap(edge =>
        for {
          from <- vertices.find(_ == edge.from)
          to <- vertices.find(_ == edge.to)
        } yield drawingApi.drawLine(from, to)
      )
        .fold(pure[Context, Unit](()))((x, y) => x.flatMap(_ => y))
      result <- drawingApi.start()
    } yield result
    app.runA(drawingApi.context()).value
  }
}
