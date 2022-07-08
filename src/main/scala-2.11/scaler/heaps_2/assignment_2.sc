import scala.math
//N people having different priorities are standing in a queue.
//
//The queue follows the property that each person is standing at most B places away from its position in the sorted queue.
//
//Your task is to sort the queue in the increasing order of priorities.
//No two persons can have the same priority.
//Use the property of the queue to sort the queue with complexity O(NlogB).

def solve(A: Array[Int], B: Int): Array[Int]  = {
  import scala.collection.mutable
  if(B>A.length-1)A.sorted
  else if(B==0) A
  else {
    val pq = mutable.PriorityQueue.empty[(Int, Int)](Ordering.by(ord => ord._1)).reverse
    A.zipWithIndex.foldLeft(pq) { case (q, (ele, idx)) => q.enqueue((ele, idx))
      q
    }
    val result = Array.fill(A.length)(0)
    var i = 0
    val acc = mutable.ListBuffer.empty[(Int, Int)]

    while (i<A.length) {
      var (currentMin,currentIdx)  = pq.dequeue()
      while (pq.nonEmpty && math.abs(currentIdx - i) > B) {
        //println(s"currentDiff ${math.abs(currentIdx - i)} allowed $B")
        acc  += ((currentMin, currentIdx))
        var current =  pq.dequeue()
        currentMin  = current._1
        currentIdx = current._2
      }
      result(i) = currentMin
      i += 1
      //println(s"acc $acc pq size ${pq.size} currentMin $currentMin currentIdx $currentIdx result ${result.toList} head ${if(pq.nonEmpty)pq.head else "EMPTY"} ")
      acc.foldLeft(pq) { case (q, ele) => q.enqueue(ele)
        q
      }
      acc.clear()
    }

    result
  }
}
/*solve(Array(10,9,8,7,6),0)
solve(Array(10,9,8,7,6),1)*/
solve(Array(10,9,8,7,6),5)
/*
solve(Array(10,9,8,7,6),3)
solve(Array(10,9,8,7,6),4)
solve(Array(10,9,8,7,6),5)*/
