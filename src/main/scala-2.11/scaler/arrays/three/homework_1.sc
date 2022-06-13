def bfsSolve(visited:Set[Int], toVisit:Set[Int], noOfJumps:Int, target:Int, A:Array[Int]):Int={
  println(s"visited $visited toVisit $toVisit noOfJumps $noOfJumps")
  if(toVisit.isEmpty) -1
  else if(toVisit.contains(target)) noOfJumps
  else{
    val neighbours = toVisit.flatMap(pos => (pos to pos+A(pos)).filterNot(visited.contains))
    bfsSolve(visited++toVisit, neighbours, noOfJumps+1, target, A)
  }
}

def solve(A: Array[Int]): Int  = {
  bfsSolve(Set.empty, Set(0), 0, A.length-1, A)
}

def helper(A:Array[Int], noOfJumps:Int, maxIdx:Int):Int={
  if(maxIdx < A.length-1) {
    val rangeOfJumps = (maxIdx + 1 to (maxIdx + A(maxIdx) min A.length-1)).map(idx => (A(idx), idx))
    if (rangeOfJumps.nonEmpty) {
      if ((maxIdx to maxIdx + A(maxIdx)).contains(A.length - 1)) helper(A, noOfJumps + 1, A.length)
      else {
          val indexWithMaxJump = rangeOfJumps.maxBy{case (jump, idx)  => jump+idx}._2
          helper(A, noOfJumps + 1, indexWithMaxJump)
      }
    } else
      helper(A, -1, A.length)
  }else noOfJumps
}

def solveAlternate(A:Array[Int]):Int={
  helper(A, 0, 0)
}
//solveAlternate(Array(2,3,0,1,4))

//2,3,0,1,4
//0,1,2,3,4
def solveHelper(A:Array[Int], noOfJumps:Int, currMax:Int, currIdx:Int):Int={
 // println(s"noOfJumps $noOfJumps, currMax $currMax, currIdx $currIdx")
  if(currMax == 0) -1
  else if(currIdx >=A.length-1) noOfJumps
  else{
    val (reachedEnd, didNotReachEnd)  =  ((currIdx+1) to A(currIdx)+currIdx).partition(_ >= A.length-1)
   // println(s"reachedEnd ${reachedEnd.toList} didNotReachEnd ${didNotReachEnd.toList}")
    if(reachedEnd.nonEmpty)solveHelper(A, noOfJumps+1, reachedEnd.max, reachedEnd.max)
    else {
      val (nextIdx, nextMax) = didNotReachEnd.map(idx => (idx, idx + A(idx))).maxBy(_._2)
      solveHelper(A, noOfJumps + 1, nextMax, nextIdx)
    }
  }
}



def solve1(A:Array[Int]):Int={
  if(A(0)+0>=A.length-1)1
  else solveHelper(A, 0, A(0)+0, 0)
}

solve1(Array.fill(100000)(1))