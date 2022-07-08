//Given an array of integers A, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.
//
//Find and return the number of permutations of A that are squareful. Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].
def isPerfectSquare(arr:List[Long]):Boolean={
  if(arr.length ==1)false
  else {
    arr.tail.foldLeft(true, arr.head) { case ((isPerfect, prevEle), ele) =>
      if (isPerfect) {
        if (math.sqrt(prevEle + ele) - math.sqrt(prevEle + ele).toInt == 0) (true, ele)
        else (false, ele)
      }
      else (false, ele)
    }._1
  }
}
def solve(A: Array[Int]): Int  = {
  import scala.collection.mutable
  val map = A.groupBy(identity).mapValues(_.length)
  val acc = mutable.ListBuffer[List[Long]]()

  def dfs(size:Int, current:List[Long],freqMap:Map[Int,Int]):Unit={
    if(size == A.length && isPerfectSquare(current)) acc+=current
    else{
      for(num<- freqMap.keys if (freqMap(num) >0)){
        dfs(size+1, num::current, freqMap+(num->(freqMap(num)-1)))
      }
    }
  }

  dfs(0,List.empty, map)
 // println(s"acc $acc")
  acc.size
}

solve(Array(631050364, 880290171, 613501792, 93934223, 795618414, 807606438, 126743110, 19888888))