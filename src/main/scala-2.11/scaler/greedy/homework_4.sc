//N Mice and N holes are placed in a straight line. Each hole can accommodate only one mouse.
//
//The positions of Mice are denoted by array A, and the position of holes is denoted by array B.
//
//A mouse can stay at his position, move one step right from x to x + 1, or move one step left from x to x − 1. Any of these moves consume 1 minute.
//
//Assign mice to holes so that the time when the last mouse gets inside a hole is minimized.

def mice(A: Array[Int], B: Array[Int]): Int  = {
  scala.util.Sorting.quickSort(A)
  scala.util.Sorting.quickSort(B)

  A.zip(B).foldLeft(Integer.MIN_VALUE){case (time, (micePosition, holePosition)) =>

  val currentTime = math.abs(holePosition-micePosition)
    currentTime max time
  }
}
mice(Array(-2),Array(-6))