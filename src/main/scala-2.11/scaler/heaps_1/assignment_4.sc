//Given an array of integers A and an integer B. You must modify the array exactly B number of times. In a single modification, we can replace any one array element A[i] by -A[i].
//You need to perform these modifications in such a way that after exactly B modifications, sum of the array must be maximum.
import scala.collection.mutable

def solve(A: Array[Int], B: Int): Int  = {
  val pq = mutable.PriorityQueue.empty(Ordering.Int).reverse

  A.foldLeft(pq){case (q, ele) => q.enqueue(ele)
    q}

  var i = B
  while(i > 0){
    pq.enqueue(pq.dequeue() * -1)
    i=i-1
  }

  var res = 0
  while(pq.nonEmpty){
    res = res+pq.dequeue()
  }
res
}

solve(Array(57, 3, -14, -87, 42, 38, 31, -7, -28, -61),10)

