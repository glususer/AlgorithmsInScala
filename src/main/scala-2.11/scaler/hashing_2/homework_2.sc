import scala.util.Random
//Given an array A of length N. You have to answer Q queries.
//
//Each query will contain four integers l1, r1, l2, and r2. If sorted segment from [l1, r1] is the same as the sorted segment from [l2 r2], then the answer is 1 else 0.
//
//NOTE The queries are 0-indexed.

def solve(A: Array[Int], B: Array[Array[Int]]): Array[Int]  = {
  val max = 1000000007
  val min = A.min
  val hashingFnMap = A.map(num => (num,(Random.nextInt(max)*1l*num)+min)).toMap

  val prefixSumWithHashingFn = A.scanLeft(0L){case (acc, ele) =>
  acc+(ele*1L)+hashingFnMap.getOrElse(ele,0l)
  }.tail

 // println(s"prefixSumWithHashingFn ${prefixSumWithHashingFn.toList}")

  B.map{case (query) => val range1Start = query(0)
  val range1End = query(1)
  val range2Start = query(2)
  val range2End = query(3)
  if(range1End-range1Start != range2End-range2Start) 0
    else{
    val  prefixSumRange1 = if(range1Start ==0) prefixSumWithHashingFn(range1End) else prefixSumWithHashingFn(range1End)-prefixSumWithHashingFn(range1Start-1)
    val prefixSumRange2 = if(range2Start == 0)prefixSumWithHashingFn(range2End) else  prefixSumWithHashingFn(range2End)-prefixSumWithHashingFn(range2Start-1)
   // println(s"prefixSumRange1 $prefixSumRange1 prefixSumRange2 $prefixSumRange2")
    if(prefixSumRange1 == prefixSumRange2) 1 else 0
  }
  }
}

solve(Array(1, 3, 2),Array(Array(0, 1, 1, 2)))