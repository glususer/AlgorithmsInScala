//https://leetcode.com/problems/maximum-ascending-subarray-sum/
def maxAscendingSum(nums: Array[Int]): Int = {
 val (maxSum, currentSum,_) = nums.foldLeft(0,0,0){case((maxSum, prevEle, currentSum), currEle) =>
  if(prevEle < currEle) (math.max(currentSum+currEle, maxSum), currEle,currentSum+currEle)
 else(math.max(currentSum, maxSum), currEle,currEle)}

 math.max(maxSum, currentSum)
}

maxAscendingSum(Array(100,10,1))