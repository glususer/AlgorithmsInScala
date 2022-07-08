//Given an array A, find the next greater element G[i] for every element A[i] in the array.
//The next greater element for an element A[i] is the first greater element on the right side of A[i] in the array, A.
//
//More formally:
//
//G[i] for an element A[i] = an element A[j] such that
//    j is minimum possible AND
//    j > i AND
//    A[j] > A[i]
//Elements for which no greater element exists, consider the next greater element as -1.



def nextGreater(A: Array[Int]): Array[Int]  = {
  import scala.collection.mutable
  val greaterArray = Array.fill(A.length)(-1)
  val stack = mutable.Stack[Int]()
  stack.push(A.length-1)

  A.indices.reverse.tail.foldLeft(stack){case (stck, idx) =>
  while(stck.nonEmpty && A(stck.top)<= A(idx)) stck.pop()
    if(stck.isEmpty) greaterArray(idx) = -1
    else greaterArray(idx) = A(stck.top)
    stck.push(idx)
    stck
  }
  greaterArray
}

nextGreater(Array(3,2,1))