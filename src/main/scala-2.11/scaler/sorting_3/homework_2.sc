//Given an array A of size N, Groot wants you to pick 2 indices i and j such that
//
//1 <= i, j <= N
//A[i] % A[j] is maximum among all possible pairs of (i, j).
//Help Groot in finding the maximum value of A[i] % A[j] for some i, j.

def solve(A: Array[Int]): Int  = {
  val max = A.max
  val secondMaxArray = A.filter(_ != max)
  if(secondMaxArray.nonEmpty) secondMaxArray.max%max
  else 0
}