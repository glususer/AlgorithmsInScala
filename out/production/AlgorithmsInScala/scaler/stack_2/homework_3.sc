//Given an integer array A of size N. You have to generate it's all subarrays having a size greater than 1.
//
//Then for each subarray, find Bitwise XOR of its maximum and second maximum element.
//
//Find and return the maximum value of XOR among all subarrays.

def solve(A: Array[Int]): Int  = {
  import scala.collection.mutable
  val stack = mutable.Stack[Int]()
  stack.push(A(0))
  A.indices.tail.foldLeft(stack,Integer.MIN_VALUE){case ((stck,result),i) =>
    var currentMax = Integer.MIN_VALUE
    while(stck.nonEmpty && stck.top <= A(i)){
      currentMax = currentMax max stck.top ^ A(i)
      stck.pop()
    }
    if(stack.nonEmpty) currentMax = currentMax max stck.top ^ A(i)
    stck.push(A(i))
    (stck, currentMax max result)
  }._2
}

solve(Array(2,3,1,4))