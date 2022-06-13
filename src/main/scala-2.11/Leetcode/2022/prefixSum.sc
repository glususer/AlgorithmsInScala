def findMiddleIndex(nums: Array[Int]): Int = {
  val left = nums.scanLeft(0){case (acc,ele) => acc+ele}.tail
  val right = nums.scanRight(0){case (ele,acc) => acc+ele}.dropRight(0)
  left.zip(right).zipWithIndex.find{case ((l,r),_) => l == r}.map(_._2).getOrElse(-1)
}

findMiddleIndex(Array(2,5))

def getSumAbsoluteDifferences(nums: Array[Int]): Array[Int] = {
val prefixSum = nums.scanLeft(0){case (acc,ele) => acc+ele}.tail
  val suffixSUm = nums.scanRight(0){case (ele,acc) => acc+ele}.dropRight(0)

  prefixSum.zip(suffixSUm).foldLeft(0){case (idx, (prefix,suffix))=>
    val sumBefore = idx*nums(idx) - (prefix - nums(idx))
    val sumAfter = suffix-nums(idx) - nums(idx)*(nums.length-1-idx)
    nums(idx) = sumAfter+sumBefore
    idx+1
  }
  nums
}
val nums = Array(2,3,5)
getSumAbsoluteDifferences(nums)
println(s"nums ${nums.toList}")