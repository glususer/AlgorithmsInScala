//Given an array A of non-negative integers of size N. Find the minimum sub-array Al, Al+1 ,..., Ar
// such that if we sort(in ascending order) that sub-array, then the whole array should get sorted. If A is already sorted, output -1.
//2,6,4,8,10,9,15

def subUnsort1(A: Array[Int]): Array[Int]  = {
  val (leftStart, leftEnd,_) = A.tail.foldLeft(0,0,0){case ((start, end, idx), ele) =>
    if(A(idx) > ele && start ==0)(idx+1, end, idx+1)
    else if(A(idx) > ele)(start, idx+1, idx+1)
    else(start, end, idx+1)
  }

  val (rightStart, rightEnd,_) = A.dropRight(1).foldRight(A.length-1,A.length-1,A.length-1){case (ele,(start, end, idx)) =>
    // println(s"start $start end $end idx $idx ele $ele")
    if(A(idx) < ele && start ==  A.length-1)(idx-1, end, idx-1)
    else if(A(idx) < ele)(start, idx-1, idx-1)
    else(start, end, idx-1)
  }

  val (start,end) = (leftStart min leftEnd min rightEnd min rightStart, leftStart max leftEnd max rightEnd max rightStart)
  println(s"start $start end $end")
  val (minInUnsorted, maxInUnsorted) = (start to end).foldLeft(Integer.MAX_VALUE, Integer.MIN_VALUE){case ((min, max), idx) =>
  if(A(idx) < min) (A(idx), max)
  else if(A(idx) > max) (min, A(idx))
  else(min, max)
  }

  val minIdx = (A.zipWithIndex.slice(0, start).find(_._1 > minInUnsorted) orElse A.zipWithIndex.slice(end, A.length).find(_._1 > minInUnsorted)).map(_._2).getOrElse(start)
  val maxIdx = (A.zipWithIndex.slice(0, minIdx).find(_._1 < maxInUnsorted) orElse A.zipWithIndex.slice(end, A.length).find(_._1 < maxInUnsorted)).map(_._2).getOrElse(end)
  println(s"minIdx $minIdx maxIdx $maxIdx minInUnsorted $minInUnsorted maxInUnsorted $maxInUnsorted ")
  A.slice(minIdx, maxIdx+1)
}

/*val arr = Array(1,3,2,5,4,7,8)
val unsorted = subUnsort1(arr)*/

//int start =0;
//
//int end = A.length-1;
//
//
//w
//hile(start< A.length){
//
// if(A[start]== copiedArray[start])
//
//    { start++;}
//
// else break;
//
// //if start has reached end then it means array is sorted
//
// if (start== A.length) return new int [] {-1};
//
// //Lets iterate from end to find the difference
//
// while (end >-1) {
//
//  if (A[end]==copiedArray[end])
//
//   { end–;}
//
//  else break;
//
//}
//
//return new int [] { start, end};
def subUnsort(A: Array[Int]): Array[Int]  = {
  val sorted = A.sorted
  var start = 0
  var end = A.length-1
  var flag1 = true
  var flag2 = true

  while(start<A.length && flag1) {
   // println(s"start $start")
    if (A(start) == sorted(start)) start = start + 1
    else flag1 = false
  }

  if (start == A.length) Array(-1)
  else {
    while (end > -1 && flag2) {
    //  println(s"end $end")
      if (A(end) == sorted(end)) end = end - 1
      else flag2 = false
    }
    Array(start, end)
  }
}
val arr = Array(1,2,3)
val unsorted = subUnsort(arr)