import scala.collection.immutable.IndexedSeq
import scala.util.Random

/**
  * Created by shivangi on 02/09/16.
  */
object Arrays {

  def activityTime(start:Array[Int],finish:Array[Int]):Int={
   ???
  }

  def twoSum(l:Array[Int],target:Int):(Int,Int)= {
    var (i, j) = (0, l.length - 1)
    while (i < j) {
      if (l(i) + l(j) == target) return (i, j)
      else if (l(i) + l(j) > target) j -= 1
      else i += 1
    }
    (0,0)
  }

  def binSearch(l:Array[Int]):Int={
    ???
  }

  def stockPrices(l:Array[Int]):(Int,Int)={
    val len = l.length
    var (min,max)=(l.last,l.last)
    for (i<-len-2 to 0 by -1){
      if(min > l(i)) min = l(i)
      if(max < l(i) ) max = l(i)
    }
    (min,max)
  }
/* print matrix in a spiral manner. For eg
  1  ,  2,  3,  4,  5,  6, 7
  16 , 17, 18, 19, 20, 21, 8
  15,  14, 13, 12, 11, 10, 9
   should print 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
 */
  def printSpiralMatrix(l:Array[Array[Int]]):Unit={
    val row = l.length
    val col = l(0).length
    val noOfElements = row*col
    var iterateRow =false
    var iterateCol =true
    var incrRow =false
    var incrCol = true
    var nextIncrRow=(1,row-1)
    var nextIncrCol =(0,col-1)
    var nextDecrCol=(col-2,0)
    var nextDecrRow=(row-2,1)
    var count =0
    var toRight=0
    var toLeft = row-1
    var climbUp=0
    var climbDown=col-1

    while(count < noOfElements && iterateCol){
      if(iterateCol && incrCol &&count < noOfElements) {
        for(i<-nextIncrCol._1 to nextIncrCol._2){
          print(l(toRight)(i)+" ")
          count+=1
        }
        nextIncrCol=(nextIncrCol._1+1,nextIncrCol._2-1)
        iterateRow = true
        incrRow=true
        iterateCol=false
        incrCol=false
        toRight+=1
      }
      if(iterateRow && incrRow && count < noOfElements){
        for(i<-nextIncrRow._1 to nextIncrRow._2){
          print(l(i)(climbDown)+" ")
          count+=1
        }
        nextIncrRow=(nextIncrRow._1+1, nextIncrRow._2-1)
        iterateRow = false
        incrRow=false
        iterateCol=true
        incrCol=false
        climbDown-=1
      }
      if(iterateCol && !incrCol && count < noOfElements){
        for(i<-nextDecrCol._1 to nextDecrCol._2 by -1) {
          print(l(toLeft)(i)+" ")
          count+=1
        }
        iterateRow = true
        incrRow=false
        iterateCol=false
        incrCol=false
        nextDecrCol=(nextDecrCol._1-1,nextDecrCol._2+1)
        toLeft-=1
      }
      if(iterateRow && !incrRow && count < noOfElements){
        for(i<-nextDecrRow._1 to nextDecrRow._2 by -1) {
          print(l(i)(climbUp)+" ")
          count+=1
        }
        iterateRow = false
        incrRow=false
        iterateCol=true
        incrCol=true
        nextDecrRow=(nextDecrRow._1-1,nextDecrRow._2+1)
        climbUp+=1
      }
    }
  }

  /**
    * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
    * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
    * Find the minimum element in O(log n) time complexity. The array may contain duplicates.
    */
  def minInSortedArray(l:Array[Int]):Int={
      def helper(low:Int,high:Int):Int={
        (high-low) match{
          case (0)=>l(low)
          case (1)=> if(l(low) > l(high)) l(high) else l(low)
          case(_)=>{
            val mid = low+(high-low)/2
            if(l(mid) > l(high) ) helper(mid,high)
            else  helper(low,mid)
          }
        }
      }
    helper(0,l.length-1)
  }

  def kthLargest(s:Array[Int],k:Int):Int={
      val kth = s.length-k
       def helper(l:Array[Int],m:Int,prevRand:Int):Int={
         m match{
           case (m)=> {
             if(m==kth) l(prevRand)
             else {
               val r = Random.nextInt(l.length)
               val pvt = l(r)
               val (lessThen, greaterThen) = l.partition(x => x < pvt)
               helper(l, lessThen.length,r)
             }
           }
         }
       }
    helper(s,Integer.MAX_VALUE,0)
  }
  /*Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which
   the sum ≥ s. If there isn't one, return 0 instead.For example, given the array [2,3,1,2,4,3] and s = 7,the sub
   array [4,3] has the minimal length under the problem constraint.
   https://leetcode.com/problems/minimum-size-subarray-sum/
   */

  def minSubArray(l:Array[Int],target:Int):Array[(Int,Int)]={
   ???
  }

  /* Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product
   of all the elements of nums except nums[i]. Solve it without division and in O(n). For example, given [1,2,3,4], return [24,12,8,6].
   https://leetcode.com/problems/product-of-array-except-self/
   */

  def productExceptSelf(l:Array[Int]):IndexedSeq[Int]={
    val left =l.scanLeft(1)(_*_)
    val right = l.scanRight(1)(_*_).drop(1)
    for{
      i<-0 until l.length
    }yield(left(i)*right(i))
  }
  //val missingElement = val missingElement = (array.min to array.max).partition{x=>array.exists(_ == x)}._2.filter(_ !=0))
  /* Given an unsorted integer array, find the first missing positive integer. For example, Given [1,2,0] return 3,
  and [3,4,-1,1] return 2.
  https://leetcode.com/problems/first-missing-positive/
   */
  def firstMissingPositive(l:Array[Int]):Int= {
    if (l.max == l.min || l.max < 0 || l.isEmpty) return 1
    else {
      val missingElement = (l.min to l.max).partition { x => l.exists(_ == x) }._2.filter(_ > 0)
      if (missingElement.isEmpty) l.max + 1
      else missingElement.head
    }
  }

  def combination(k:Int,l:List[Int]):List[List[Int]]={
    if (k > l.length) Nil
    else l match {
      case x :: xs => {
        if (k == 1) l.map(x => List(x))
        else {
          combination(k - 1, xs).map(x :: _) ::: combination(k, xs)
        }
      }
      case _ => Nil
    }
    }

  def powerSet(l:List[Int]):List[List[Int]]={
   val y = for{
      i<- 0 to l.length
    }yield combination(i,l)
    y.flatMap(x=>x).toList
  }

  def permutations(l:List[Int]):List[List[Int]]={
    def helper(k:Int,z:List[Int]):List[List[Int]]= {
      l match {
        case (x :: xs) => {
          if (k == 1) List(z(0)) :: Nil
          else if (k == 2) List(z(0), z(1)) :: List(z(1), z(0)) :: Nil
          else {
           val y =  for {
              i <- 0 until z.length
              x = helper(k - 1, z.filter(_ != z(i))).map(z(i)::_)
            } yield x
            y.flatten(x=>x).toList
          }
        }
      }

    }
    helper(l.length,l)
  }
}
