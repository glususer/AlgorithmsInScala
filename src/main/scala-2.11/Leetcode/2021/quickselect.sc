
object leetcode extends App {

  def findKthLargest(nums: Array[Int], k: Int): Int = {
    quickSelect(nums.length-k, nums.toList)
  }

  def quickSelect(k: Int, nums: List[Int]): Int = {
    val pivot = nums.head
    val (less, more) = nums.tail.partition(_ < pivot)
    if (less.length == k) pivot
    else if (k < less.length) quickSelect(k, less)
    else quickSelect(k - less.length - 1, more)
  }
}

  //println(findKthLargest(Array(8,2,4,10,14,12,6),1))
//println(findKthLargest(Array(14,12,8,2,4,10,6),2))
//println(findKthLargest(Array(14,12,8,2,4,10,6),3))
//println(findKthLargest(Array(14,12,8,2,4,10,6),4))
//println(findKthLargest(Array(14,12,8,2,4,10,6),5))
//println(findKthLargest(Array(14,12,8,2,4,10,6),6))
println(Array(2,3,2,3,3,4,1,4,1).sorted.toList)
println(leetcode.findKthLargest(Array(2,3,2,3,3,4,1,4,1),10))