//2,0,1,3


def solve(A: Array[Int]): Int  = {
  val posMap = collection.mutable.Map(A.zipWithIndex: _*)

 val swaps =  A.foldLeft(0,0,0){case ((sortedTillIdx, currentIdx,noOfSwaps),ele) =>
  //  println(s"A ${A.toList},sortedTillIdx $sortedTillIdx, currentIdx $currentIdx, noOfSwaps $noOfSwaps  posmap ${posMap}")
  if(currentIdx == ele) (sortedTillIdx+1, currentIdx+1, noOfSwaps)
  else{
    val firstIndexOutOfOrder = posMap.getOrElse(currentIdx,0)
  // println(s"sortedTillIdx $sortedTillIdx,currentIdx $currentIdx, noOfSwaps $noOfSwaps, outOfOrdeer $firstIndexOutOfOrder ")
    val temp = A(firstIndexOutOfOrder)
    A(firstIndexOutOfOrder) = ele
    A(currentIdx) = temp
    posMap.update(ele,firstIndexOutOfOrder)
    posMap.update(currentIdx, currentIdx)

    (sortedTillIdx+1, currentIdx+1, noOfSwaps+1)
  }
  }._3

  //println(s"A ${A.toList}")

  swaps
}
solve(Array(2,0,1,3))