import scala.collection.mutable

//https://leetcode.com/problems/sliding-window-maximum/
def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {
  if(k >= nums.length)Array(nums.max)
  else {
    val pq = mutable.PriorityQueue.empty[(Int,Int)](Ordering.by(numWithIdx => numWithIdx._1))
    val outputArr = Array.fill(nums.length - (k - 1))(0)
    pq.enqueue(nums.zipWithIndex.take(k):_*)
    outputArr(0) = pq.head._1

    nums.zipWithIndex.drop(k).foldLeft(outputArr) { case (_, ele) =>
      val idx = ele._2
      if (idx - pq.head._2 >= k) pq.dequeue()
      pq.enqueue(ele)
      while(idx-pq.head._2 >= k)
        pq.dequeue()
      outputArr(idx - k+1) = pq.head._1
      outputArr
    }
    outputArr
  }
}
/*maxSlidingWindow(Array(1),1)
maxSlidingWindow(Array(1,-1),1)
maxSlidingWindow(Array(9,11),2)*/
maxSlidingWindow(Array(9,10,9,-7,-4,-8,2,-6),5)
//[9,10,9,-7,-4,-8,2,-6]
//5