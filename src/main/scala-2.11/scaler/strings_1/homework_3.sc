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

  B.map{ arr =>
    val l1 = arr(0)
  val l2 = arr(1)
  val l3 = arr(2)
  val l4 = arr(3)
    if(l2-l1 != l4-l3) false
    else {
      val sum1 = if(l1 ==0) prefixSumWithHashingFn(l2)  else prefixSumWithHashingFn(l2)-prefixSumWithHashingFn(l1-1)
      val sum2 =  if(l3 ==0) prefixSumWithHashingFn(l4) else prefixSumWithHashingFn(l4) - prefixSumWithHashingFn(l3-1)
   //   println(s"sum1 $sum1 sum2 $sum2")
      sum1 == sum2
    }
  }.map(res => if(res) 1 else 0)
  }

solve(Array(1, 7, 11, 8, 11, 7, 1), Array(Array(0, 2, 4, 6)))