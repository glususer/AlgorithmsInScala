//Given an array A, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.
//
//More formally,
//
//G[i] for an element A[i] = an element A[j] such that
//
//j is maximum possible AND
//
//j < i AND
//
//A[j] < A[i]
//
//Elements for which no smaller element exist, consider the next smaller element as -1.
//[4, 5, 2, 10, 8] ans= [-1, 4, -1, 2, 2]
def prevSmaller(A: Array[Int]): Array[Int]  = {
  import scala.collection.mutable
  val smallerArray = Array.fill(A.length)(-1)
  val stack = mutable.Stack[Int]()
  stack.push(0)

  A.indices.tail.foldLeft(stack) { case (stck, idx) =>
    while(stck.nonEmpty && A(stck.top)>=A(idx))
      stck.pop()
    if(stck.isEmpty) smallerArray(idx) = -1
    else smallerArray(idx) = A(stck.top)
    stck.push(idx)
    stck
  }
  smallerArray
  }

println(s"smaller ${prevSmaller(Array(3, 2, 1)).toList}")
