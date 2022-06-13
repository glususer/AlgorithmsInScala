//Given an array, A of integers of size N. Find the maximum value of j - i such that A[i] <= A[j].

def loop(i:Int, j:Int, n:Int, diff:Int,rightmax:Array[Int], leftMin:Array[Int]):Int={
  if(j >= n || i >= n) diff
  else{
    if(leftMin(i)<=rightmax(j))loop(i, j+1, n,diff max (j-i), rightmax, leftMin)
    else loop(i+1, j, n,diff, rightmax, leftMin)
  }
}

def maximumGap(A: Array[Int]): Int  = {
  val leftMin = A.scanLeft(A.head){case (prevEle, currentEle) =>
  if(currentEle < prevEle) (currentEle)
  else prevEle}.drop(1)

  val rightmax = A.scanRight(A.last){case (currentEle, prevEle) =>
    if(currentEle > prevEle) currentEle
    else prevEle}.dropRight(1)

 /* val maxDiff = loop(0,0,A.length,Integer.MIN_VALUE, rightmax, leftMin)
  maxDiff*/

  var i=0
  var j=0
  var maxDiff = Integer.MIN_VALUE
  while(i<A.length && j < A.length){
    if(leftMin(i) <= rightmax(j)){
      maxDiff = maxDiff max (j-i)
      j=j+1
    }else i=i+1
  }
  maxDiff
}
maximumGap(Array(1,10))