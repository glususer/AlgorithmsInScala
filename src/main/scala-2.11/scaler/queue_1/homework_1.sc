//Given an array of integers A and an integer B, we need to reverse the order of the first B elements of the array, leaving the other elements in the same relative order.
//
//NOTE: You are required to the first insert elements into an auxiliary queue then perform Reversal of first B elements.

def solve(A: Array[Int], B: Int): Array[Int]  = {
  if(B<=1) A
  else {
    import scala.collection.mutable
    val queue = mutable.Queue[Int]()
    (0 until B).foldLeft(queue) { case (q, idx) =>
      q.enqueue(A(idx))
      q
    }

    for (i <- B-1 to 0 by -1)
      A(i) = queue.dequeue()
    A
  }
}
val arr = Array(4,5,7,8,10)
//solve(arr,0)
//solve(Array(4,5,7,8,10),1)
//solve(Array(4,5,7,8,10),2)
//solve(Array(4,5,7,8,10),3)
//solve(Array(4,5,7,8,10),4)
solve(Array(4,5,7,8,10),5)