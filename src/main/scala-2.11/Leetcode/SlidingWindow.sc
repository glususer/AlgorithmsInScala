//https://leetcode.com/problems/max-consecutive-ones-iii/
def longestOnes(A: Array[Int], K: Int): Int = {
  var max = 0
  var i = 0
  var j = 0
  var zeroCounter = 0
  while(j<A.length){
    println(zeroCounter, j,i,max)
    if(A(j)==0){
      zeroCounter+=1
    }
    while(zeroCounter > K){
      if(A(i)==0) zeroCounter-=1
      i+=1
    }
    max = Math.max(max, j-i-1)
    j+=1
  }
  max
}

//longestOnes(Array(1,1,1,0,0,0,1,1,1,1,0),2)

//https://leetcode.com/problems/minimum-size-subarray-sum/

/*def helper(nums:List[Int],acc:List[(Int,Int)], target:Int):List[(Int,Int)]={
  nums match{
    case Nil => acc
    case x::xs =>   helper(xs,acc:::nums.scanLeft(0)(_+_).tail.zipWithIndex.filter(ele => ele._1 >= target), target)
  }
}
def minSubArrayLen(s: Int, nums: Array[Int]): Int = {
 val sumArrays = helper(nums.toList, List.empty, s)
  if(sumArrays.nonEmpty) sumArrays.minBy(y => y._2)._2+1
  else 0
}*/

//minSubArrayLen(7, Array(1,2,3,2,8))

def minSubArrayLen2(s: Int, nums: Array[Int]): Int = {
  if (nums.isEmpty) 0 else {
    var left = 0
    var right = 0
    var currrentMin = Integer.MAX_VALUE
    var sum = 0
    while (right < nums.length) {
      sum = sum + nums(right)
      while (left <= right && sum >= s) {
        println(s"left $left right $right currrentMin $currrentMin sum $sum")
        if(currrentMin > right-left+1) currrentMin = right-left+1
        sum -= nums(left)
        left += 1
      }
      right += 1
    }
    if(currrentMin == Integer.MAX_VALUE) 0 else currrentMin
  }
}

minSubArrayLen2(7, Array(3,3,1,4,4))

////https://leetcode.com/problems/subarray-product-less-than-k/
def numSubarrayProductLessThanK(nums: Array[Int], k: Int): Int = {
  var left =0
  var right =0
  var count =0
  var product = 1
  while(right < nums.length){
    product*=nums(right)
    while(left <=right && product>=k){
      product /=nums(left)
      left+=1

    }
    count += right-left+1
    right +=1
  }
  count
}
numSubarrayProductLessThanK(Array(10,5,2,6),100)


