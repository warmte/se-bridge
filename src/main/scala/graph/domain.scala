package graph

import draw._

case class Edge[T](from: T, to: T)

trait Graph {
  def drawingApi: DrawingBuilderApi

  def drawGraph(): GraphApp
}
