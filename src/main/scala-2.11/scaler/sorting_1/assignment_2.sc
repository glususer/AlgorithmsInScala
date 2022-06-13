import org.apache.spark.sql.sources.LessThanOrEqual
//Find the Bth smallest element in given array A .
// List(4,2,9,0,1) B = 2

import scala.util.Random


def quickSelect(nums:Array[Int],k:Int):Int={
 // val pivot = nums(Random.nextInt(nums.size))
  val pivot = nums.head

  val (less,more) = nums.partition(_ < pivot)
  println(s"pivot $pivot, less ${less.toList} more ${more.toList} k $k")
  if(less.length == k-1) pivot
  else if(k < less.length)quickSelect(less,k)
  else quickSelect(more,k-less.length-1)
}

def helper(A:Array[Int], k:Int,i:Int = 0):Int={
  // println(s"i $i arr ${A.toList}")
  if(i==k && i > 0)A(i-1)
  else{
    val minimiumIdx = ( i until A.length).foldLeft(i){case (acc, idx) =>
      if(A(idx) < A(acc)) idx
      else acc
    }
    val temp = A(i)
    A(i) = A(minimiumIdx)
    A(minimiumIdx) = temp
    // println(s"minimiumIdx $minimiumIdx A ${A.toList}")
    helper(A, k, i+1)
  }
}

def kthsmallest(A: Array[Int], B: Int): Int  = {
  //quickSelect(A,B)
  helper(A, B)
}

val arr1 = Array(8, 16, 80, 55, 32, 8, 38, 40, 65, 18, 15, 45, 50, 38, 54, 52, 23, 74, 81, 42, 28, 16, 66, 35, 91, 36, 44, 9, 85, 58, 59, 49, 75, 20, 87, 60, 17, 11, 39, 62, 20, 17, 46, 26, 81, 92)
kthsmallest( arr1,9)

arr1.sorted.toList.zip(1 to arr1.length)




val arr = Array(4,3,3,2,2,2,1,1)
helper(arr,1)
helper(arr,2)
helper(arr,3)
helper(arr,4)
helper(arr,5)
helper(arr,6)
helper(arr,7)
helper(arr,8)




