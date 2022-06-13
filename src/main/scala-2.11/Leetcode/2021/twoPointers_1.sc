import scala.collection.mutable

def threeSum(nums: Array[Int]): List[List[Int]] = {
  if(nums.length <3) List.empty
  else {
    scala.util.Sorting.quickSort(nums)
    //println(s"nums ${nums.toList}")
    var i = 0
    val set = mutable.Set[List[Int]]()
    while (i < nums.length - 1) {
      var left = i + 1
      var right = nums.length - 1
      while (left < right) {
        //  println(s"i $i left $left right $right set $set sum ${nums(i) + nums(left) + nums(right)}")
        if (nums(i) + nums(left) + nums(right) > 0) right = right - 1
        else if (nums(i) + nums(left) + nums(right) < 0) left = left + 1
        else {
          val current = List(nums(i), nums(left), nums(right)).sorted
          if (!set.contains(current)) set += current
          left = left + 1
          right = right - 1
        }
      }
      i = i + 1
    }
    set.toList
  }
}
threeSum(Array(-1,0,1,2,-1,-4))