//Given a sorted array of integers A of size N and an integer B.
//
//array A is rotated at some pivot unknown to you beforehand.
//
//(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2 ).
//
//You are given a target value B to search. If found in the array, return its index otherwise, return -1.
//
//You may assume no duplicate exists in the array.
//
//NOTE: Users are expected to solve this in O(log(N)) time.

def binarySearch(left:Int, right:Int, key:Int, array: Array[Int]):Int={
  if(left < right){
    val mid = (right-left)/2+left
    if(key <= array(mid))binarySearch(left,mid,key,array)
    else binarySearch(mid+1, right, key,array)
  }
  else left
}

def findPivot(left:Int, right:Int, arr:Array[Int]):Int={
  if(left<right){
    val mid = ((right-left)/2)+left
 //   println(s"left $left mid $mid right $right")
    if(arr(mid) < arr(right)) findPivot(left,mid,arr)
    else findPivot(mid+1, right,arr)
  }
  else left
}
def search(A: Array[Int], B: Int): Int  = {
  val pivot = findPivot(0,A.length-1,A)
  val idxInLeft = binarySearch(0,pivot,B,A)
  lazy val idxInRight = binarySearch(pivot,A.length-1,B,A)
  if (A(idxInLeft) == B) idxInLeft
  else if(A(idxInRight) == B) idxInRight
  else -1
}
val arr = Array(1)
search(arr,1)
search(arr,2)
search(arr,3)
search(arr,4)
search(arr,5)
search(arr,6)
search(arr,7)
search(arr,8)