//Given an integer array A of size N.
//
//If we store the sum of each triplet of the array A in a new list, then find the Bth smallest element among the list.
//
//NOTE: A triplet consists of three elements from the array. Let's say if A[i], A[j], A[k] are the elements of the triplet then i < j < k.
def loop(start:Int, end:Int, i:Int,A:Array[Int], count:Int, mid:Int):Int={
  if(start<=end){
    val sum = A(i)+A(start)+A(end)
    //println(s"sum $sum start $start end $end i $i count $count")
    if(sum <= mid) loop(start+1, end, i, A,count+(end-start),mid)  else loop(start,end-1,i,A,count,mid)
  }else count
}

def checkCondition(A:Array[Int], mid:Int): Int ={
  A.indices.foldLeft(0){case (acc, i) =>
  val currentCount = loop(i+1,A.length-1,i,A,acc,mid)
 //   println(s"currentCount $currentCount i $i")
  currentCount
  }
}

def binarySearch(A:Array[Int], left:Int, right: Int, key:Int):Int={
  if(left<right){
    val mid = ((right-left)/2)+left
    val sumOfTripletsLessThanMid = checkCondition(A,mid)
    val sumOfTripletsLessThanMidMinusOne = checkCondition(A,mid-1)
   // println(s"left $left mid $mid right $right sumAtMid $sumOfTripletsLessThanMid sumAtMidMinusOne $sumOfTripletsLessThanMidMinusOne key $key ")
    if(sumOfTripletsLessThanMid == key && sumOfTripletsLessThanMidMinusOne < key) mid
    //else if (sumOfTripletsLessThanMidMinusOne == key) mid-1
    else if(sumOfTripletsLessThanMid >= key)binarySearch(A, left, mid, key)
    else binarySearch(A, mid+1, right, key)
  }else left
}

def solve(A: Array[Int], B: Int): Int  = {
  scala.util.Sorting.quickSort(A)
  binarySearch(A, A.take(3).sum, A.takeRight(3).sum,B)
}

// expected = 65948 , actual = 65949, key = 8481
solve(Array(13360, 33118, 20591, 23446, 14026, 19765, 18746, 4348, 36176, 31807, 34782, 22761, 39975, 11299, 26163, 13164, 14030, 25263, 31324, 14116, 5726, 18093, 38511, 6948, 12764, 23561, 23457, 37197, 24183, 10244, 20539, 9508, 7867, 19246, 14443, 10015, 18211, 25759, 14494, 33252, 15490, 23838, 18404, 6590 ),8481)
//checkCondition(Array(1,5,7,3,2),10)
/*
val i  =  3
val arr = Array(1,2,3,5,7)
loop(i+1,arr.length-1,i,arr,0,10)*/
