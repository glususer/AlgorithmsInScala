@scala.annotation.tailrec
def maxDistanceHelper(nums:Array[Int], left:Int, right:Int, target: Int):Int={
 // println(s"in helper left $left right $right")
  if(left < right){
    val mid = left+(right-left)/2
  //  println(s"in helper mid $mid ")
    if(nums(mid) <= target) maxDistanceHelper(nums, left, mid, target)
    else maxDistanceHelper(nums, mid+1, right, target)
  }
  else {
    if(nums(left) < target) left-1 else left
  }
}

@scala.annotation.tailrec
def maxDistanceHelper2(nums:Array[Int], left:Int, right:Int, target: Int):Int={
 // println(s"in helper2 left $left right $right")
  if(left < right){
    val mid = (left+right)/2+1
  // println(s"in helper2 mid $mid ")

    if(nums(mid) < target) maxDistanceHelper2(nums, left, mid-1, target)
    else maxDistanceHelper2(nums, mid, right, target)
  }
  else right
}
//https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/
def maxDistance(nums1: Array[Int], nums2: Array[Int]): Int = {
  nums1.zipWithIndex.foldLeft(0){case (maxDistance, (ele,index)) =>
    val bsIndex = maxDistanceHelper(nums2, 0, nums2.length-1, ele)
    val bsIndex2 = maxDistanceHelper2(nums2, 0, nums2.length-1, ele)
    println(s"bsIndex $bsIndex bsIndex2 $bsIndex2 for ele $ele and index $index")
    Math.max(bsIndex - index, maxDistance)
  }
}

//nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
//(0,0), (2,2), (2,3), (2,4), (3,3), (3,4), and (4,4)
//maxDistanceHelper(Array(20,8,6,2,1), 0, 4,8)

//maxDistance(Array(55,30,5,4,2), Array(100,20,10,10,5))

def minSpeedCondition(speed: Int, hour: Double, dist: Array[Int]):Boolean={
  val lastIndx = dist.length-1
  val timeTaken = "%09.9f".format(dist.zipWithIndex.foldLeft(0.0){case(totalTime, (currentDistance, idx)) =>
    val currentTime = (currentDistance*1.0)/speed
    if(idx < lastIndx) totalTime+math.ceil(currentTime)
    else totalTime+currentTime
  }).toDouble
// println(s"timeTaken at speed of $speed is $timeTaken")
  timeTaken <= hour
}

def minSpeedOnTimeHelper(left:Int, right:Int,dist: Array[Int], hour: Double):Int={
  if(left < right){
    val mid = left + (right - left)/2
    if(minSpeedCondition(mid, hour, dist)) minSpeedOnTimeHelper(left, mid, dist, hour)
    else  minSpeedOnTimeHelper(mid+1, right,  dist, hour)
  }else {
    if(minSpeedCondition(left, hour, dist)) left else -1
  }
}

def minSpeedOnTime(dist: Array[Int], hour: Double): Int = {
 minSpeedOnTimeHelper(1,10000000, dist, hour)
}

minSpeedCondition(9999996,2.01,Array(1,1,100000))
minSpeedOnTime(Array(1,1,100000), 2.01)