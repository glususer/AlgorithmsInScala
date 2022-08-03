//Given an directed graph having A nodes labelled from 1 to A containing M edges given by matrix B of size M x 2such that there is a edge directed from node
//
//B[i][0] to node B[i][1].
//
//Find whether a path exists from node 1 to node A.
//
//Return 1 if path exists else return 0.
//
//NOTE:
//
//There are no self-loops in the graph.
//There are no multiple edges between two nodes.
//The graph may or may not be connected.
//Nodes are numbered from 1 to A.
//Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
def bfs(toVisit:Array[Int], visited:Set[Int],nodeVsEdgeGraph:Map[Int, Array[Int]], target:Int):Boolean={
  //println(s"toVisit ${toVisit.toList} visited $visited")
  if(toVisit.contains(target)) true
  else if(toVisit.isEmpty) false
  else{
    val neighbours = toVisit.flatMap(node => nodeVsEdgeGraph.getOrElse(node, Array[Int]()).filterNot(visited.contains)).distinct
    bfs(neighbours, visited++toVisit, nodeVsEdgeGraph, target)
  }
}
def solve(A: Int, B: Array[Array[Int]]): Int  = {
  val nodeVsEdgeGraph = B.groupBy(arr => arr(0)).map{case (key, valueArr) => (key,valueArr.flatten.filterNot(_ == key))}
  val pathExist = bfs(nodeVsEdgeGraph.getOrElse(1,Array[Int]()), Set.empty, nodeVsEdgeGraph, A)
  if(pathExist) 1 else 0
}

val matrix =  Array(Array(1,2),Array(4,1),Array(2,4),Array(3,4),Array(5,2),Array(1,3))//.groupBy(arr => arr(0)).map{case (key, valueArr) => (key,valueArr.flatten.filterNot(_ == key))}
solve(5,matrix)