//Given an unsorted integer array A of size N.
//Find the maximum difference between the successive elements in its sorted form.
//
//Try to solve it in linear time/space.
//
//You may assume that all the elements in the array are non-negative integers and fit in the 32-bit signed integer
// range. You may also assume that the difference will not overflow.
//https://leetcode.com/problems/maximum-gap/discuss/1240543/Python-Bucket-sort-explained

def maximumGap(A: Array[Int]): Int  = {
  if(A.length==1)0
  else if(A.length==2) A(0)-A(1) max A(1)-A(0)
  else {
    val min = A.min
    val max = A.max
    val interval = (max - min) / (A.length - 1) max 1

    val minMaxWithBuckets = A.map { num => (num / interval, num) }
      .groupBy { case (bucket, num) => bucket }
      .mapValues(arr => arr.map(_._2)).toList.filter(_._2.nonEmpty)
      .sortBy(_._1)
      .map { case (bucket, nums) => (bucket, (nums.min, nums.max)) }
    
    val (_, _, diff) = minMaxWithBuckets.tail.foldLeft(minMaxWithBuckets.head._2._1, minMaxWithBuckets.head._2._2, 0) { case ((prevMin, prevMax, diff), lst) =>
      val currentMax = lst._2._2
      val currentMin = lst._2._1
      (currentMin, currentMax, currentMin - prevMax max diff)
    }
    diff
  }
}

maximumGap(Array(1,10,5))