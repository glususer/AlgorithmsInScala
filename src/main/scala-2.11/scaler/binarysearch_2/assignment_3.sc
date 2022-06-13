//Given a sorted array of integers A(0 based index) of size N, find the starting and the ending position of a given integer B in array A.
//
//Your algorithm's runtime complexity must be in the order of O(log n).
//
//Return an array of size 2, such that the first element = starting position of B in A and the second element = ending position of B in A, if B is not found in A return [-1, -1].
def binarySearchStart(left:Int, right:Int,A:Array[Int],key:Int):Int={
  if(left < right){
    val mid = ((right-left)/2)+left
    if(A(mid) < key) binarySearchStart(mid+1, right,A,key)
    else binarySearchStart(left,mid,A,key)
  } else left
}

def binarySearchEnd(left:Int, right:Int,A:Array[Int],key:Int):Int={
  if(left < right){
    val mid = ((right-left)/2)+left
    if(A(mid) <= key) binarySearchEnd(mid+1, right,A,key)
    else binarySearchEnd(left,mid,A,key)
  } else left-1
}

def searchRange(A: Array[Int], B: Int): Array[Int]  = {
  val start = binarySearchStart(0, A.length,A,B)
  val end = binarySearchEnd(0, A.length,A,B)
  Array(if( start < A.length && start >=0 && A(start) == B) start else -1, if(end < A.length && end >=0 && A(end) == B) end else -1)
}
searchRange(Array(1,2,2,2,2,2,3,4,4,4),2)