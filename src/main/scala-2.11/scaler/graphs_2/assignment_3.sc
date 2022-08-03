//Given a undirected graph having A nodes. A matrix B of size M x 2 is given which represents the edges such that there is an edge between B[i][0] and B[i][1].
//
//Find whether the given graph is bipartite or not.
//
//A graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B
//
//Note:
//
//There are no self-loops in the graph.
//
//No multiple edges between two pair of vertices.
//
//The graph may or may not be connected.
//
//Nodes are Numbered from 0 to A-1.
//
//Your solution will run on multiple testcases. If you are using global variables make sure to clear them.
def bfsBiPartite(toVisit:List[Int], visited1:Set[Int], visited2:Set[Int],nodeVsEdgeGraph:Map[Int, Array[Int]], totalNodes:Int):Boolean={
  //println(s"toVisit $toVisit,visited1 $visited1 visited2 $visited2 nodeVsEdgeGraph ${ nodeVsEdgeGraph.filterNot(toVisit.contains)}")
  if (toVisit.isEmpty) {
    if(nodeVsEdgeGraph.isEmpty) visited2.size+visited1.size == totalNodes
    else bfsBiPartite(nodeVsEdgeGraph.head._2.toList,visited1+nodeVsEdgeGraph.head._1, visited2, nodeVsEdgeGraph.filterNot(_._1 == nodeVsEdgeGraph.head._1),totalNodes)
  } else{
    val neighbours = toVisit.flatMap(node => nodeVsEdgeGraph.getOrElse(node, Array[Int]())).distinct//.filterNot(node => visited1.contains(node) && visited2.contains(node)).distinct
    if(neighbours.exists(visited1.contains) && neighbours.exists(visited2.contains))  false
    else if(neighbours.exists(visited1.contains))
      bfsBiPartite(neighbours.filterNot(visited1.contains),
        visited1, visited2++toVisit,
        nodeVsEdgeGraph.filterNot{case (k,_) => toVisit.contains(k)},
        totalNodes)
    else
      bfsBiPartite(neighbours.filterNot(visited2.contains),
        visited1++toVisit,
        visited2,
        nodeVsEdgeGraph.filterNot{case (k,_) => toVisit.contains(k)},
        totalNodes)
  }
}

def dfsBiPartite(toVisit:Int, visited1:Set[Int], visited2:Set[Int], nodeVsEdgeGraph:Map[Int, Array[Int]] ):Boolean={
  val neighbours = nodeVsEdgeGraph.getOrElse(toVisit, Array.empty)
  val unvisitedNeighbours = neighbours.filterNot(neighbour => visited2.contains(neighbour) || visited1.contains(neighbour)).distinct

  val neighboursInVisited1 = neighbours.filter(visited1.contains)
  val neighboursInVisited2 = neighbours.filter(visited2.contains)

  if(neighboursInVisited1.nonEmpty && neighboursInVisited2.nonEmpty) false
  else if(neighboursInVisited1.isEmpty && neighboursInVisited2.nonEmpty) unvisitedNeighbours.exists(neighbour => dfsBiPartite(neighbour, visited1, visited2+neighbour, nodeVsEdgeGraph))
  else unvisitedNeighbours.exists(neighbour => dfsBiPartite(neighbour, visited1+neighbour, visited2, nodeVsEdgeGraph))
}

def solve(A: Int, B: Array[Array[Int]]): Int  = {
  val concatenatedEdgesForUndirectedGraph = B++B.map(arr => Array(arr(1), arr(0)))
  val nodeVsEdgeGraph = concatenatedEdgesForUndirectedGraph.groupBy(arr => arr(0))
    .map{case (key, valueArr) => (key,valueArr.flatten.filterNot(_ == key))}
  val isBiPartitie = nodeVsEdgeGraph.keys.exists(node => dfsBiPartite(node, Set.empty, Set.empty, nodeVsEdgeGraph))
  if(isBiPartitie) 1 else 0
}

def solve1(A: Int, B: Array[Array[Int]]): Int  = {
  val concatenatedEdgesForUndirectedGraph = B++B.map(arr => Array(arr(1), arr(0)))
  val nodeVsEdgeGraph = concatenatedEdgesForUndirectedGraph.groupBy(arr => arr(0))
    .map{case (key, valueArr) => (key,valueArr.flatten.filterNot(_ == key))}
  //println(s"nodeVsEdgeGraph ${nodeVsEdgeGraph.mapValues(_.toList)}")
  val isBiPartitie = bfsBiPartite(nodeVsEdgeGraph.head._2.toList,Set(nodeVsEdgeGraph.head._1), Set.empty, nodeVsEdgeGraph.filterNot(_._1 == nodeVsEdgeGraph.head._1),nodeVsEdgeGraph.size)
  if(isBiPartitie) 1 else 0
}
solve1(8,Array(Array(5, 6), Array(1, 3), Array(2, 4), Array(4, 5)))

def helperBiPartite(visited:Array[Int], toVisit:Int, edgeArr:Map[Int,Array[Int]]):Boolean={
  val neighbours = edgeArr.getOrElse(toVisit,Array.empty)
  if(visited(toVisit) == 0) visited(toVisit) = 1

  val unvisitedNeighbours = neighbours.filter(neighbour => visited(neighbour) == 0)

  unvisitedNeighbours.foreach{neighbour =>if(visited(toVisit) == 1) visited(neighbour) =2 else visited(neighbour) = 1}

  if (neighbours.exists{neighbour => visited(toVisit) == visited(neighbour)}) false
  else unvisitedNeighbours.forall{neighbour => helperBiPartite(visited, neighbour, edgeArr)}
}

def isBipartite(A:Int,B: Array[Array[Int]]): Int = {
  val concatenatedEdgesForUndirectedGraph = B++B.map(arr => Array(arr(1), arr(0)))

  val edgeArray =  concatenatedEdgesForUndirectedGraph.groupBy(arr => arr(0))
    .map{case (key, valueArr) => (key,valueArr.flatten.filterNot(_ == key))}

  val visited = Array.fill(A)(0)
  val isBiPartite = edgeArray.keys.forall(node => helperBiPartite(visited, node, edgeArray))
  if(isBiPartite) 1 else 0
}


