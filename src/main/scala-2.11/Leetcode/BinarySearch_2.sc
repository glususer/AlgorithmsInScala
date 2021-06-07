def searchRangeHelper(nums:Array[Int], target:Int, left:Int, right:Int, index: String)(fn : Int => Int) :Int={
  if(left < right){
    val mid = left+(right-left)/2
    if(nums(mid) > target) searchRangeHelper(nums, target, left, mid, index)(fn)
    else if(nums(mid) < target) searchRangeHelper(nums, target, mid+1, right, index)(fn)
    else{
      if(index == "LEFT")
        if(mid > 0 && nums(mid-1) != nums(mid))  mid
        else  searchRangeHelper(nums, target, left, fn(mid), index)(fn)
      else {
        if(mid < nums.length && nums(mid) != nums(mid+1)) mid
        else searchRangeHelper(nums, target, fn(mid), right, index)(fn)
      }
    }
  } else {
    if(nums (left) == target) left else -1
  }
}
def searchRange(nums: Array[Int], target: Int): Array[Int] = {
  if(nums.isEmpty)Array(-1,-1)
  else {
    val left = searchRangeHelper(nums, target, 0, nums.length - 1, "LEFT")(x => x - 1)
    val right = searchRangeHelper(nums, target, 0, nums.length - 1, "RIGHT")(x => x + 1)
    Array(left, right)
  }
}

@scala.annotation.tailrec
def maxDistanceHelper(nums:Array[Int], left:Int, right:Int, target: Int):Int={
  if(left < right){
    val mid = (right+left)/2 +1
    if(nums(mid) < target) maxDistanceHelper(nums, left, mid-1, target)
    else maxDistanceHelper(nums, mid, right, target)
  }
  else right
}
//https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/
def maxDistance(nums1: Array[Int], nums2: Array[Int]): Int = {
 nums1.zipWithIndex.foldLeft(0){case (maxDistance, (ele,index)) =>
   val bsIndex = maxDistanceHelper(nums2, 0, nums2.length-1, ele)
   println(s"bsIndex $bsIndex for ele $ele and index $index")
     Math.max(bsIndex - index, maxDistance)
 }
}

//nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
//(0,0), (2,2), (2,3), (2,4), (3,3), (3,4), and (4,4)
//maxDistanceHelper(Array(20,8,6,2,1), 0, 4,8)

maxDistance(Array(2,2,2), Array(10,10,1))
