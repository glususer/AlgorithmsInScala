//Given an array of integers A, find and return the peak element in it.
// An array element is peak if it is NOT smaller than its neighbors.
//
//For corner elements, we need to consider only one neighbor.
// We ensure that answer will be unique.
//
//NOTE: Users are expected to solve this in O(log(N)) time.
def findPeak(left:Int, right:Int, arr:Array[Int]):Int={
  if(left<right){
    val mid = ((right-left)/2)+left
    if(arr(mid) < arr(mid+1)) findPeak(mid+1, right,arr)
    else findPeak(left,mid,arr)
  }else {
    if(left < arr.length)left else left-1
  }
}

def solve(A: Array[Int]): Int  = {
  A(findPeak(0, A.length-1,A))
}

solve(Array(1,2,3,4,5))
