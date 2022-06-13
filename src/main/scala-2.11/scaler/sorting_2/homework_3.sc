//Given an array of integers A of size N that is a permutation of [0, 1, 2, ..., (N-1)], if we split the array into some number of "chunks" (partitions), and individually sort each chunk. After concatenating them in order of splitting, the result equals the sorted array.
//
//What is the most number of chunks we could have made?
// Tecchnique: Iterate through array and keep a variable of max, when max == currentIdx, then we have got 1 chunk


def solve(A: Array[Int]): Int  = {
  A.indices.foldLeft(Integer.MIN_VALUE, 0){case((maxUpUntil,count),i) =>
    val updatedMax = A(i) max maxUpUntil
  if(updatedMax == i) (updatedMax,count+1)
  else (updatedMax, count)
  }._2
}

solve(Array(2,0,1,3))