//Given an array of integers A, find the number of sextuplets that satisfy the equation ( (a * b + c) / d ) - e = f.
//
//Where a, b, c, d, e, f belong to the given array A.
//
//NOTE: Since the answer can be very large, return the number of ways modulo (109 + 7).


def startIdx(A:Array[Int], left:Int, right:Int,key:Int):Int= {
  if (left < right) {
    val mid = ((right - left) / 2) + left
    if (A(mid) < key) startIdx(A, mid + 1, right, key)
    else startIdx(A, left, mid, key)
  } else {
    if (left < A.length && A(left) == key) left else -1
  }
}

def endIdx(A:Array[Int], left:Int, right:Int,key:Int):Int={
  if(left<right){
    val mid = ((right-left)/2)+left
    if(A(mid)<=key) endIdx(A, mid+1, right,key)
    else endIdx(A, left,mid,key)
  }else {
    if(left > 0 && A(left-1) == key)left-1 else -1
  }
}

def solve(A: Array[Int]): Int  = {
  val rhs = (for{
    i<-A.indices
    j<-A.indices
    k<- A.indices
  }yield (if(A(i) !=0) A(i)*(A(j)+A(k)) else Integer.MAX_VALUE )).filterNot(_ == Integer.MAX_VALUE).toArray

  scala.util.Sorting.quickSort(rhs)

  val lhs =  (for{
    i<-A.indices
    j<-A.indices
    k<- A.indices
  }yield A(i)+A(j)*A(k)).toArray

  lhs.foldLeft(0){case (count, lhsEle)  =>
    val end = endIdx(rhs,0,  rhs.length,lhsEle)
    val start = startIdx(rhs, 0, rhs.length, lhsEle)
    val countOfSolInRHS = {if(end != -1) (end-start)+1 else 0}
    (countOfSolInRHS+count)%1000000009
  }
}
solve(Array(13, -25, 9, -27, -5, -23, 19, 1, -21, 25, 15, -29, -3, 21, 3, 23, -15, -17, -1, 0, -19, -11, -9, -7, 5, 17, -13, 11, 27, 7))