//You are given an array A of N elements. You have to make all elements unique. To do so, in one step you can increase any number by one.
//
//Find the minimum number of steps.
def solve(A: Array[Int]): Int  = {
  scala.util.Sorting.quickSort(A)
  A.tail.foldLeft(0,A.head){case ((steps, prevEle), currEle) =>
  if(prevEle < currEle) (steps, currEle)
  else if (prevEle == currEle) (steps+1, currEle+1)
  else (steps+(prevEle+1)-currEle, prevEle+1)
  }._1
}
solve(Array(1 ,4, 4, 1, 2 ,2))