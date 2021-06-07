//https://leetcode.com/problems/sum-of-distances-in-tree/

def dfs(edgeMap:Map[Int, Array[Int]], toVisit:Int, visited:Set[Int], distance:Int, node:Int):Int={
  if(visited.contains(toVisit) ) distance
  else if (toVisit == node) distance
  else{
      val neighbours = edgeMap.getOrElse(node, Array.emptyIntArray)
      neighbours.map(neighbour => dfs(edgeMap, toVisit, visited+node, distance+1,neighbour)).sum
  }
}
//val edgeMap = Map(0 -> Array(3), 1 -> Array(2), 2 -> Array(0))

//println(s"distance is ${dfs(edgeMap, 3, Set.empty,0,2)}")


def dfsNodeCount(directedEdgeMap:Map[Int, Array[Int]], toVisit:Int, visited:Set[Int], count:Array[Int], currentCount:Int):Int={
  if(visited.contains(toVisit)) currentCount
  else{
      val neighbours = directedEdgeMap.getOrElse(toVisit,Array.emptyIntArray).filterNot(visited.contains)
      val countTotal = neighbours.map(neighbour => dfsNodeCount(directedEdgeMap, neighbour, visited+toVisit, count,currentCount)+1).sum
      count(toVisit) = countTotal
      countTotal
  }
}

def distanceLoop(N:Int, node:Int,distanceArr:Array[Int], parent:Array[Int], directedEdgeMap:Map[Int,Array[Int]],nodeCount:Array[Int]):Unit={
  val near = nodeCount(node) +1
  val far = N-near
  val distance = distanceArr(parent(node)) - near + far
  distanceArr(node) = distance
  println(s"distance is $distance and node is $node and parent is ${parent(node)}, baseDist: ${distanceArr(parent(node))}, near: ${nodeCount(node) }, far:${ (N-1-nodeCount(node) )}")
  if(directedEdgeMap.contains(node)){
    directedEdgeMap.getOrElse(node,Array.emptyIntArray)
      .foreach{neighbour => distanceLoop(N,neighbour, distanceArr,parent, directedEdgeMap,nodeCount)}
  }
}

def sumOfDistancesInTree(N: Int, edges: Array[Array[Int]]): Array[Int] = {

  val graph = Array.fill(N)(Seq.empty[Int])
  edges.foreach{e =>
    graph(e.head) :+= e.last
    graph(e.last) :+= e.head
  }

 val directedEdgeMap =  graph.zipWithIndex.map{case(edges, idx) => (idx,edges.toArray)}.toMap

  println(s"directedEdgeMap is ${directedEdgeMap.foreach{case(node,edges) => println(s"node is $node and edges are ${edges.toList}")}}")

  val parent = Array.fill(N)(Integer.MAX_VALUE)

  directedEdgeMap.foreach{case(node,neighbours) => neighbours.foreach{to => parent(to) = node}}

  val distanceArr = Array.fill(N)(0)
  val startingNode = parent.zipWithIndex.filter(_._1 == Integer.MAX_VALUE).map(_._2).head
  val childCount = Array.fill(N)(0)

  dfsNodeCount(directedEdgeMap, startingNode,Set.empty, childCount, 0)
  println(s"node count arr is ${childCount.toList}")
  println(s"startingNode is $startingNode")

  val baseDistance =(0 until N)
    .map{to => dfs(directedEdgeMap, to, Set.empty,0,startingNode)}.sum

  println(s"baseDistance is $baseDistance and startingNode is $startingNode")

  distanceArr(startingNode) = baseDistance

  directedEdgeMap.getOrElse(startingNode, Array.emptyIntArray)
    .foreach(node => distanceLoop(N,node, distanceArr,parent, directedEdgeMap,childCount))

  distanceArr
}

//val edgeMap = Map(0 -> Array(1,2), 5 -> Array.emptyIntArray, 1 -> Array.emptyIntArray, 2 -> Array(3,4,5), 3 -> Array.emptyIntArray, 4 -> Array.emptyIntArray)

//val edgeMap = Map(2->Array(0), 1->Array(0))
sumOfDistancesInTree(3,Array(Array(2,1),Array(0,2)))
//sumOfDistancesInTree(4,Array(Array(1,2),Array(2,0),Array(0,3)))
//val countArr = Array.fill(10)(0)
//dfsNodeCount(edgeMap, 1,Set.empty, countArr,0)

//println(s"countArr is ${countArr.toList}")
