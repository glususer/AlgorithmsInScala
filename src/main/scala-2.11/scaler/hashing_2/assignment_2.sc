//Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in 2D Cartesian plane.
//
//Find and return the number of unordered triplets (i, j, k) such that (A[i], B[i]), (A[j], B[j]) and (A[k], B[k]) form a right-angled triangle with
// the triangle having one side parallel to the x-axis and one side parallel to the y-axis.
//
//NOTE: The answer may be large so return the answer modulo (109 + 7).

def solve(A: Array[Int], B: Array[Int]): Int  = {
  val freqY = B.groupBy(identity).mapValues(_.length)
  val freqX = A.groupBy(identity).mapValues(_.length)

  A.zip(B).map{case (x_i,y_i) => val a =  (freqX.getOrElse(x_i,0)-1)
    val b = (freqY.getOrElse(y_i,0)-1)
    (a*1l*b)%1000000007}.sum.toInt
}