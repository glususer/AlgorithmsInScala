val l = List(6, 7, 0, 1, 2, 3, 4)
//Array(3,3,1,3)
def findPivotInArray(l: Array[Int]): Int = {
  var left = 0
  var right = l.length - 1

  while (left < right) {
    val medium = left + (right - left) / 2
    if (l(medium) > l(right)) left = medium + 1
    else if (l(medium) == l(right)) right = right-1
    else right = medium
  }
  left
}

def searchInArray(nums: Array[Int], left: Int, right: Int, target: Int): Int = {
  (left, right) match {
    case (l, r) if l == r => if (target != nums(l)) -1 else l
    case (l, r) if (l < r) => val medium = l + (r - l) / 2
      if (target > nums(medium)) searchInArray(nums, medium + 1, right, target)
      else searchInArray(nums, left, medium, target)
    case (_, _) => -1
  }
}

//https://leetcode.com/problems/search-in-rotated-sorted-array/
def search(nums: Array[Int], target: Int): Int = {
  if (nums.length == 0) -1
  else {
    val pvtInd = findPivotInArray(nums)
    if (pvtInd == 0)
      searchInArray(nums, 0, nums.length - 1, target)
    else {
      val (l1, r1, l2, r2) = (0, pvtInd - 1, pvtInd, nums.length - 1)
      if (target >= nums(l2) && target <= nums(r2))
        searchInArray(nums, l2, r2, target)
      else if (target >= nums(l1) && target <= nums(r1))
        searchInArray(nums, l1, r1, target)
      else -1
    }
  }
}

//search(Array(1, 3), 0)

def findMin(nums: Array[Int]): Int = {
  if(nums.length == 0) nums.head
  else{
    val pvtInd = findPivotInArray(nums)
    nums(pvtInd)
  }
}
//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

def findPivot(l: Array[Int]): Int = {
  findPivotInArray(Array(3,3,1,3,3))
}

def findDuplicateInSorted(l:Array[Int]):Int = {
  def invariant(arr:Array[(Int,Int)]):Int ={
    var left = 0
    var right = arr.length-1
    while(left < right){
      val mid = left + (right-left)/2
      if(arr(mid)._1 < arr(mid)._2 ) right = mid
      else left = left+1
    }
    left
  }
  l(invariant(l.zipWithIndex))
}

//findDuplicateInSorted(Array(0,1,2,3,4,4,5,6,7,8,9))

val nums = Array(5,4,6,7,9,3,10,9,5,6)

//https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
def removeDuplicates(nums: Array[Int]): Int = {
  ???
}

//https://leetcode.com/problems/median-of-two-sorted-arrays/submissions/

def helper(num1:List[Int], num2:List[Int], acc:List[Int], count:Int):List[Int]={
  count match{
    case 0 => acc
    case _ => if(num1.nonEmpty&& num2.nonEmpty)  {
      if(num1.head > num2.head ) helper(num1, num2.tail, acc:+num2.head, count-1)
      else helper(num1.tail, num2, acc:+num1.head, count-1)
    } else if(num1.isEmpty && num2.isEmpty){
      acc
    } else if(num1.isEmpty){
      helper(num1,num2.drop(count), acc:::num2.take(count), 0)
    }else{
      helper(num1.drop(count),num2, acc:::num1.take(count),0)
    }
  }
}


def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
  val length = nums1.length + nums2.length
  val arrLength = length/2+1
  val medianArr = helper(nums1.toList,nums2.toList,List.empty,arrLength)
  println(medianArr)
  if(medianArr.isEmpty) 0
  else if(medianArr.size == 1)medianArr.head
  else{
    if(length%2 == 1) medianArr.last
    else medianArr.takeRight(2).sum*1.0/2
  }

}

findMedianSortedArrays(Array(1,2,8,10),Array(3,4,5))

//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/


