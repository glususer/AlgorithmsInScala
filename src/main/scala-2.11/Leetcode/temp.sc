

//https://leetcode.com/problems/subarray-product-less-than-k/
//[10, 5, 2, 6]
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

//https://leetcode.com/problems/k-diff-pairs-in-an-array/
def helper(nums:Array[Int],idx:Int,numSet:Map[Int,Int], acc:Set[(Int,Int)],k:Int):Set[(Int,Int)]={
  if(idx == nums.length) acc
  else{
    val numAtDiffK = numSet.contains(nums(idx)-k) && numSet(nums(idx)-k) != idx
  //  println(nums(idx),numSet.contains(nums(idx)-k),(nums(idx),nums(idx)-k))
    lazy val pair = if (nums(idx) < nums(idx)-k)(nums(idx),nums(idx)-k) else (nums(idx)-k,nums(idx))
    if(numAtDiffK) helper(nums,idx+1,numSet,acc+pair,k)
    else helper(nums,idx+1,numSet,acc,k)
  }
}
def findPairs(nums: Array[Int], k: Int): Int = {
  val numSet = Map((nums.zipWithIndex):_*)
  helper(nums,0,numSet,Set.empty,k).size
}

findPairs(Array(-1,-2,-3),1)


//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

def maxProfit(prices: Array[Int]): Int = {
  println(s"${prices.toList.sliding(2,1).toList}")
  if(prices.length <2) 0 else prices.sliding(2,1).map(x => x(1)-x(0)).filter(_ > 0).sum
}

maxProfit(Array(1, 3, 2, 8, 4, 9))

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
def maxProfit2(prices: Array[Int], fee: Int): Int = {
  if(prices.length <2) 0 else prices.sliding(2,1).map(x => x(1)-x(0)-fee).filter(_ > 0).sum
}
val x = Array(1, 3, 2, 8, 4, 9)
val fee = 2
maxProfit2(x,fee)

x.sliding(3,1).toList

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

def maxProfit(prices: Array[Int], fee: Int): Int = {
  var bsbp = -prices(0)
  var bsp = 0
  println(s"bsbp ${bsbp} bsp ${bsp} i 0 prices ${prices(0)}")

  for(i <- 1 until prices.length){
      if(bsp - prices(i) > bsbp){
        bsbp = bsp - prices(i)
      }
    println(s"bsbp ${bsbp} bsp ${bsp}  i ${i} prices ${prices(i)}")

    if(prices(i) + bsbp - fee > bsp)
      bsp = prices(i) +bsbp -fee
  }
  if(bsp> 0) bsp else 0

}

maxProfit(Array(2,1,4,4,2,3,2,5,1,2),1)

//https://leetcode.com/problems/longest-well-performing-interval/
def longestWPI(hours: Array[Int]): Int = {
 val x =  hours.scanLeft(0,0, if(hours.head > 8)1 else 0, hours.head) ((acc,num) => if (num > 8){
   if(acc._4 < 9) (acc._1+1,0, acc._3+1,num)
   else (acc._1+1,acc._2, acc._3,num)
 }  else {
   if(acc._3 > 0) (acc._1,acc._2+1,acc._3,num) else (acc._1,acc._2+1,acc._3,num)
 }).tail
  //  println(x.toList)
   val y= x.filter{case((p,np,interval,_)) => interval > 0}
       .groupBy(_._3).mapValues(arr => (arr.toList.filter(tup => (tup._1-tup._2) > 0 && tup._2 > 0),arr.length))//.maxBy(_._2._1)
  println(y)
//y._2._2
    // if(y.nonEmpty) y.maxBy(_._1)._1 else 0
  2

}

longestWPI(Array(9,9,9,6,6,0,6,6,9))

//https://leetcode.com/problems/integer-break/
def integerBreak(n: Int): Int = {
  val dp = Array.fill(n+1)(1)

  for(i <- 3 to n){

    dp(i) = (1 until i).flatMap(num => List(num * dp(Math.abs(i-num)),num*(Math.abs(i-num)))).max
   // println((1 until i).map(num => List(num * dp(Math.abs(i-num)),num*(Math.abs(i-num)))), i)

  }
  println(dp.toList)

  dp(n)
}

integerBreak(10)

//https://leetcode.com/problems/largest-rectangle-in-histogram/
def largestRectangleArea(heights: Array[Int]): Int = {
  if(heights.isEmpty) 0 else {

    val zipped = heights.zipWithIndex
    val leftMin = zipped.map { case (ele, idx) => zipped.take(idx).reverse.find(num => num._1 < ele).getOrElse(0, Integer.MIN_VALUE) }.map(_._2)
    val rightMin = zipped.map { case (ele, idx) =>
      zipped.takeRight(zipped.length - 1 - idx).find(num => num._1 < ele).getOrElse(heights.last, Integer.MAX_VALUE)
    }.map(_._2)

    val x = (heights zip (leftMin zip rightMin)).map { case (num, (l, r)) =>
      if (r == Integer.MAX_VALUE && l == Integer.MIN_VALUE) num * heights.length
      else if (r == Integer.MAX_VALUE) {
        (heights.length - 1 - l) * num
      }
      else if (l == Integer.MIN_VALUE) if (r - 1 == 0) num else r * num
      else {
        if (r - l - 1 == 0 || r - l == 0) num else (r - l - 1) * num
      }
    }
    println(leftMin.toList)
    println(rightMin.toList)
    println(x.toList)

    x.max
  }
}

largestRectangleArea(Array(2,1,5,6,2,3))

def findDuplicate(nums: Array[Int]): Int = {
  nums.foreach{n =>
    if(nums(n.abs - 1) > 0)nums(n.abs - 1) *= -1 else nums(n.abs - 1) = nums.length }
  nums.zipWithIndex.find(x => x._1.abs == nums.length).getOrElse(0,0)._2+1
}


findDuplicate(Array(1,3,4,2,2))

val connections = Array(Array(1,2),Array(3,2),Array(2,1,4,5))

connections.find(arr => arr.head == 2).getOrElse(Array.empty).toList

List(5, 3, 9, 8, 4, 6).foldLeft(Integer.MAX_VALUE, Integer.MIN_VALUE){case (acc,ele) => if(ele < acc._1) (ele, acc._2) else if(ele > acc._2)(acc._1, ele) else(acc._1, acc._2) }

def coolPointCondition(mid:Int, arr:Array[Int]):Boolean={
  if(arr(mid) > arr(mid+1)) true
  else false
}

def coolPoint(arr:Array[Int], left:Int, right:Int):Int={
  if(left < right){
    val mid = left+(right-left)/2
    if(coolPointCondition(mid, arr)) coolPoint(arr, left,mid)
    else(coolPoint(arr,mid+1, right))
  }
  else left
}
def peakIndexInMountainArray(arr: Array[Int]): Int = {
  arr(coolPoint(arr, 0, arr.length-1))
}
val arr = Array(0,2,1,0)
peakIndexInMountainArray(arr)

List(1,2,3,4).take(5)

"FeatureProto.road_segment.direction[0].speed[1].special_speed[0]".split('.').last.foldLeft(""){case(acc, ch) => if(ch.isLetter || ch == '_')acc+ch else acc}

