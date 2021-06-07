import scala.collection.mutable

//https://leetcode.com/problems/search-in-rotated-sorted-array/
def findPivot(nums:Array[Int], left:Int, right: Int):Int={
  if(left < right){
    val mid = left+(right-left)/2
    if(nums(mid) > nums(right)) findPivot(nums, mid+1, right)
    else findPivot(nums, left, mid)
  }else left
}

/*def search(nums: Array[Int], target: Int): Int = {
  findPivot(nums, 0, nums.length-1)
}*/
val arr = Array(0,0,2,3,3,3,5,5,5,6,6,9)
arr(findPivot(arr, 0, arr.length-1 ))

//https://leetcode.com/problems/search-in-rotated-sorted-array/
def searchBSMinIdx(nums:Array[Int], left:Int, right:Int, target:Int):Int={
  if(target > nums(right)) right+1
  else {
    if (left < right) {
      val mid = left + (right - left) / 2
      if (nums(mid) >= target) searchBSMinIdx(nums, left, mid, target)
      else searchBSMinIdx(nums, mid + 1, right, target)
    } else {
      if (nums(left) == target) {
        nums.splitAt(left)._2.takeWhile(_ == target).length + left - 1
      } else left
    }
  }
}

def searchBSMaxIdx(nums:Array[Int], left:Int, right:Int, target:Int):Int={
  if(left < right){
    val mid = left + (right-left)/2
    if(nums(mid) > target) searchBSMinIdx(nums, left, mid,target)
    else searchBSMinIdx(nums, mid+1, right, target)
  } else {
    if(nums(left) == target) left else -1
  }
}

def search(nums: Array[Int], target: Int): Int = {
  val pivot = findPivot(nums, 0, nums.length-1)
  val left = searchBSMinIdx(nums, 0, pivot-1, target)
  val right = searchBSMinIdx(nums, pivot, nums.length-1, target)
//  println(s"pivot $pivot left $left right $right")
  if(left != -1) left else if(right != -1) right else -1
}
println(s"nums is ${arr.zipWithIndex.toList}")
searchBSMinIdx(arr,0, arr.length-1,12)
//searchBSMaxIdx(nums,0, nums.length-1,6)

def minNoOfJumps(nums:Array[Int]):Int={
  nums.zipWithIndex.tail.foldLeft(mutable.HashMap[Int,Int](0->0)){case (acc, ele) =>
    val prevPossiblePositions = nums.zipWithIndex.take(acc.size)
      .filter(x => x._1+x._2 >= ele._2)

      val jumpsForNextPosition = prevPossiblePositions.map(x => acc(x._2)).min
  acc.put(ele._2, jumpsForNextPosition+1)

    println(s" acc is ${acc} for index ${ele._2} and element is ${ele._1}")
    acc
  }.getOrElse(nums.length-1,0)

}


minNoOfJumps(Array(2,3,1,1,4))
