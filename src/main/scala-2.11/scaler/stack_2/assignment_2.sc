//Given an array of integers A.
//
//A represents a histogram i.e A[i] denotes the height of the ith histogram's bar. Width of each bar is 1.
//
//Find the area of the largest rectangle formed by the histogram.
def nearestSmallerOnLeft(A: Array[Int]): Array[Int]  = {
  import scala.collection.mutable
  val smallerArray = Array.fill(A.length)(-1)
  val stack = mutable.Stack[Int]()
  stack.push(0)

  A.indices.tail.foldLeft(stack) { case (stck, idx) =>
    while(stck.nonEmpty && A(stck.top)>=A(idx))
      stck.pop()
    if(stck.nonEmpty) smallerArray(idx) = stck.top
    stck.push(idx)
    stck
  }
  smallerArray
}

def nearestSmallerOnRight(A: Array[Int]): Array[Int]  = {
  import scala.collection.mutable
  val smallerArray = Array.fill(A.length)(A.length)
  val stack = mutable.Stack[Int]()
  stack.push(A.length-1)

  A.indices.reverse.tail.foldLeft(stack) { case (stck, idx) =>
    while(stck.nonEmpty && A(stck.top)>=A(idx))
      stck.pop()
    if(stck.nonEmpty) smallerArray(idx) = stck.top
    stck.push(idx)
    stck
  }
  smallerArray
}


def largestRectangleArea(A: Array[Int]): Int  = {
  val smallerOnLeft = nearestSmallerOnLeft(A)
  val smallerOnRight = nearestSmallerOnRight(A)

  val areas = A.indices.map{i => val left = smallerOnLeft(i)
    val right = smallerOnRight(i)
    val areaOfRect = A(i)*(right-left-1)
    areaOfRect
  }

  /*println(s"smallerOnLeft ${smallerOnLeft.toList}" )
    println( s"smallerOnRight ${smallerOnRight.toList}")
  println(s"areas ${areas.toList}")*/
  if(areas.nonEmpty) areas.max
  else 0
}

largestRectangleArea(Array(2))