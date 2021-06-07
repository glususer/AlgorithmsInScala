//https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
def binarySearch(ints: Array[Int], key: Int, left:Int, right:Int, target:Int):Int={
  if(key*2 > target) -1
  else {
    if (left < right) {
      val mid = left + (right - left) / 2
      if (ints(mid) + key > target) binarySearch(ints, key, left, mid, target)
      else binarySearch(ints, key, mid + 1, right, target)
    }
    else {
      if (key + ints(left) <= target) left
      else left - 1
    }
  }
}
def numSubseq(nums: Array[Int], target: Int): Int = {
  val mod = 1e9.toInt + 7
  val end = nums.length - 1
  val pows = Array.fill(end + 1)(1)

  pows.zipWithIndex.tail.foreach { case (_, idx) => pows(idx) = pows(idx - 1) * 2 % mod }

  scala.util.Sorting.quickSort(nums)
  nums.zipWithIndex.foldLeft(0) { case (acc, (ele,idx)) =>
    val idxOfNumWhereConditionFails = binarySearch(nums, ele, idx, end, target)
    val currentResult = if (idxOfNumWhereConditionFails == -1) 0 else pows(idxOfNumWhereConditionFails - idx) % mod
    (acc + currentResult) % mod
  }
}
//binarySearch(Array(3,4,5,7,7,7,10),3,0,5,12)
numSubseq(Array(14,4,6,6,20,8,5,6,8,12,6,10,14,9,17,16,9,7,14,11,14,15,13,11,10,18,13,17,17,14,17,7,9,5,10,13,8,5,18,20,7,5,5,15,19,14),22)
//numSubseq(Array(3,4,5,7,7,7,10), 12)

def trap(height: Array[Int]): Int = {
  height.zipWithIndex.foldLeft(Integer.MIN_VALUE, Integer.MIN_VALUE, 0,0,height.length-1){case ((leftMax, rightMax, totalVol, leftIdx, rightIdx), ele) =>
  if(leftIdx<=rightIdx){
    val currentIdx = ele._2
    val currentLeftMax = math.max(leftMax, height(leftIdx))
    val currentRightMax = math.max(rightMax, height(rightIdx))
    if(currentLeftMax < currentRightMax){
      val currentVol = currentLeftMax - ele._1
      println(s"currentLeftMax $currentLeftMax, currentRightMax $currentRightMax, ele $ele, leftIdx $leftIdx rightIdx $rightIdx, currentVol $currentVol")
      (currentLeftMax, currentRightMax, totalVol+currentVol, leftIdx+1, rightIdx)
    }else{
      val currentVol = currentRightMax- ele._1
      println(s"currentLeftMax $currentLeftMax, currentRightMax $currentRightMax, ele $ele, leftIdx $leftIdx rightIdx $rightIdx, currentVol $currentVol")

      (currentLeftMax, currentRightMax, totalVol+currentVol, leftIdx, rightIdx-1)
    }
  }else{
    (leftMax, rightMax, totalVol, leftIdx, rightIdx)
  }
  }._3
}

//trap(Array(2,0,6,4,2,4))

//https://leetcode.com/problems/product-of-array-except-self/
def productExceptSelf(nums: Array[Int]): Array[Int] = {
 val product =  nums.foldLeft(1){case(acc, ele) => if(ele != 0) acc*ele else acc}
nums.zipWithIndex.foldLeft(product){case(acc, ele) =>
  if(ele._1 !=0) nums(ele._2) = acc/ele._1 else nums(ele._2) = product
acc}
  nums
}

productExceptSelf(Array(-1,1,0,-3,3)).toList