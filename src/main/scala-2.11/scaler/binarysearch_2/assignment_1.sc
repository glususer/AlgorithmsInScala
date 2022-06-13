//Given an integer A.
//
//Compute and return the square root of A.
//
//If A is not a perfect square, return floor(sqrt(A)).
//
//DO NOT USE SQRT FUNCTION FROM THE STANDARD LIBRARY.
//
//NOTE: Do not use the sqrt function from the standard library. Users are expected to solve this in O(log(A)) time.
def helper(left:Long, right:Long,key:Int):Long={
  if(left<right){
    val mid=  ((right-left)/2)+left
    if(mid*mid >= key) helper(left,mid, key)
    else helper(mid+1, right, key)
  }else {
    if(left*left > key)left-1 else left
  }

}

def sqrt(A: Int): Int  = {
  helper(0l,100000l,A).toInt
}
sqrt(14)