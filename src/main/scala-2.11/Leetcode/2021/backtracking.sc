

def helper(nums:Array[Int], acc:Set[Set[Int]], index: Int):Set[Set[Int]]={
  if(index > nums.length-1) acc
  else helper(nums,acc++ acc.map(set => set+nums(index))++Set(Set(nums(index))), index+1 )
}
def subsets(nums: Array[Int]): List[List[Int]] = {
  helper(nums, Set(Set(nums(0))), 1).map(set => set.toList).toList:+(List.empty)
}

//subsets(Array(1,2,2))

def helpersubsetsWithDup(nums:Array[Int], acc:Set[List[Int]], index: Int):Set[List[Int]]={
  if(index > nums.length-1) acc
  else helpersubsetsWithDup(nums,acc++ acc.map(lst => lst:+nums(index))++Set(List(nums(index))), index+1 )
}

def subsetsWithDup(nums: Array[Int]): List[List[Int]] = {
  scala.util.Sorting.quickSort(nums)
  helpersubsetsWithDup(nums, Set(List(nums(0))), 1).map(set => set.toList).toList:+(List.empty)
}

//subsetsWithDup(Array(4,1,4,4,4))

def helperCombinationSum2(candidates: Array[Int], target: Int, index: Int, acc: Set[List[Int]], currentSum: Int, currentSumList: List[Int], str:String): Set[List[Int]] = {
  println(s"str $str")
  if (index > candidates.length - 1 || candidates(index) + currentSum > target) acc
  else if (candidates(index) + currentSum == target) helperCombinationSum2(candidates, target, index + 1, acc + (currentSumList :+ candidates(index)), 0, List.empty, str+"i")
  else if(candidates(index) + currentSum < target){
    val including = helperCombinationSum2(candidates, target, index + 1, acc, currentSum + candidates(index), currentSumList :+ candidates(index), str+"i")
    val excluding = helperCombinationSum2(candidates, target, index + 1, acc, currentSum, currentSumList, str+"e")
    including ++ excluding
  } else acc
}

def combinationSum2(candidates: Array[Int], target: Int): List[List[Int]] = {
  scala.util.Sorting.quickSort(candidates)

  if(candidates.sum < target)List.empty
  else helperCombinationSum2(candidates, target,0, Set.empty, 0, List.empty,"").toList
}
//1,2,2,2,5

combinationSum2(Array(1,1,1,1,1),3)
