/**
  * Created by piyush on 13/09/16.
  */
object DynamicProgramming {
  /**
    * Subset sum algorithm - can we achieve sum t using elements from s?
    * O(s.map(abs).sum * s.length)
    *
    * @param l set of integers
    * @param k target
    * @return true iff there exists a subset of s that sums to t
    */
  def subSetSum(l: List[Int], k: Int): Boolean = {
    def helper(l: List[Int], k: Int): Boolean = {
      (l, k) match {
        case (Nil, k) => false
        case (x :: xs, 0) => true
        case (x :: Nil, k) => {
          if (x == k) true
          else false
        }
        case (x :: xs, k) => {
          if (l.head > k) subSetSum(l.tail, k)
          else subSetSum(l.tail, k - l.head) || subSetSum(l.tail, k)
        }
      }
    }
    helper(l.sortWith(_ > _), k)
  }

  /**
    * Partition a sequence into two partitions such that difference of their sum is minimum
    * O(s.length * s.sum)
    *
    * @param s list to partition
    * @return a partition of s into a and b s.t. |a.sum - b.sum| is minimum
    */

  def closestPartition(s: List[Int]): (List[Int], List[Int]) = {

    def helper(s: List[Int], a: List[Int], b: List[Int]): (List[Int], List[Int]) = {
      (s, a, b) match {
        case (Nil, a, b) => (a, b)
        case (x :: Nil, a, b) => {
          if (a.sum > b.sum) (a, x :: b)
          else (x :: a, b)
        }
        case (x :: xs, a, b) => {
          if (a.sum > b.sum) helper(xs, a, x :: b)
          else helper(xs, x :: a, b)
        }
      }
    }
    helper(s.sortWith(_ > _), List(), List())
  }

  /**
    * Generate all possible valid brackets
    *Number of brackets = C(n) i.e. the n-th Catalan number
    * because C(n) = sigma(i = 0 to n-1 C(i)*C(n-i)).
    * Same goes for no of binary trees for n number of nodes.
    *
    * @return list of all possible valid n-pair bracket strings
    */
  def validBrackets(n: Int): List[String] = {
    def helper(k: Int, l: List[String]): List[String] = {
      k match {
        case 0 => l
        case 1 => "()" :: Nil
        case 2 => "()()" :: "(())" :: Nil
        case _ => {
          for {
            i <- 0 until  k
            j = k - 1 - i
            aList = helper(i, l)
            bList = helper(j,l)
            a<-aList
            b<-bList
          } yield s"($a)$b"
        }.toList
      }
    }
    helper(n, List(""))
  }

  /**
    * Find longest common subsequence (not necessarily contiguous) of 2 sequences
    * O(a.length * b.length) since each item in cache is filled exactly once in O(1) time
    *
    * @param a first sequence
    * @param b second sequence
    * @return  longest common subsequence of a and b
    *         if multiple possible lcs, return the one that is "earliest" in a
    */
  def longestCommonSubsequence(a: List[Char], b: List[Char]):String = {
    def helper(a:List[Char],b:List[Char],c:List[Char]):List[Char]={
      (a,b) match{
        case (List(),b)=>c
        case (a,List()) =>c
        case (x::xs, y::ys)=>{
          if(x==y) helper(a, ys, x::c)
          else {
              if(a.contains(y)) helper(a, ys, y :: c)
              else helper(a,ys,c)
          }
        }
      }
    }
    helper(a,b,List()).reverse mkString("")
  }

  /**
    * Find length of longest (strictly) increasing subsequence
    * O(n log n)
    *
    * @param l input sequence
    * @return return longest increasing subsequence of a
    */
  def longestIncreasingSubsequence(l:Vector[Int]):Int= {
    var LIS = new Array[Int](l.length)
    LIS(0)=1
    for (i<-1 until l.length){
      var max = 0
      for(j<-0 until  i){
        if(l(j)<l(i) && max < LIS(j)) max = LIS(j)
      }
      if(max ==0) LIS(i)=1
      else LIS(i)=(1+max)
    }
    LIS.max
  }

  /**
    * Find the maximum sum of a contiguous sub array
    * O(n) Kadane's algorithm
    *
    * @param s
    * @return the maximum contiguous sub array sum
    */
  def maxSubArraySum(s: Seq[Int]):Int={
    s.scanLeft(0)(_+_ max 0).max
  }

  /**
    * Given an integer array with all positive numbers
    * and no duplicates, find the number of possible combinations that
    * add up to a positive integer target.
    *
    * @param l
    * @return no of ways in which this sum can be achieved
    */
  def combinationSum(l:Array[Int]):Int={
    ???
  }


}
