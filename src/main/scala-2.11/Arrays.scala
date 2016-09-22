/**
  * Created by shivangi on 02/09/16.
  */
object Arrays {

  def activityTime(start:Array[Int],finish:Array[Int]):Int={
   ???
  }

  def twoSum(l:Array[Int],target:Int):(Int,Int)= {
    var (i, j) = (0, l.length - 1)
    while (i < j) {
      if (l(i) + l(j) == target) return (i, j)
      else if (l(i) + l(j) > target) j -= 1
      else i += 1
    }
    (0,0)
  }

  def binSearch(l:Array[Int]):Int={
    ???
  }

  def stockPrices(l:Array[Int]):(Int,Int)={
    val len = l.length
    var (min,max)=(l.last,l.last)
    for (i<-len-2 to 0 by -1){
      if(min > l(i)) min = l(i)
      if(max < l(i) ) max = l(i)
    }
    (min,max)
  }

  /**
    * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
    * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
    * Find the minimum element in O(log n) time complexity. The array may contain duplicates.
    */
  def minInSortedArray(l:Array[Int]):Int={
???
  }
}
