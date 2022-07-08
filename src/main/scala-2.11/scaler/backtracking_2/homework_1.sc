//Given an array of candidate numbers A and a target number B, find all unique combinations in A where the candidate numbers sums to B.
//The same repeated number may be chosen from A unlimited number of times.
//
//Note:
//
//1) All numbers (including target) will be positive integers.
//2) Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
//3) The combinations themselves must be sorted in ascending order.
//4) CombinationA > CombinationB iff (a1 > b1) OR (a1 = b1 AND a2 > b2) OR ... (a1 = b1 AND a2 = b2 AND ... ai = bi AND ai+1 > bi+1)
//5) The solution set must not contain duplicate combinations.
//A = [2, 3, 6, 7]
//B = 7
//[ [2, 2, 3] , [7] ]
def combinationSum(A: Array[Int], B: Int): Array[Array[Int]]  = {
   scala.util.Sorting.quickSort(A)
  val dis = A.distinct
  val acc  = scala.collection.mutable.ListBuffer[List[Int]]()

  def helper(dis: Array[Int], B: Int, sum:Int, ind:Int, currentList:List[Int]):Unit ={
    if(B==sum ){
      acc+=currentList
    } //Base
    else if(sum > B) return // Base
    else{
      for(i<- ind until dis.length){ // all combinations
        val currentSum = sum+dis(i)
       helper(dis,B,currentSum, i, currentList:+dis(i))//set
      }
    }
  }
  helper(dis,B,0,0,List.empty)
  acc.map(_.toArray).toArray
}
combinationSum(Array(2,3,6,7),7)