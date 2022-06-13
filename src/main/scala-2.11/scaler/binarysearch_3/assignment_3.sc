//Given an array of integers A and an integer B, find and return the maximum value K such that there is no subarray in A of size K with the sum of elements greater than B.
// majorr overflow issue with maxSumOfSubArrayOfLengthK
def NoArrayOfLengthKWithSumGreaterThanB(A:Array[Int], k:Int,B:Int):Boolean= {
  val intialSum = A.take(k).sum
  val intialBool = intialSum <= B
  if(!intialBool) intialBool
  else {
    val (_, hasSomeSubArrayWithSumGreaterThanK) = A.zipWithIndex.drop(k).foldLeft(A.take(k).sum, intialBool) { case ((intialSum, isSumLessThanOrEqualToB), (ele, idx)) =>
      val currentSum = intialSum + ele - A(idx - k)
      // println(s"currentSum $currentSum ele $ele prevSum $intialSum isSumLessThanOrEqualToB $isSumLessThanOrEqualToB")
      if (isSumLessThanOrEqualToB) {
        //   println(s"here")
        if (currentSum > B) (currentSum, false)
        else (currentSum, true)
      } else (currentSum, isSumLessThanOrEqualToB)
    }
    hasSomeSubArrayWithSumGreaterThanK
  }
}

def maxSumOfSubArrayOfLengthK(A:Array[Int],k:Int):Int={
  val  (_,maxSum) = A.zipWithIndex.drop(k).foldLeft(A.take(k).sum,A.take(k).sum) { case ((prevSum,maxSum), (ele, idx)) =>
    val currentSum = prevSum + ele - A(idx - k)
      if (currentSum > maxSum) (currentSum, currentSum)
      else (currentSum, maxSum)
  }
  maxSum
}

def maxSumOfSubArrayOfLengthKAlternate(A:Array[Int],k:Int):Long={
  var maxSum = A.take(k).foldLeft(0L){case (acc, ele) => acc+ele}
  var prevSum = A.take(k).foldLeft(0L){case (acc, ele) => acc+ele}

  for(i<- k until A.length){
    val currentSum = prevSum+A(i)-A(i-k)
    if(currentSum > maxSum) maxSum = currentSum
    prevSum = currentSum
  }
  maxSum
}

def binarySearchForK(left:Int, right:Int, targetSum:Int,arr:Array[Int]):Int={
  if(left<right){
    val mid = ((right-left)/2)+left
    val currentSumWithArrayLengthMid = maxSumOfSubArrayOfLengthKAlternate(arr,mid)
    val currentSumWithArrayLengthMidPlusOne = maxSumOfSubArrayOfLengthKAlternate(arr,mid+1)
    if(currentSumWithArrayLengthMidPlusOne > targetSum && currentSumWithArrayLengthMid <= targetSum) mid
    else if(currentSumWithArrayLengthMid > targetSum)binarySearchForK(left, mid, targetSum,arr)
    else binarySearchForK(mid+1,right,targetSum,arr)
  }
  else left
}
def solve(A: Array[Int], B: Int): Int  = {
  binarySearchForK(0,A.length,B,A)
}