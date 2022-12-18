package graph.impl

import draw.DrawingBuilderApi
import graph.Edge
import graph.drawable.DrawableGraph

case class ListGraph(drawingApi: DrawingBuilderApi, verticesNumber: Int, edgesList: Seq[Edge[Int]]) extends DrawableGraph
