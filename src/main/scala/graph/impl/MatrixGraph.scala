package graph.impl

import draw.DrawingBuilderApi
import graph.Edge
import graph.drawable.DrawableGraph

case class MatrixGraph(drawingApi: DrawingBuilderApi, verticesNumber: Int, matrix: IndexedSeq[IndexedSeq[Int]]) extends DrawableGraph {
  override def edgesList: Iterable[Edge[Int]] =
    (0 until verticesNumber).map {
      i =>
        (0 until verticesNumber).map {
          j => if (matrix(i)(j) == 1) Edge(i, j) else Edge(-1, -1)
        }
    }.foldLeft(IndexedSeq.empty[Edge[Int]])((x, y) => y ++ x)
      .filter(x => x != Edge(-1, -1))
}
