import scala.collection.mutable
//https://leetcode.com/problems/binary-subarrays-with-sum/
//HInt: prefix sum

def numSubarraysWithSum(A: Array[Int], S: Int): Int = {
  val x = A.scanLeft(0)(_+_).tail.scanLeft(mutable.HashMap(0->1),0){case (acc,ele) => val currentSum = ele-S
    val total = acc._2+ acc._1.getOrElse(currentSum,0)
  acc._1.update(ele, acc._1.getOrElse(ele,0)+1)
  //  println(s"hashmap ${acc._1} count is ${acc._2} and ele is $ele")
    (acc._1, total)}
  x.last._2
}

numSubarraysWithSum(Array(0,0,0,0,1),2)
