//Given an integer array A of size N. Find the contiguous subarray within the given array (containing at least one number) which has the largest product.
//Return an integer corresponding to the maximum product possible.
//NOTE: Answer will fit in 32-bit integer value.

def maxProduct(A: Array[Int]): Int  = {
  val result = A.tail.foldLeft(A.head,A.head, A.head)
  {
    case ((minProduct, maxProduct, result),ele) =>
      val fp = minProduct*ele
      val sp = maxProduct*ele
      val updatedMin = fp min ele min sp
      val updatedMax = fp max ele max sp

      (updatedMin, updatedMax, result max updatedMax)
  }._3
  result
}

maxProduct(Array(4,10,-5,7,-2))