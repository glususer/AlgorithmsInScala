def getBinaryString(x:Int, length:Int):String={
  val binaryStr = x.toBinaryString
  if(length == binaryStr.length) binaryStr
  else (0 until (length-binaryStr.length)).map(_ => "0").mkString("")+x.toBinaryString
}
//Given an integer array A of N integers, find the pair of integers in the array which have minimum XOR value.
// Report the minimum XOR value.
def findMinXor(A: Array[Int]): Int  = {
  scala.util.Sorting.quickSort(A)
  val minXor = A.tail.foldLeft(A.head, Integer.MAX_VALUE){case ((prevEle,minXor),ele) =>
  if((prevEle^ele) < minXor) (ele, prevEle^ele)
    else(ele, minXor)
  }._2
  minXor
}

findMinXor(Array(15, 5, 1, 10, 2))