def helper(left:Int, right:Int,nums:Array[Int], target:Int, min:Int,max:Int):(Int,Int)={
  if(left == right) {
    if(nums(left) == target){
      val l = List(left,right,min,max)
        .filter(x => x>=0 && x!=Integer.MAX_VALUE && x!= Integer.MIN_VALUE )
      (l.min, l.max)
    } else (-1,-1)
  }
  else{
    val middle = left + (right-left)/2
    if(nums(middle) < target) helper(middle+1,right, nums,target,min,max)
    else if (nums(middle) > target) helper(left,middle,nums,target,min,max)
    else {
      val (leftMin,leftMax) = helper(left,middle,nums,target,min,max)
      val (rightMin,rightMax) = helper(middle+1,right,nums,target,min,max)
      val l = List(leftMin,leftMax, rightMin,rightMax).filter(_>=0)
      (l.min,l.max)
    }
  }
}

def searchRange(nums: Array[Int], target: Int): Array[Int] = {
  if(nums.isEmpty)Array(-1,-1)
  else {
    val (minIdx, maxIdx) = helper(0, nums.length - 1, nums, target, Integer.MAX_VALUE, Integer.MIN_VALUE)
    Array(minIdx, maxIdx)
  }
}

val nums1 = Array(7)
val target = 8
helper(0,nums1.length-1,nums1,target,Integer.MAX_VALUE,Integer.MIN_VALUE)

//https://leetcode.com/problems/first-bad-version/
//https://towardsdatascience.com/powerful-ultimate-binary-search-template-and-many-leetcode-problems-1f850ef95651


def isBadVersion(n:Int):Boolean={
  Array(false, false, false, false,true, true)(n)
}
def helper(l:Int,r:Int,n:Int):Int={
  if(l<r){
    val mid = l+(r-l)/2
    if(isBadVersion(mid)) helper(l,mid,n)
    else helper(mid+1,r,n)
  }
  else l
}
def firstBadVersion(n: Int): Int = {
  helper(1,n,n)
}

//firstBadVersion(5)
//https://leetcode.com/problems/sqrtx/

def condition(num:Long,target:Long):Boolean={
  num*num > target
}

def helperSqrt(left:Long,right:Long,target:Long):Long={

  if(left < right){
    val mid = left + (right-left)/2
 //   println(left,mid,right,condition(mid,target))
    if(condition(mid,target)) helperSqrt(left,mid,target)
    else helperSqrt(mid+1,right,target)
  }else left-1
}
def mySqrt(x: Int): Int = {
  helperSqrt(0.toLong,(x+1.toLong),x.toLong).toInt
}

//mySqrt(2147483647)

//https://leetcode.com/problems/search-insert-position/
def searchInsert(idx:Int, target:Int,nums:Array[Int]):Boolean={
  nums(idx) >= target
}
def helperSerarchInsert(l:Int,r:Int,target:Int,nums:Array[Int]):Int={
  if(l<r){
    val mid = l + (r-l)/2
    if(searchInsert(mid,target,nums)) helperSerarchInsert(l,mid,target,nums)
    else helperSerarchInsert(mid+1,r,target,nums)
  }else {
    //println(s"l $l nums(l) ${nums(l)} ${target}")
      l
    }
}
def searchInsert(nums: Array[Int], target: Int): Int = {
  helperSerarchInsert(0,nums.length,target,nums)
}
//https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
//https://towardsdatascience.com/powerful-ultimate-binary-search-template-and-many-leetcode-problems-1f850ef95651
def canShip(target:Int, weights:Array[Int],days:Int):Boolean={
  val weightsInDays = weights.scanLeft(0,1){case(acc,weight) => if(acc._1+weight > target) (weight,acc._2+1) else(acc._1+weight,acc._2)}
 // println(target,weightsInDays.toList)
  weightsInDays.lastOption.getOrElse(0,days+1)._2 <=days

}

def helperShip(l:Int,r:Int, weights:Array[Int], days:Int):Int={
  if(l < r){
    val mid = l+(r-l)/2
    if(canShip(mid,weights,days))helperShip(l,mid,weights,days)
    else helperShip(mid+1,r,weights,days)
  }else l
}
def shipWithinDays(weights: Array[Int], D: Int): Int = {
  helperShip(weights.max,weights.sum,weights,D)
}

