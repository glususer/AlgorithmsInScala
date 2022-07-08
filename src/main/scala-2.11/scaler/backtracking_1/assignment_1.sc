//Given a set of distinct integers A, return all possible subsets.
//
//NOTE:
//
//Elements in a subset must be in non-descending order.
//The solution set must not contain duplicate subsets.
//Also, the subsets should be sorted in ascending ( lexicographic ) order.
//The list is not necessarily sorted.
//
//
//Problem Constraints
//1 <= |A| <= 16
//INTMIN <= A[i] <= INTMAX
def subsets(A: Array[Int]): Array[Array[Int]]  = {
  scala.util.Sorting.quickSort(A)
  import scala.collection.mutable.ListBuffer
  val acc = ListBuffer.empty[List[Int]]

  def dfs(ind:Int, ans:List[Int]):Unit={
    if(ind == A.length) return  // Base case
    else{
      val current = ans:+A(ind) // take it
      acc+=current
      dfs(ind+1, current)
      dfs(ind+1,ans)// leave it
    }
  }

  dfs(0,List.empty)
  (List()+:acc).map(_.toArray).toArray
}


subsets(Array(1,2,3,4))