//https://leetcode.com/problems/minimum-size-subarray-sum/

def minSubArrayLenHelper(target: Int, prefixSum: Array[Int], left:Int, right:Int): Int={
  if(prefixSum(right)-prefixSum(left) >= target) minSubArrayLenHelper(target, prefixSum, left+1, right)
  else left
}

def minSubArrayLen(target: Int, nums: Array[Int]): Int = {

  if(nums.sum < target) 0
  else {
    val prefixSum = {
      nums.scanLeft(0) { case (acc, ele) => acc + ele }
    }.tail

    prefixSum.foldLeft(0, 0, Int.MaxValue, prefixSum(0)) { case ((left, right, minLength, _), ele) =>
      if (prefixSum(right) < target) {
        (left, right + 1, minLength, ele)
      }
      else {
        val newLeft = minSubArrayLenHelper(target, prefixSum, left, right)
        val currentLength = right - newLeft + 1
        (newLeft, right + 1, if (minLength < currentLength) minLength else currentLength, ele)
      }
    }._3
  }
}
minSubArrayLen(11, Array(1,1,1,1,1,1,1,1))