//shipWithinDays(Array(10,10,10),2)
//https://leetcode.com/problems/split-array-largest-sum/
def canSplit(mid: Int, nums: Array[Int], partition: Int):Boolean={
 val cumulativeSum =  nums.scanLeft(0,1){case(acc,num) => if(acc._1+num> mid) (num,acc._2+1) else (acc._1+num,acc._2)}
  cumulativeSum.lastOption.getOrElse(0,partition+1)._2 <=partition
}
def helperSplitArray(l:Int,r:Int,m:Int,nums:Array[Int]):Int={
  if(l<r){
    val mid = l+(r-l)/2
    if(canSplit(mid,nums,m)) helperSplitArray(l,mid,m,nums)
    else helperSplitArray(mid+1,r,m,nums)
  }else l
}
def splitArray(nums: Array[Int], m: Int): Int = {
  helperSplitArray(nums.max, nums.sum,m,nums)
}

//splitArray(Array(1,4,4),3)

//https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/

def foundNosSmallerThenK(num:Int,k:Int,m:Int,n:Int):Boolean={
  var count = 0
  for(i <- 1 to m){
    if(n*i <= num) count = count+n
    else count = count+(1 to n).map(num => num*i).count(_ <=num)
  }

  println(s" count of nos less than $num is $count")
  count >= k
}
def helperKthNumber(l:Int,r:Int,k:Int,m:Int,n:Int):Int={
  println(l,r)
  if(l<r){
    val mid = l + (r-l)/2
    if(foundNosSmallerThenK(mid,k,m,n))helperKthNumber(l,mid,k,m,n)
    else helperKthNumber(mid+1,r,k,m,n)
  }
  else l
}
def findKthNumber(m: Int, n: Int, k: Int): Int = {
  helperKthNumber(1,m*n,k,m,n)
}

//findKthNumber(9895,28405,100787757)

//https://leetcode.com/problems/ugly-number-iii/
def gcd (a:Long,b:Long):Long={
  if(a == 0) b
  else gcd(b%a,a)
}
def lcm(a:Long,b:Long):Long ={
  (a*b)/gcd(a,b)
}
def conditionUgly(mid:Long, n:Int, a:Long, b:Long, c:Long):Boolean={
  val currentCount = mid/a+mid/b+mid/c-mid/lcm(a,b)-mid/lcm(b,c)-mid/lcm(a,c)+mid/lcm(a,lcm(b,c))
  println(s"count at $mid is $currentCount")
  currentCount <  n
}

def findUgly(n: Int, a: Long, b: Long, c: Long,left:Long, right:Long):Long={
  if(left < right) {
    val mid = left+(right-left)/2
    println(s"mid $mid, left $left, right $right")
    if(conditionUgly(mid, n, a, b, c)) findUgly(n,a,b,c ,mid+1,right)
    else findUgly(n,a,b,c,left,mid)
  }else left
}
def nthUglyNumber(n: Int, a: Int, b: Int, c: Int): Int = {
  val l = List(a.toLong,b.toLong,c.toLong).sorted
  findUgly(n,l.head,l.tail.head,l.last,1, (2*math.pow(10,9)).toLong).toInt
}
println(s"2nd ugly  number is ${nthUglyNumber(1000000000,2,217983653,336916467)}")

def conditionPiles(piles:Array[Int], mid:Int, hours:Int):Boolean={
  piles.foldLeft(0){case(acc, ele) =>val quotient = ele/mid
    val remainder = ele%mid
    acc+(quotient+ (if(remainder > 0) 1 else 0))} <= hours
}

def binSearchPiles(piles:Array[Int], left:Int, right:Int, hours:Int):Int={
  if(left < right){
    val mid = left+(right-left)/2
    if(conditionPiles(piles, mid,hours)) binSearchPiles(piles, left,mid, hours)
    else binSearchPiles(piles, mid+1,right,hours)
  }else left
}

def minEatingSpeed(piles: Array[Int], H: Int): Int = {
  binSearchPiles(piles, 1,piles.max,H)
}

minEatingSpeed(Array(30,11,23,4,20),6)

//https://leetcode.com/problems/furthest-building-you-can-reach/

