//Given N bags, each bag contains Bi chocolates. There is a kid and a magician.
//In a unit of time, the kid can choose any bag i, and eat Bi chocolates from it, then the magician will fill the ith bag with floor(Bi/2) chocolates.
//
//Find the maximum number of chocolates that the kid can eat in A units of time.
//
//NOTE:
//
//floor() function returns the largest integer less than or equal to a given number.
//Return your answer modulo 109+7

import scala.collection.mutable

def nchoc(A: Int, B: Array[Int]): Int  = {
  var i = A
  var totalChoclates = 0l
  val pq = mutable.PriorityQueue.empty(Ordering.Long)

  B.foldLeft(pq){case (q, ele) => q.enqueue(ele)
  q}
  val mod = 1000000007l

  while(i > 0){
    val numofChoclatesEaten = pq.dequeue()
    totalChoclates += numofChoclatesEaten%mod
    pq.enqueue(math.floor(numofChoclatesEaten/2).toLong)
    i=i-1
  }
  totalChoclates %= mod
  totalChoclates.toInt
}
//284628164
nchoc(10,Array(2147483647, 2000000014, 2147483647))