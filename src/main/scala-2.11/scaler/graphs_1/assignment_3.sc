//Given an directed graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
//
//Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.
def dfsCycle(toVisit:Int,nodeVsEdgeGraph:Map[Int, Array[Int]], visited:Set[Int]):Boolean= {
  if (visited.contains(toVisit)) true
  else {
    val neighbours = nodeVsEdgeGraph.getOrElse(toVisit, Array[Int]())
    neighbours.foreach { neighbour => if (dfsCycle(neighbour, nodeVsEdgeGraph, visited + toVisit)) return true }
    false
  }
}
def solve(A: Int, B: Array[Array[Int]]): Int  = {
  val nodeVsEdgeGraph = B.groupBy(arr => arr(0)).map{case (key, valueArr) => (key,valueArr.flatten.filterNot(_ == key))}
  val cycleExists = (1 to A).exists(node => dfsCycle(node, nodeVsEdgeGraph, Set.empty))
  if(cycleExists) 1 else 0
}

solve(5, Array(Array(1, 2),
  Array(2, 3),
  Array(3, 4),
  Array(4, 5)))