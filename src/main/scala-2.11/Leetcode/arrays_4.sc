def makeParititions(nums:Array[Int], used:Array[Boolean], currInd:Int, currSum:Int, targetSum:Int, noOfPartition:Int):Boolean={
  if(noOfPartition == 1) true
  else if(currSum == targetSum){
    makeParititions(nums, used, 0,0, targetSum, noOfPartition-1)
  }else{
    currInd until nums.length foreach{ i =>
      if(!used(i)){
        used(i) = true
        if(makeParititions(nums,used,i+1,currSum+nums(i),targetSum,noOfPartition)) {
          println(s"it is gonna be true nums: ${nums.toList}, ")
          return true
        }
        used(i) = false
      }
    }
    false
  }
}

def canPartitionKSubsets(nums: Array[Int], k: Int): Boolean = {
  if(nums.sum%k != 0) false
  else{
    val eachPartitionSum = nums.sum/k
    makeParititions(nums, Array.fill(nums.length)(false), 0,0,eachPartitionSum,k)
  }
}

canPartitionKSubsets(Array(1,2,3,4),3)