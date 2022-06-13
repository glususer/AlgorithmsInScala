
def findLonely(nums: Array[Int]): List[Int] = {
val numsVsFreq = nums.groupBy(identity).map{case(num, lst) => (num, lst.length)}

  nums.filter{num => val freq = numsVsFreq.getOrElse(num ,0)
  freq ==1 && !numsVsFreq.contains(num-1) && !numsVsFreq.contains(num+1)}.toList
}

findLonely(Array(5,6,8,10))