
def dfsBiPartite(toVisit:Int, visited:Array[Int], nodeVsEdgeGraph:Map[Int, Array[Int]] ):Boolean={
  val neighbours = nodeVsEdgeGraph.getOrElse(toVisit, Array.empty)
  val unvisitedNeighbours = neighbours.filter(neighbour => visited(neighbour) ==0)
  if(visited(toVisit) == 0) visited(toVisit) = 1

  unvisitedNeighbours.foreach{neighbour =>if(visited(toVisit) == 1) visited(neighbour) =2 else visited(neighbour) = 1}


  if (neighbours.exists{neighbour => visited(toVisit) == visited(neighbour)}) false
  else unvisitedNeighbours.forall{neighbour => dfsBiPartite(neighbour,visited, nodeVsEdgeGraph)}
}

def solve(A: Int, B: Array[Array[Int]]): Int  = {
  val concatenatedEdgesForUndirectedGraph = B++B.map(arr => Array(arr(1), arr(0)))
  val nodeVsEdgeGraph = concatenatedEdgesForUndirectedGraph.groupBy(arr => arr(0))
    .map{case (key, valueArr) => (key,valueArr.flatten.filterNot(_ == key))}
  val visited = Array.fill(A)(0)
  val isBiPartitie = nodeVsEdgeGraph.keys.forall(node => dfsBiPartite(node, visited, nodeVsEdgeGraph))
  if(isBiPartitie) 1 else 0
}

solve(9, Array(Array(2, 5),
  Array(6, 7),
  Array(4, 8),
  Array(2, 3),
  Array(0, 3),
  Array(4, 7),
  Array(1, 8),
  Array(3, 8),
  Array(1, 3)))

val l1 = List(1,2,3)
val l2 = List(4,5,6)
val ll = List(1,2,3,4,5,6,7,8)

ll.filterNot{node => l2.contains(node) || l1.contains(node)}