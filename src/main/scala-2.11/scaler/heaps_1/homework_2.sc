//Misha loves eating candies. She has been given N boxes of Candies.
//She decides that every time she will choose a box having the minimum number of candies, eat half of the candies and put the remaining candies in the other box that has the minimum number of candies.
//Misha does not like a box if it has the number of candies greater than B so she won't eat from that box. Can you find how many candies she will eat?
//NOTE 1: If a box has an odd number of candies then Misha will eat the floor(odd / 2).
//NOTE 2: The same box will not be chosen again

def solve(A:Array[Int], B:Int):Int={
  import scala.collection.mutable

  val pq = mutable.PriorityQueue.empty(Ordering.Int).reverse

  A.foldLeft(pq){case(q, ele) => q.enqueue(ele)
  q
  }
  var ans = 0
  var flag = true
  while(pq.nonEmpty && flag) {
    val x = pq.dequeue()
    if (x <= B && flag) {
      val y = x / 2
      ans = ans + y
      if (pq.nonEmpty) {
        val remaining = x - y
        val otherMin = pq.dequeue()
        pq.enqueue(otherMin + remaining)
      } else flag = false
    }
  }
  ans
}