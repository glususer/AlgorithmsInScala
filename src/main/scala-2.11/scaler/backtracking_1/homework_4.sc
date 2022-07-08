//Given a collection of integers denoted by array A of size N that might contain duplicates, return all possible subsets.
//
//NOTE:
//
//Elements in a subset must be in non-descending order.
//The solution set must not contain duplicate subsets.
//The subsets must be sorted lexicographically.

def subsetsWithDup(A: Array[Int]): Array[Array[Int]]  = {
  scala.util.Sorting.quickSort(A)
  import scala.collection.mutable
  val acc = mutable.ListBuffer.empty[List[Int]]
  val set = mutable.Set.empty[List[Int]]

  acc+=List.empty

  def dfs(ind:Int, ans:List[Int]):Unit={
    if(ind == A.length) return
    else{
      if(!set.contains(ans:+A(ind))) {
        acc+=ans:+A(ind)
        set+=ans:+A(ind)
      }
      dfs(ind+1,ans:+A(ind)) // take ith ele
      dfs(ind+1, ans) // leave ith ele
    }
  }
  dfs(0,List.empty)
  acc.map(_.toArray).toArray
}

subsetsWithDup(Array(1,2,2))