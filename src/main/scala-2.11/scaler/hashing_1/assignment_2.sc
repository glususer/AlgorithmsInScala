//Given an array of integers A, find and return whether the given array contains a non-empty subarray with a sum equal to 0.
//
//If the given array contains a sub-array with sum zero return 1, else return 0.

def solve(A: Array[Int]): Int  = {
 val prefixSum =  A.scanLeft(0l){case (acc, ele) => (acc+ele)}
  if(prefixSum.groupBy(identity).mapValues(_.length).exists(_._2>1))1 else 0
}

solve(Array(1,-1))