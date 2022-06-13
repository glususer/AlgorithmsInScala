def loop(A:Array[Int], currentLen:Int, setOfNums:Set[Int], currentNum:Int):Int={
  if(setOfNums.contains(currentNum+1)) loop(A, currentLen+1, setOfNums, currentNum+1)
  else currentLen
}

def loopWithVars(setOfNums:Set[Int],currentNum:Int ):Int={
  var currentLen = 1
  var temp = currentNum
  while(setOfNums.contains(temp+1)) {
    currentLen = currentLen+1
    temp = temp+1
  }
  currentLen
}

def longestConsecutive(A: Array[Int]): Int  = {
  val setOfNums = A.toSet
  A.indices.foldLeft(Integer.MIN_VALUE){case (maxLength, idx) =>
  if(setOfNums.contains(A(idx)-1)) maxLength max 1
   else{
   // val currentLen = loop(A, 1, setOfNums, A(idx))
    val currentLen = loopWithVars(setOfNums, A(idx))
    maxLength max currentLen
  }
  }
}

longestConsecutive(Array(100, 4, 200, 1, 3, 2))
