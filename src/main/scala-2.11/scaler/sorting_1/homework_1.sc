//Given an array of integers A, find and return the minimum value of | A [ i ] - A [ j ] | where i != j and |x| denotes the absolute value of x.

def isSorted (A:Array[Int]):Boolean={
// println(s"array testing of isSorted ${A.toList}")
 val sortedFromLeft = (1 until A.length).foldLeft(Int.MinValue){case (ele,idx) =>
  if(ele ==0 && A(idx-1) > A(idx)) idx-1
  else ele}

 val sortedFromRight = (0 to A.length-2).foldRight(Int.MaxValue) { case (idx, ele) =>
  //println(s"current ${A(idx)} next ${A(idx+1)} idx ${idx+1}")
  if(ele == Int.MaxValue && A(idx) > A(idx+1)) idx+1
  else if( A(idx) == A(idx+1)) ele
  else ele
 }

 sortedFromLeft == Int.MinValue && sortedFromRight == Int.MaxValue
}

def solve(A: Array[Int]): Array[Int]  = {
 val firstEleGreaterIdx = (1 until A.length).foldLeft(Int.MinValue,0){case ((ele, repetitions),idx) =>
  if(ele == Int.MinValue && A(idx-1) > A(idx)) (idx-1-repetitions,0)
  else if(A(idx-1) == A(idx)) (ele, repetitions+1)
  else (ele, 0)
 }._1 max 0

 val firstEleSmallerIdx = (0 to A.length-2).foldRight(Int.MaxValue,0) { case (idx, (ele,repetitions)) =>
  //println(s"current ${A(idx)} next ${A(idx+1)} idx ${idx+1}")
  if(ele == Int.MaxValue && A(idx) > A(idx+1)) (idx+1+repetitions,0)
  else if( A(idx) == A(idx+1)) (ele, repetitions+1)
  else (ele, 0)
 }._1 min A.length-1


 val start= A.slice(0,firstEleGreaterIdx)
 val mid = A.slice(firstEleGreaterIdx, firstEleSmallerIdx+1)
 val end = A.slice(firstEleSmallerIdx+1, A.length)

 println(s"startIdx $firstEleGreaterIdx, endIdx $firstEleSmallerIdx")
 println(s"start ${start.toList} mid ${mid.toList} end ${end.toList} isSorted ${isSorted(start++mid.sorted++end)}")

 if(start.isEmpty && end.isEmpty && isSorted(A)) Array(-1)
 else if (start.isEmpty && end.isEmpty)  Array(firstEleGreaterIdx, firstEleSmallerIdx)
 else if(isSorted(start++mid.sorted++end)) Array(firstEleGreaterIdx, firstEleSmallerIdx)
 else{
   val minInUnsorted = mid.min
  val maxInUnsorted = mid.max

 val newStart =  start.zipWithIndex.find{case(ele, idx) => ele >  minInUnsorted}.map(_._2).getOrElse(firstEleGreaterIdx)

  val newEnd = end.zipWithIndex.find{case(ele, _) => ele <  maxInUnsorted}.map(_._2)  match{
  case Some(x) => start.length+mid.length+end.splitAt(x)._2.span(_ == end(x))._1.length
  case None => firstEleSmallerIdx
 }

 /* val newEnd = end.zipWithIndex.foldLeft(firstEleSmallerIdx){case (current, (ele, idx)) =>
   println(s"current $current, ele $ele, idx $idx maxInUnsorted $maxInUnsorted")
  if(maxInUnsorted > ele && current == firstEleSmallerIdx) (idx)
  else if (current != firstEleGreaterIdx && end(current) == ele) idx
  else current}*/

  Array(newStart, newEnd)
 }
}

val arr = Array(1, 9, 8, 9, 9, 9, 8, 10, 9, 17, 9, 10, 18, 8, 17, 10, 17, 17, 20, 20)
solve(arr)
println(s"arr ${(arr.zipWithIndex.toList)}")

Array(17, 17, 20, 20).span(_ == 17)