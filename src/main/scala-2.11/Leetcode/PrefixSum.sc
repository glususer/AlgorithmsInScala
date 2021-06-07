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

def subarraysDivByK2(A: Array[Int], K: Int): Int = {
  val prefixSum = A.scanLeft(0)(_ + _).tail
  val remainderCount = prefixSum.foldLeft(mutable.HashMap(0 -> 1),0) { case (acc, ele) => val remainder = ele % K
    val absRemainder = if (remainder < 0) K + remainder else remainder
    val count = acc._2+acc._1.getOrElse(absRemainder,0)
    acc._1.update(absRemainder, acc._1.getOrElse(absRemainder, 0) + 1)
    println(s"hashMap is ${acc._1} and  ele is $ele and count is $count and acc._2 is ${acc._2}")
    (acc._1,count)
  }
  remainderCount._2
}
subarraysDivByK2(Array(4,5,0,-2,-3,1),5)

//[7,-5,5,-8,-6,6,-4,7,-8,-7]
//7

//https://leetcode.com/problems/continuous-subarray-sum/
def checkSubarraySum(nums: Array[Int], k: Int): Boolean = {
  if(k == 0) {
    nums.zipWithIndex.foldLeft(0,false){case (acc,ele) => if(ele._1 == 0 ){
      if(ele._2 - acc._1 > 1)(ele._2 - acc._1,true)
      else(ele._2,false)
    }
    else(0, false)}
  }._2
  else{
    val prefixSum = nums.scanLeft(0)(_ + _).tail
    prefixSum.zipWithIndex.foldLeft(mutable.HashMap(0 -> -1), false) {
      case (acc, ele) =>
        val remainder = ele._1 % k
        val currentLength = if (acc._1.contains(remainder))
          ele._2 - acc._1.getOrElse(remainder, 0) else 0

        if (!acc._1.contains(remainder)) acc._1.put(remainder, ele._2)
        (acc._1, currentLength > 0)
    }._2
  }
}

checkSubarraySum(Array(0,1,0), 0)

//https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
def maxAbsoluteSum(nums: Array[Int]): Int = {
  val prefix = nums.scanLeft(0)(_+_).tail
  val prefixMax = prefix.max
  val prefixMin = prefix.min
  if(prefixMax < 0 && prefixMin < 0) math.abs(prefixMin)
  else if(prefixMax > 0 && prefixMin > 0) prefixMax
  else prefix.max-prefix.min
}
