class Graph[T] {
  type Vertex = T
  type GraphMap = Map[Vertex,List[Vertex]]
  var g:GraphMap = Map()

  def BFS(start: Vertex): List[List[Vertex]] = {

    def BFS0(elems: List[Vertex],visited: List[List[Vertex]]): List[List[Vertex]] = {
      val newNeighbors = elems.flatMap(g(_)).filterNot(visited.flatten.contains).distinct
      if (newNeighbors.isEmpty)
        visited
      else
        BFS0(newNeighbors, newNeighbors :: visited)
    }

    BFS0(List(start),List(List(start))).reverse
  }

  def DFS(start: Vertex): List[Vertex] = {

    def DFS0(v: Vertex, visited: List[Vertex]): List[Vertex] = {
      if (visited.contains(v))
        visited
      else {
        val neighbours:List[Vertex] = g(v) filterNot visited.contains
        val x = neighbours.foldLeft(v :: visited)((b,a) => DFS0(a,b))
        x
      }
    }
    DFS0(start,List()).reverse
  }
}

object Graph{
  def main(args: Array[String]): Unit = {
    val intGraph = new Graph[Int]
    intGraph.g = Map(1 -> List(2,4), 2-> List(1,3), 3-> List(2,4), 4-> List(1,3))
    println(intGraph.BFS(1))

    println(intGraph.BFS(2))

    println(intGraph.DFS(3))
  }
}
