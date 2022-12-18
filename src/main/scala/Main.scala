import draw.DrawingBuilderApi
import draw.impl.awt.AwtBuilder
import draw.impl.fx.FXBuilder
import graph._
import graph.impl._

import scala.io.Source

object Main extends App {

  case class GraphDrawerException(message: String) extends Exception

  private val argumentsError = "Wrong arguments. Please, list graph type [list, matrix], drawer type [fx, awt] and input file path."

  private def graphError(t: String) = f"Your $t graph is listed wrong. Please, type number of vertices in the first line and your graph in others."

  private def getGraph(drawingApi: DrawingBuilderApi, graphType: String, path: String): Graph = {

    Source.fromFile(path).getLines.toList match {
      case nStr +: tail =>
        val n = Integer.parseInt(nStr)
        graphType match {
          case "list" =>
            val edges = tail.map(_.split(" ").map(Integer.parseInt)).map {
              case Array(x, y) => Edge(x, y)
              case _ => throw GraphDrawerException(graphError(graphType))
            }
            ListGraph(drawingApi = drawingApi, verticesNumber = n, edgesList = edges)
          case "matrix" =>
            val matrix = tail.map(_.split(" ").map(Integer.parseInt)).map {
              case array if array.length == n && !array.exists(x => x != 0 && x != 1) => array.toIndexedSeq
              case _ => throw GraphDrawerException(graphError(graphType))
            }.toIndexedSeq
            MatrixGraph(drawingApi = drawingApi, verticesNumber = n, matrix = matrix)
          case _ => throw GraphDrawerException(argumentsError)
        }
      case _ => throw GraphDrawerException(graphError(graphType))
    }

  }

  val graph: Graph = args.toList match {
    case List(graphType, drawerType, inputPath) => drawerType match {
      case "fx" => getGraph(FXBuilder(300, 300), graphType, inputPath)
      case "awt" => getGraph(AwtBuilder(300, 300), graphType, inputPath)
      case _ => throw GraphDrawerException(argumentsError)
    }
    case _ => throw GraphDrawerException(argumentsError)
  }

  graph.drawGraph()
}
