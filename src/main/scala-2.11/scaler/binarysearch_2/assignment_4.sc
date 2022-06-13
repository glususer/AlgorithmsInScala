//Given a sorted array of integers A where every element appears twice except for one element which appears once,
// find and return this single element that appears only once.
//NOTE: Users are expected to solve this in O(log(N)) time.

def binarySearch(left:Int,right:Int,mid:Int,A:Array[Int]):Int={
  if(left < right){
    if(mid%2==1)binarySearch(left,right, mid-1,A)
    else if(A(mid) !=A(mid+1)) binarySearch(left,mid,((right-left)/2)+left,A)
    else binarySearch(mid+2, right,((right-left)/2)+left,A)
  }else left
}

def solve(A: Array[Int]): Int  = {
  A(binarySearch(0, A.length-1,A.length/2,A))
}
val arr = Array(1,1,2,3,3)
solve(arr)