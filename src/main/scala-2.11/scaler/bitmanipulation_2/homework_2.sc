import scala.collection.mutable
//You are given an array of integers A of size N.
//
//The value of a subarray is defined as BITWISE OR of all elements in it.
//
//Return the sum of value of all subarrays of A % 109 + 7.

def solve(A: Array[Int]): Int  = {
  A.indices.foldLeft(0){case (sum, i) =>
 //   println(s"sum $sum")
    (i until A.length).foldLeft(0,sum){case ((sum1, sum), j) =>
    val updatedSum1 = sum1 | A(j)
  //    println(s"updatedSum1 $updatedSum1, updatedSum ${sum+updatedSum1}")
      (updatedSum1, (sum+updatedSum1)%1000000007)
    }._2
  }
}

solve(Array(1, 2, 3))
0|2
2|3