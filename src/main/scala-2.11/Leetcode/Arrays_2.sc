import scala.collection.mutable

//https://leetcode.com/problems/subarray-sums-divisible-by-k/
//public int subarraysDivByK(int[] A, int K) {
//        int[] map = new int[K];
//		map[0] = 1;
//        int count = 0, sum = 0;
//        for(int a : A) {
//            sum = (sum + a) % K;
//            if(sum < 0) sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
//            count += map[sum];
//            map[sum]++;
//        }
//        return count;
//    }
def subarraysDivByK(A: Array[Int], K: Int): Int = {
 val prefixSum =  A.scanLeft(0)(_+_).tail
  println(s"prefixSum ${prefixSum.toList}")
 val arr = Array.fill(math.max(math.abs(prefixSum.max),K)+1)(0)
  arr(0) = 1
 val remainderCount = prefixSum.foldLeft(arr,0) {case (acc,ele) => val remainder = ele%K
   val absRemainder = if(remainder < 0) K+remainder else remainder
   val currentCount = acc._2+acc._1(absRemainder)
   acc._1(absRemainder) = acc._1(absRemainder) +1
   (acc._1,currentCount)
 }

  println(s"${remainderCount._1.toList} ${prefixSum.toList}")

  remainderCount._2
 // remainderCount.filter{case (key,value) => value > 1}.map{case (key,value) => (1 until value).product}.sum
}

subarraysDivByK(Array(-2),6)

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
maxAbsoluteSum(Array(-3,-5,-3,-2,-6,3,10,-10,-8,-3,0,10,3,-5,8,7,-9,-9,5,-8))
maxAbsoluteSum(Array(2,-5,1,-4,3,-2))
