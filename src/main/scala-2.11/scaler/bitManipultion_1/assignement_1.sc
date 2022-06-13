def singleNumber(A: Array[Int]): Int  = {
  A.tail.foldLeft(A.head){case (acc, ele) => acc ^ele}
}

singleNumber(Array(1,2,2,1,3))