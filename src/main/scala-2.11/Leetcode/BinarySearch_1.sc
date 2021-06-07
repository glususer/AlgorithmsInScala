//https://leetcode.com/problems/find-smallest-letter-greater-than-target/
def helper(left:Int, right:Int, letters:Array[Char], key:Char):Int={
  if(left < right){
    val mid = left +(right-left)/2
    if(letters(mid).toInt > key.toInt)helper(left,mid, letters,key)
    else helper(mid+1,right,letters,key)
  }else {
    if(left == letters.length) 0
    else left
  }
}

def nextGreatestLetter(letters: Array[Char], target: Char): Char = {
 val index = helper(0,letters.length,letters,target)
  letters(index)
}

nextGreatestLetter(Array('c','f','j'),'d')
//https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/
def currentSum1(start:Int, stop:Int , key:Long):Long={
  val totalPostionsToBeFilled = stop-start+1
  val currentSum = (key * (key+1))/2
  val remainingOnes = math.abs(totalPostionsToBeFilled-key)
//println(s"totalPostionsToBeFilled $totalPostionsToBeFilled,currentSum $currentSum,remainingOnes $remainingOnes  ")
  if(totalPostionsToBeFilled == key)  currentSum
  else if(totalPostionsToBeFilled > key) currentSum+remainingOnes
  else {
    val diff = ((key-totalPostionsToBeFilled)*(key-totalPostionsToBeFilled+1))/2
    currentSum - diff
  }
}
/*def currentSum(idx:Int, keyIndex:Int, key:Int, total:Long, n:Int):Long={
  if(idx < 0 || idx > n-1 ) total
  else{
    val toBeFilled = key-math.abs(keyIndex-idx)
    val nextIdx = if(idx < keyIndex) idx -1 else idx+1
    currentSum(nextIdx, keyIndex, key, total+{if (toBeFilled > 0 )toBeFilled else 1}, n)
  }
}*/

def maxSumCondition(mid: Int, n: Int, maxSum: Long, index: Int):Boolean = {
  val sumRight = currentSum1(index+1, n-1, mid-1)
  val sumLeft = currentSum1(0, index-1, mid-1)
 println(s" sumRight is ${sumRight} and sumLeft is ${sumLeft} and total sum is ${sumRight+sumLeft+mid}, mid is $mid")
   (sumRight+sumLeft+mid <= maxSum)
}

def binarySearch(left:Int, right:Int, n:Int, maxSum:Int, index:Int):Int={
  println(s"left is $left, right is $right")
  if(left < right){
    val mid = left +(right-left)/2
    if(maxSumCondition(mid, n, maxSum, index))binarySearch(mid+1, right, n,maxSum,index)
    else binarySearch(left, mid, n, maxSum, index)

  }else {
    if(maxSumCondition(left,n, maxSum, index)) left
    else left-1
  }
}
def maxValue(n: Int, index: Int, maxSum: Int): Int = {
 binarySearch(1,maxSum+1-n,n,maxSum, index)
}
maxValue(4,0,4)
//maxSumCondition(3,6,10,1)
//155230825

//def currentSum1(start:Int, stop:Int ,startSum:Int, key:Int, total:Long):Long={
//currentSum1(2,4,4,4)

def findPeakElementHelper(nums:Array[Int], left:Int, right:Int):Int={
 // println(s"left is $left and right is $right")
  if(left<right){
    val mid = left +(right-left)/2
    if(nums(mid)<nums(mid+1))findPeakElementHelper(nums, mid+1, right)
    else findPeakElementHelper(nums, left,mid)
  }else left
}
def findPeakElement(nums: Array[Int]): Int = {
  findPeakElementHelper(nums, 0, nums.length-1)
}
val nums = Array(0,2,3,4,5,6)
val x = findPeakElement(nums)
println(s"Peak element is ${nums(x)}")