def cannotJumpFurther(heights:Array[Int], bricks:Int, ladders:Int, mid:Int):Boolean={
  println(s"here")
  val greatestDistance = heights.take(mid+1).sliding(2,1).map(arr => arr.lastOption.getOrElse(0)-arr(0)).filter(_ >0).toList.sortWith(_ > _).take(ladders)
  println(s"greatestDistance ${greatestDistance}")
  val canJump = heights.take(mid+1).foldLeft(heights.head,bricks,ladders){case(acc,ele) =>
    val diff = ele-acc._1
    val prevElement = acc._1
    if(prevElement < ele)
  {if(greatestDistance.contains(diff) && acc._3 > 0 ) (ele, acc._2, acc._3-1)
  else if (diff < acc._2) (ele,acc._2-(diff),acc._3)
  else (ele,acc._2-(diff), acc._3 )
    /*if(diff < acc._2) (ele,acc._2-(diff),acc._3)
  else if (diff > acc._2 && acc._3 > 0) (ele, acc._2, acc._3-1)
  else (ele,acc._2-(diff), acc._3 )*/}
  else{
      (ele, acc._2, acc._3)
  }}
  println(s"cannotJumpFurther on $mid is $canJump and is ${canJump._2 < 0} : arr is ${heights.take(mid+1).toList}")
  canJump._2 > 0
}

def furthestBuildingHelper(heights:Array[Int], bricks:Int, ladders:Int, left:Int,right:Int):Int={
  if(left< right) {
    val mid = left + (right-left)/2
    println(s"left $left, mid $mid, right $right")
    if(cannotJumpFurther(heights,bricks,ladders,mid)) furthestBuildingHelper(heights, bricks, ladders,mid+1,right)
    else furthestBuildingHelper(heights, bricks, ladders,left, mid)
  }else left-1
}
def furthestBuilding(heights: Array[Int], bricks: Int, ladders: Int): Int = {
  val x = furthestBuildingHelper(heights, bricks, ladders, 0, heights.length)
  if(x > 0) x else 0
}

//Array(4,12,2,7,3,18,20,3,19).sliding(2,1).toList.map(arr => arr.last-arr.head).zipWithIndex.filter(_._1>0)

//furthestBuilding(Array(7,5,13),0,0)

//https://leetcode.com/problems/longest-increasing-subsequence/
//awesome exmaple of BS - https://leetcode.com/problems/longest-increasing-subsequence/discuss/343006/Python-Binary-search-and-dp
def exists(mid: Int, dp:Array[Int], num:Int):Boolean=
  dp(mid) >= num

def binarySearchInArr(dp:Array[Int], left:Int, right:Int, num:Int):Int ={
  if(left < right){
    val mid = left + (right-left)/2
    if(exists(mid, dp,num)) binarySearchInArr(dp, left,mid,num)
    else binarySearchInArr(dp, mid+1,right,num)
  }
  else left
}

def lengthOfLISHelper(nums:Array[Int],dp:Array[Int]):Array[Int]={
  if(nums.isEmpty) dp
  else{
    val index = binarySearchInArr(dp,0,dp.length,nums.head)
    println(s"key is ${nums.head} and index is $index, dp arr is ${dp.toList}")
    if(index > dp.length-1) lengthOfLISHelper(nums.tail, dp:+nums.head)
    else {
      dp(index) = nums.head
      lengthOfLISHelper(nums.tail, dp)
    }
  }
}

def lengthOfLIS(nums: Array[Int]): Int = {
  lengthOfLISHelper(nums, Array.empty).length
}

lengthOfLIS(Array(95,96,98,100,0,5))

//binarySearchInArr(Array(2,4),0,2,3)

//https://leetcode.com/problems/maximum-length-of-repeated-subarray/
def findLengthBS(A:Array[Int], B:Array[Int], left:Int, right:Int):Int={
  if(left < right){
    val mid  = left + (right-left)/2
    println(s"left $left mid $mid right $right")
    val range = 1 until A.length-mid+1
    if(range.exists(i =>
      A.sliding(i,1).toList.exists(aList => B.intersect(aList).nonEmpty)))findLengthBS(A,B, mid+1, right)
    else findLengthBS(A,B, left, mid)

  }else left
}
def findLength(A: Array[Int], B: Array[Int]): Int = {
  findLengthBS(A,B,0, A.length)
}

//findLength(Array(1,2,3,2,1),Array(3,2,1,4,7))

