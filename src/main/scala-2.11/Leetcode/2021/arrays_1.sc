//https://leetcode.com/problems/frequency-of-the-most-frequent-element/
def maxFrequency(nums: Array[Int], k: Int): Int = {
  scala.util.Sorting.quickSort(nums)
  nums.zipWithIndex.foldLeft(1){case(maxFreq, (ele, idx)) =>
  val currentFreq = (idx-1 to 0 by -1).foldLeft(k, 1) { case ((remainingK, freq), innerIdx) =>
    val requiredk = ele - nums(innerIdx)
    if (remainingK >= requiredk) (remainingK - requiredk, freq + 1)
    else (remainingK, freq)
  }

    math.max(currentFreq._2, maxFreq)
  }
}

maxFrequency(Array(3,9,6),2)

def binarySearch(nums:Array[Int], left:Int, right:Int, k:Int, currentIdx:Int):Int={
  println(s"nums ${nums.toList} left $left, right $right, currentIdx $currentIdx currentElement ${nums(currentIdx) - nums(currentIdx-1)}")
  val currentElement = nums(currentIdx) - nums(currentIdx-1)
  if(left < right){
    val mid = (right-left)/2+left
    val freq = right - left+1
    val freqSum = freq*currentElement
    if(freqSum <= nums(currentIdx-1)+k)binarySearch(nums, left, mid, k, currentIdx)
    else binarySearch(nums, mid+1, right, k, currentIdx)
  }else {
    if (currentIdx - left == 1){
      if(nums(left)+k >= nums(currentIdx)) 2 else 1
    }
    else currentIdx - left+1
  }

}

def maxFrequency1(nums: Array[Int], k: Int): Int = {
  scala.util.Sorting.quickSort(nums)
  val prefixSum = nums.scanLeft(0){_+_}
  val freqArray  = prefixSum.zipWithIndex.tail.scanLeft(1){case(maxFreq,(_,idx)) =>
    if(idx < nums.length) {
      val currentFreq = binarySearch(prefixSum.tail, 0, idx - 1, k, idx)
       println(s" currentFreq $currentFreq for num ${nums(idx)} idx $idx")
      if (currentFreq > maxFreq) currentFreq else maxFreq
    } else maxFreq
  }
  println(freqArray.tail.toList)
  if(freqArray.forall(_ == 1)) 1 else freqArray.last
}

/*maxFrequency1(Array(1,4,8,13),5)
maxFrequency1(Array(3,9,6),2)*/
maxFrequency1(Array(1,2,4),5)