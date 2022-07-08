import scala.collection.mutable.ListBuffer

//Given an array A of size N denoting collection of numbers that might contain duplicates, return all possible unique permutations.
//
//NOTE: No 2 entries in the permutation sequence should be the same.
def permute(A: Array[Int]): Array[Array[Int]]  = {

  // calculate frequencies
  val map = A.foldLeft(Map.empty[Int, Int])((map, num) => map + (num -> (map.getOrElse(num, 0) + 1)))
  val acc = ListBuffer.empty[List[Int]]

  def dfs(size: Int, permutation: List[Int], freq: Map[Int, Int]): Unit = {
    if (size == A.length) acc += permutation.reverse
    else
      for (num <- freq.keys if freq(num) > 0) {
        dfs(size + 1, num :: permutation, freq + (num -> (freq(num) - 1)))
      }
  }
  dfs(0, List.empty[Int], map)
  acc.map(_.toArray).toArray
}