//https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
def minDaysCondition(bloomDay:Array[Int],bouquets:Int,flowers:Int,mid:Int):Boolean={
  val possibleBouquets = bloomDay.map(day => if(mid >= day) 1 else 0).foldLeft((0,0)){case (acc,ele) => if(ele ==1) {
    if((acc._2+1)%flowers == 0)(acc._1+1,0) else (acc._1, acc._2+1)
  } else (acc._1,0)}

  possibleBouquets._1 >= bouquets
}
def minDaysBS(bloomDay: Array[Int], bouquets:Int, flowers:Int, left:Int,right:Int):Int={
  if(left < right){
    val mid = left +(right-left)/2
    println(s"left $left mid $mid right $right")
    if(minDaysCondition(bloomDay, bouquets, flowers,mid)) minDaysBS(bloomDay,bouquets,flowers, left,mid)
    else minDaysBS(bloomDay,bouquets,flowers, mid+1,right)
  }else left
}
def minDays(bloomDay: Array[Int], m: Int, k: Int): Int = {
  if(m*k > bloomDay.length) -1
  else{
    minDaysBS(bloomDay, m,k,0,bloomDay.max)
  }
}

//minDays(Array(1,10,3,10,2),3,1)

//https://leetcode.com/problems/find-k-closest-elements/
def findClosestElementsCondition(arr: Array[Int], k: Int, x: Int, mid:Int):Boolean={

  if(mid+k > arr.length-1) {
    false
  }
  else {
    println(s" mid $mid ${x-arr(mid)} ${arr(mid+k)-x}")
    x-arr(mid) > arr(mid+k)-x
  }
}
def findClosestElementsBS(arr: Array[Int], k: Int, x: Int, left:Int, right:Int):Int={
  if(left < right){
    val mid = left + (right-left)/2
    println(s"left $left mid $mid right $right")
    if(findClosestElementsCondition(arr, k, x, mid)) findClosestElementsBS(arr, k, x, mid+1, right)
    else findClosestElementsBS(arr, k, x, left, mid)
  } else left
}
def findClosestElements(arr: Array[Int], k: Int, x: Int): List[Int] = {
  val startingIdx = findClosestElementsBS(arr, k, x, 0, arr.length-1)
  arr.slice(startingIdx, startingIdx+k).toList
}

//findClosestElements(Array(-2,-1,1,2,3,4,5), 3, 3)

//https://leetcode.com/problems/find-k-th-smallest-pair-distance/
def indexOfNumBS(nums:Array[Int],target:Int,left:Int,right:Int):Int= {
  if(target > nums(right)) right+1
  else {
    if (left < right) {
      val mid = left + (right - left) / 2
      if (nums(mid) >= target) indexOfNumBS(nums, target,left, mid)
      else indexOfNumBS(nums, target,mid + 1, right)
    } else {
      if (nums(left) == target) {
        nums.splitAt(left)._2.takeWhile(_ == target).length + left - 1
      } else left
    }
  }
}
def firstNumGreaterThenMid(nums:Array[Int], idx:Int, diff:Int):Int={
  val idxGreaterThanK = indexOfNumBS(nums,nums(idx)+diff,if(idx+1 < nums.length) idx+1 else nums.length-1,nums.length-1)
  if(idxGreaterThanK > nums.length-1) {
    nums.length-1-idx
  } else{
    if(nums(idxGreaterThanK) == nums(idx)+diff) idxGreaterThanK-idx
    else idxGreaterThanK-idx-1
  }
}
def countPairs(nums:Array[Int], mid:Int):Int=
  nums.zipWithIndex.map{case (_,idx) => firstNumGreaterThenMid(nums, idx, mid)}.sum

def smallestDistancePairBS(nums:Array[Int], left:Int, right:Int, k:Int):Int={
  if(left < right){
    val mid = left + (right-left)/2
    if(countPairs(nums, mid) < k)smallestDistancePairBS(nums, mid+1,right,k)
    else smallestDistancePairBS(nums, left, mid,k)
  } else left
}
def smallestDistancePair(nums: Array[Int], k: Int): Int = {
  val x = nums.sorted
  val maxDiff = x.last - x.head
  smallestDistancePairBS(x, 0, maxDiff,k)
}
val arr = Array(1,3,1)
//smallestDistancePair(arr,1)







