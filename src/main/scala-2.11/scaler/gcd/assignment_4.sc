def solve(A: Array[Int]): Array[Int]  = {
  val sizeOfOriginalArr = math.sqrt(A.length).toInt
  A.grouped(sizeOfOriginalArr).map(grouped => grouped.max).toArray.distinct
}

solve(Array(5,5,5,15))