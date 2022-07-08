import org.apache.avro.generic.GenericData
//Given a 2D binary matrix of integers A containing 0's and 1's of size N x M.
//
//Find the largest rectangle containing only 1's and return its area.
//
//Note: Rows are numbered from top to bottom and columns are numbered from left to right
//https://leetcode.com/problems/maximal-rectangle/discuss/872089/Easy-C%2B%2B-soln-based-on-Largest-Histogram
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
  if(areas.nonEmpty) areas.max
  else 0
}


def solve(A: Array[Array[Int]]): Int  = {
  if(A.length == 0) 0
  else{
    val histogram = Array.fill(A(0).length)(0)
    var result = Integer.MIN_VALUE
    for{i<- A.indices}{
      for{j<-A(0).indices}{
        if(A(i)(j) == 1) histogram(j) = histogram(j)+1
        else histogram(j) = 0
      }
      val currentArea = largestRectangleArea(histogram)
      result =  result max currentArea
      println(s"histogram ${histogram.toList} currentArea $result")
    }
    result
  }
}
//largestRectangleArea(Array(0, 1, 1, 1, 3, 0, 1, 0, 1))
val arr = Array(Array(0, 1, 1),Array(1, 0, 0) ,Array(1, 0, 0),Array(1, 0, 0) ,Array(1, 0, 0) ,Array(1, 1, 1), Array(0, 1, 0))
val arr2 = Array(Array(1, 0, 0, 0, 1, 0, 1, 0, 0),Array(0, 0, 0, 0, 1, 1, 0, 0, 0),Array(0, 1, 1, 1, 1, 0, 1, 0, 1))
solve(arr2)

