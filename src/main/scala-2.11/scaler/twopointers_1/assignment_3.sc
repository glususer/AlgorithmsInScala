//Given n non-negative integers A[0], A[1], …, A[n-1] , where each represents a point at coordinate (i, A[i]).
//
//N vertical lines are drawn such that the two endpoints of line i is at (i, A[i]) and (i, 0).
//
//Find two lines, which together with x-axis forms a container, such that the container contains the most water.
//
//Note: You may not slant the container.

def loopTailRecursive(A:Array[Int], i:Int, j:Int, maxArea:Int):Int={
  println(s"i $i j $j maxArea $maxArea")
  if(i<A.length-1 && j>=0 && i<j){
    val currentArea = (j-i)*  (A(i) min A(j))
    val (updatedI, updatedJ) = if(A(i)< A(j)) (i+1,j) else if(A(i) > A(j)) (i, j-1) else (i+1, j-1)
    loopTailRecursive(A, updatedI, updatedJ, maxArea max currentArea)
  }
  else maxArea
}

def loopOnlyForScalerBecauseTheirScalaCompilerSucks(A:Array[Int]):Int={
  var i=0
  var j=A.length-1
  var maxArea = 0

  while(i<A.length-1 && j>=0 && i<j){
    val currentArea = (j-i)*  (A(i) min A(j))
    maxArea = maxArea max currentArea
    if(A(i)< A(j)) i=i+1
    else if(A(i) > A(j)) j=j-1
    else {
      i=i+1
      j=j-1
    }
  }
  maxArea
}


def maxArea(A: Array[Int]): Int  = {
  //loopTailRecursive(A, 0, A.length-1, 0)
  loopOnlyForScalerBecauseTheirScalaCompilerSucks(A)
}

maxArea(Array(1,5,4,3))