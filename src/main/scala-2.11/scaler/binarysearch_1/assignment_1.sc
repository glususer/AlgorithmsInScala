//Sorted Insert Position
//Given a sorted array A of size N and a target value B, return the index (0-based indexing) if the target is found.
//If not, return the index where it would be if it were inserted in order.
//
//NOTE: You may assume no duplicates in the array. Users are expected to solve this in O(log(N)) time

def binarySearch(left:Int, right:Int, key:Int, array: Array[Int]):Int={
  if(left < right){
    val mid = (right-left)/2+left
    if(key <= array(mid))binarySearch(left,mid,key,array)
    else binarySearch(mid+1, right, key,array)
  }
  else left
}
def searchInsert(A: Array[Int], B: Int): Int  = {
  binarySearch(0, A.length,B,A)
}
val arr = Array(1,3,5,7,9)
binarySearch(0,arr.length,5,arr)
binarySearch(0,arr.length,10,arr)
binarySearch(0,arr.length,0,arr)
binarySearch(0,arr.length,6,arr)