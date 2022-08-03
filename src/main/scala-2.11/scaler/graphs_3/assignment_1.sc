import scala.collection.mutable.ListBuffer
//Given an directed acyclic graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such
// that there is a edge directed from node B[i][0] to node B[i][1].
//
//Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
//
//Return the topological ordering of the graph and if it doesn't exist then return an empty array.
//
//If there is a solution return the correct ordering. If there are multiple solutions print the lexographically smallest one.
//
//Ordering (a, b, c) is said to be lexographically smaller than ordering (e, f, g) if a < e or if(a==e) then b < f and so on.

def topologicalSortBFS(toVisit:Array[Int], inDegreeArr : Array[Int], nodeVsEdgeGraph: Map[Int, Array[Int]], acc:List[Int], visited:Set[Int]):List[Int]={
  if(toVisit.isEmpty) acc
  else{
    val neighbours = toVisit.flatMap{node => nodeVsEdgeGraph.getOrElse(node, Array[Int]())}
      .filterNot(visited.contains)//.groupBy(identity).mapValues(_.length)


    inDegreeArr.zipWithIndex
      .foreach{case (inDegree,node) => toVisit.foreach(visit => if(node == visit) inDegreeArr(node) = -1)
        if(neighbours.contains(node)) inDegreeArr(node) = inDegree-neighbours.getOrElse(node,0)}

    val nextToVisit = inDegreeArr.zipWithIndex
      .filter{case (inDegree,node) => inDegree == 0 }
      .map{case (inDegree,node) => node}
    println(s"toVisit ${toVisit.toList} inDegreeArr ${inDegreeArr.zipWithIndex.toList} acc ${acc} neighbours ${neighbours}")

    topologicalSortBFS(nextToVisit, inDegreeArr, nodeVsEdgeGraph, acc++toVisit, visited++toVisit)
  }
}

def solve(A: Int, B: Array[Array[Int]]): Array[Int]  = {
  if(A == 1) return Array(0)
  else if(B.length == 0) return (0 until A).toArray

  def bfsCourse():Array[Int] = {
    val adjList = scala.collection.mutable.Map[Int,Set[Int]]().withDefaultValue(Set.empty)
    val indegrees = Array.fill(A)(0)

    B.foreach{pre =>
      adjList(pre(1)) = adjList(pre(1)) ++ Set(pre(0))
      indegrees(pre(0)) += 1
    }

    val q = scala.collection.mutable.Queue[Int]()
    indegrees.zipWithIndex.foreach{case(v,i) =>
      if(v == 0) q.enqueue(i)
    }
    val result = scala.collection.mutable.ArrayBuffer[Int]()

    while(q.nonEmpty){
      val next = q.dequeue
      result.append(next)
      adjList(next).foreach{neighbour =>
        indegrees(neighbour) -= 1
        if(indegrees(neighbour) == 0) q.enqueue(neighbour)
      }
    }
    if(result.length == A) result.toArray else Array.empty
  }
  bfsCourse()
}
solve(8,Array(Array(1, 4), Array(1, 2), Array(4, 2), Array(4, 3), Array(3, 2), Array(5, 2), Array(3, 5), Array(8, 2), Array(8, 6)))