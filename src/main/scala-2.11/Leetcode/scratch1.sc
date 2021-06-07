//https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/

def smallestDivisorBS(nums:Array[Int], left:Int, right:Int, threshold:Int):Int={
  if(left < right){
    val mid = left+(right-left)/2
    val sumMid = nums.foldLeft(0){case (acc, ele) =>
      val q = ele/mid + {if(ele%mid == 0) 0 else 1}
    acc+q}
  //  println(s"left $left, mid $mid, right $right and sum is $sumMid")
    if(sumMid <= threshold) smallestDivisorBS(nums,left,mid,threshold )
    else smallestDivisorBS(nums, mid+1, right, threshold)
  } else left
}
def smallestDivisor(nums: Array[Int], threshold: Int): Int = {
  smallestDivisorBS(nums, 1, nums.max, threshold)
}

smallestDivisor(Array(21212,10101,12121),1000000)

//https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/

def BSIdxOfStartingNegativeNum(arr:Array[Int], left:Int, right:Int):Int={
  if(left < right){
    val mid = left+(right-left)/2
    if(arr(mid) >= 0)BSIdxOfStartingNegativeNum(arr, mid+1, right)
    else BSIdxOfStartingNegativeNum(arr, left, mid)
  }else {
    if(arr(left) <0 ) left else -1
  }
}
def countNegatives(grid: Array[Array[Int]]): Int = {
  grid.foldLeft(0){case (acc,arr) =>
    val idxOfFirstNegative = BSIdxOfStartingNegativeNum(arr, 0, arr.length-1)
    println(s"starting idx is ${idxOfFirstNegative}")
    acc+{if(idxOfFirstNegative >= 0)arr.length-idxOfFirstNegative else 0}
  }
}

countNegatives(Array(Array(4,3,2,1),Array(3,2,1,-1),Array(1,1,-1,-2), Array(-1,-1,-2,-3)))
