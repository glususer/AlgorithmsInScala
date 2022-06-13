//Given 2 integers A and B and an array of integers C of size N. Element C[i] represents the length of ith board.
//You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to paint 1 unit of the board.
//
//Calculate and return the minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of the board.
//NOTE:
//1. 2 painters cannot share a board to paint. That is to say, a board cannot be painted partially by one painter, and partially by another.
//2. A painter will only paint contiguous boards. This means a configuration where painter 1 paints boards 1 and 3 but not 2 is invalid.
//
//Return the ans % 10000003.

def countPainters(A:Array[Int],timeUnit:Int, mid:Long):Int={
  val (noOfPainters, _) = A.foldLeft(1,0L){case((noOfPainters, timeTakenUntilNow), ele) =>
    val currentTimeTaken = (ele*1l*timeUnit)
    if(noOfPainters < Integer.MAX_VALUE) {
      if (currentTimeTaken > mid) (Integer.MAX_VALUE, 0)
      else if (timeTakenUntilNow + currentTimeTaken > mid) (noOfPainters + 1, currentTimeTaken)
      else (noOfPainters, timeTakenUntilNow + currentTimeTaken)
    }else (noOfPainters, timeTakenUntilNow)
  }
  noOfPainters
}

def binarySearch(left:Long, right:Long, A:Array[Int], timeUnit:Int, totalNumOfPainters:Int):Long={
  if(left<right){
    val mid = ((right-left)/2)+left
    val currentPainters = countPainters(A,timeUnit, mid)
    if(currentPainters > totalNumOfPainters)binarySearch(mid+1, right, A,timeUnit, totalNumOfPainters)
    else binarySearch(left,mid,A,timeUnit, totalNumOfPainters)
  }
  else left
}
def paint(A: Int, B: Int, C: Array[Int]): Int  =
  (binarySearch(0,C.sum*1l*B, C,B,A)% 10000003).toInt

val arr = Array(185, 186, 938, 558, 655, 461, 441, 234, 902, 681)
paint(3,10,arr)
