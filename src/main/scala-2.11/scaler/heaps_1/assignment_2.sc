import scala.collection.mutable
//You are given an array A of integers that represent the lengths of ropes.
//
//You need to connect these ropes into one rope. The cost of joining two ropes equals the sum of their lengths.
//
//Find and return the minimum cost to connect these ropes into one rope.

def solve(A: Array[Int]): Int  = {
  val pq = mutable.PriorityQueue.empty(Ordering.Int).reverse

  A.foldLeft(pq){case (q, ele) => q.enqueue(ele)
    q }
var res = 0
  while(pq.size >2){
    val rope1 = pq.dequeue()
    val rope2 = pq.dequeue()
    res = res + (rope1+rope2)
    pq.enqueue(rope1+rope2)
  }

  pq.foldLeft(res){case (sum, ele) => sum+ele}
}

solve(Array(5, 17, 100, 11))