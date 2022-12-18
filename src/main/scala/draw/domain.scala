package draw

import cats.data.State
import cats.data.State.modify
import graph.drawable.DrawableVertex

trait Context {
  def drawLine(from: DrawableVertex, to: DrawableVertex): Context

  def drawCircle(center: DrawableVertex, radius: Double): Context
}

trait GraphApp

trait DrawingBuilderApi {
  def width: Int

  def height: Int

  def context(): Context

  def start(): State[Context, GraphApp]

  def drawLine(from: DrawableVertex, to: DrawableVertex): State[Context, Unit] =
    modify[Context](_.drawLine(from, to))

  def drawCircle(center: DrawableVertex, radius: Double): State[Context, Unit] =
    modify[Context](_.drawCircle(center, radius))
}
