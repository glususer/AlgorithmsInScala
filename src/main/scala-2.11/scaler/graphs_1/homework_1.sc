//You are given N towns (1 to N). All towns are connected via unique directed path as mentioned in the input.
//
//Given 2 towns find whether you can reach the first town from the second without repeating any edge.
//
//B C : query to find whether B is reachable from C.
//
//Input contains an integer array A of size N and 2 integers B and C ( 1 <= B, C <= N ).
//
//There exist a directed edge from A[i] to i+1 for every 1 <= i < N. Also, it's guaranteed that A[i] <= i for every 1 <= i < N.
//
//NOTE: Array A is 0-indexed. A[0] = 1 which you can ignore as it doesn't represent any edge.
def dfs(nodeVsEdgeMap: Map[Int, Array[Int]], target: Int, toVisit: Int, visited:Set[Int]):Boolean={
  println(s"target $target toVisit $toVisit visited $visited nodeVsEdgeMap ${nodeVsEdgeMap.mapValues(_.toList)}")
  if(toVisit == target) true
  else if(visited.isEmpty) false
  else{
    val neighbours = nodeVsEdgeMap.getOrElse(toVisit, Array[Int]())
    val canVisit = neighbours.exists(neighbour => dfs(nodeVsEdgeMap, target, neighbour, visited + toVisit))
    //println(s"neighbours ${neighbours} canVisit $canVisit ")
    canVisit
  }
}

def solve(A: Array[Int], B: Int, C: Int): Int  = {
  if(B==C) 1
  else {
    val nodeVsEdgeMap = A.zip(1 to A.length).tail.groupBy(_._1).map { case (key, valueArr) => (key, valueArr.map(_._2)) }
    val isReachable = nodeVsEdgeMap.getOrElse(C, Array[Int]()).exists(neighbour => dfs(nodeVsEdgeMap, B, neighbour, Set(C)))
    if (isReachable) 1 else 0
  }
}

solve(Array(1,1,1,1,1), 1,1)