def solve(A: Array[Int], B: Int): Int  = {
  A.zipWithIndex.foldLeft(0){case (acc,(ele,idx)) =>
  val subArray = A.slice(idx+1,A.length)
   // println(s"idx $idx, ele $ele, subArray ${subArray.toList}")
  val sumEqualsB = subArray.map(second => ele+second).count(_ == B)
    acc+sumEqualsB
  }
}

solve(Array(1,2,3,2,1),5)