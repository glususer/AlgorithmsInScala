import scala.collection.mutable
import scala.util.Try

/*import scala.collection._
import scala.util.Random
//Runtime: 516 ms, faster than 100.00% of Scala online submissions for Max Chunks To Make Sorted II.
//Memory Usage: 51.7 MB, less than 100.00% of Scala online submissions for Max Chunks To Make Sorted II

def maxChunksToSorted(arr: Array[Int]): Int = {
  val maxLeft = arr.scanLeft(arr.head)((a,b) => if(a >= b) a else b).tail
  val minRight = arr.scanRight(arr.last)((a,b) => if(a<= b)a else b).dropRight(1)
  maxLeft.dropRight(1).zip(minRight.tail).map{case (x,y) => if(y >= x)1 else 0}.sum+1
}

//maxChunksToSorted(Array(2,1,3,4,4))

def rangeSum(a: Array[Int], n: Int, left: Int, right: Int): Int = {
val sumList = a.scanLeft(0)(_+_)
val x : Stream[Int] ={{for{
  y <- sumList
  x <- sumList
  z = if(x-y > 0) ((x-y)) else 0
}yield z }filter(_ > 0)}.map(identity)(collection.breakOut)

  val m = mutable.PriorityQueue(x:_*)(Ordering.Int.reverse)
  var loop =0
  var newLst = List[Int]()

  while(loop < right){
    newLst = newLst:+m.dequeue()
    loop +=1
  }
  newLst.slice(left-1,right).foldLeft(0){case(acc,value) => acc+value% (math.pow(10,9)+7).toInt}
}
//rangeSum(Array(1,2,3,4), n = 4, left = 1, right = 5)

def minSteps(s: String, t: String): Int = {
  s.sorted.diff(t.sorted).length
}

val x = "(())()()"

@scala.annotation.tailrec
def helper(lst:List[String],acc:List[String]):List[String]={
 // println(s"lst is ${lst.mkString(", ")} and acc is ${acc.mkString(", ")}")
  lst match {
      case Nil if(acc.length >1)=> helper(acc,List())
      case Nil if(acc.length == 1) => acc
      case x :: y :: z :: ys if (x == "(" && (y forall Character.isDigit) && z == ")") => helper(ys, acc :+ (y.toInt * 2).toString)
      case y :: z :: ys if ((y forall Character.isDigit) && (z forall Character.isDigit)) => helper(ys, acc :+ (y.toInt + z.toInt).toString)
      case x :: y :: ys if (x == "(" && y == ")") => helper(ys, acc :+ "1")
      case x :: xs => helper(xs, acc :+ x)
    }
}


helper("()".toList.map(_.toString),List()).head.toInt

"".toList.map(_.toString)

def scoreOfParentheses(S: String): Int = {
  helper(S.toList.map(_.toString),List()).head.toInt
}

// List(9,0,2,4,1,8,10,12) k = 4 ,
// 1) pivot = 9, left = List(0,2,4,1,8,9) right = List(10,12),k=4
// 2) pivot = 0, left = List(),right = List(0,2,4,1,8),k=1
// 3) pivot = 2, left = List(1,2), right = (4,8)

/*def kthLargest(l:List[Int],k:Int):Option[Int]={
  def helper(m:List[Int],index:Int):Option[Int]={
    if(m.nonEmpty) {
      val pivot = m.head
      val (left, right) = m.partition(_ < pivot)
      println(left,right)
      if (right.size == index) Some(pivot)
      else if (right.size > index) helper(right, index)
      else helper(left, index)
    }
    else None
  }
  helper(l, k)
}*/

def kthLargest(s:Array[Int],k:Int):Int={
  val kth = s.length-k
  def helper(l:Array[Int],m:Int,prevRand:Int):Int={
    println(l.mkString(", "),m,kth,prevRand)

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

def kthLargest2(s:List[Int],k:Int):Int={
  def helper(l:List[Int],lenList:Int,idx:Int):Int={
    if(lenList == k-1)l(idx)
    else{
      val r = Random.nextInt(l.length)
      val pvt = l(r)
      val (lessThen, greaterThen) = l.partition(x => x <= pvt)
      helper(l, greaterThen.length,r)
    }
  }
  helper(s,Integer.MAX_VALUE,0)
}
//kthLargest2(List(9,0,2,4,1,8,10,12),3)

def priceForStep(priceWithIndex: (Int, Int),prevCost1:Int, preVCost2:Int):Int = {
  val idx = priceWithIndex._2
  val price = priceWithIndex._1
  idx match{
    case 0 => priceWithIndex._1
    case 1 => priceWithIndex._1
    case _ => Math.min(prevCost1,preVCost2)  + price
  }
}

def newCost(arr: Array[Int]) : Array[Int]= {
  ???
}

def minCostClimbingStairs(cost: Array[Int]): Int = {
  cost.length match{
    case 0 => 0
    case 1 => cost.head
    case 2 => Math.min(cost.head, cost.last)
    case _ => val finalCost = cost.tail.tail.scanLeft((cost.head, cost.tail.head)) {case (acc, value) =>
      (acc._2,Math.min(acc._1, acc._2)+value) }.tail.last

      Math.min(finalCost._1, finalCost._2)
  }
}

minCostClimbingStairs(Array(1, 100, 1, 1, 1, 100, 1, 1, 100, 1))
//val xx = List(10,15,20)
//  xx.tail.tail.scanLeft((xx.head, xx.tail.head)) {case (acc, value) => (acc._2,Math.min(acc._1, acc._2)+value) }.tail.last

def maximumProduct(a: Array[Int]): Int = {
  val sorted = a.sorted
   Math.max(sorted.take(2).product*sorted.last, sorted.takeRight(3).product)
}

def reverseWords(s: String): String = {
  s.trim.split(" ").foldRight(" "){case (acc,value) => acc+value}
}

def reversePairs(a:Array[Int]):Int={
  ???
}

/*def mergeSortedLists(l: List[Int], m: List[Int],cnt:Int): (List[Int],Int) = {
  (l, m) match {
    case (Nil, m) => (m,cnt)
    case (l, Nil) => (l,cnt)
    case (x :: xs, y :: ys) => {
      if (x <= y){
        val t = mergeSortedLists(xs,m,cnt)
        (x :: t._1,t._2)
      }
      else{
        val t = mergeSortedLists(l,ys,l.size)
        (y ::t._1,t._2)}
    }
  }
}

def mergeSort(l: List[Int],cnt:Int): (List[Int],Int) = {
    val n = l.length / 2
  if(n == 0) (l,cnt)
  else {
    val (left, right) = l.splitAt(n)
    val leftMergeSort = mergeSort(left, cnt)
    val rightMergeSort = mergeSort(right, cnt)
    mergeSortedLists(leftMergeSort._1, rightMergeSort._1, leftMergeSort._2 + rightMergeSort._2)
  }
  }

mergeSort(List(10,3,4,2,5,7,9,11),0)*/


def merge2Maps(map1: Map[Int, Int], map2: Map[Int, Int]):Map[Int,Int]={
  map1 ++ map2.map{ case (k,v) => k -> (v + map1.getOrElse(k,0)) }
}

def mergeSortReturningInversionPairs(l:List[Int],count:Map[Int,Int]):(List[Int],Map[Int,Int])={
  val n = l.length/2
  if(n == 0) (l,count)
  else{
    def mergeSortedLists1(l:List[Int],r:List[Int],count:Map[Int,Int]):(List[Int],Map[Int,Int])={
      (l,r) match{
        case (Nil,r) => (r,count)
        case (l,Nil) => (l,count)
        case (x::xs,y::ys) if (x <= y) => {
          val t = mergeSortedLists1(xs,r,count)
          (x::t._1,t._2)
        }
        case (x::xs, y::ys) if(x>y)=>{
          val (leftKeys,rightKeys) = count.partition{case (key,_) => l.contains(key)}
          val updatedLeftKeys = leftKeys.map{case (key,value) => (key,value+1)}
          val t = mergeSortedLists1(l,ys,merge2Maps(updatedLeftKeys,rightKeys))
          (y::t._1, t._2)
        }
      }
    }
    val (left,right) = l.splitAt(n)
    val leftMergeSort = mergeSortReturningInversionPairs(left,count)
    val rightMergeSort = mergeSortReturningInversionPairs(right,count)
    mergeSortedLists1(leftMergeSort._1,rightMergeSort._1,merge2Maps(leftMergeSort._2, rightMergeSort._2))
  }
} */

// https://leetcode.com/problems/find-all-duplicates-in-an-array/
def findDuplicates(nums: Array[Int]): List[Int] = {
  nums.foreach(n => nums(n.abs - 1) *= -1)
  nums.filter(n => nums(n.abs - 1) > 0).map(_.abs).toSet.toList

}

//
def findDuplicate(nums: Array[Int]): Int = {
  nums.foreach(n => nums(n.abs - 1) *= -1)
  nums.filter(n => nums(n.abs - 1) > 0).map(_.abs).toList.headOption.getOrElse(0)
}

findDuplicate(Array(2, 2, 2, 2, 2))

def mountainHelper(l: List[Int], acc: List[List[Int]], currentList: List[Int]): List[List[Int]] = {
  l match {
    case Nil => if (currentList.headOption.getOrElse(0) == -1 && currentList.lastOption.getOrElse(0) == 1) acc :+ currentList
    else acc
    case x :: Nil => if (x == 1) acc :+ (currentList :+ x)
    else if (x == -1 && currentList.lastOption.getOrElse(-1) == -1) acc
    else if (x == -1 && currentList.lastOption.getOrElse(1) == 1) acc :+ currentList
    else acc
    case x :: y :: xs => if (x == 1 && y == 1 && currentList.lastOption.getOrElse(0) == -1) mountainHelper(xs, acc, (currentList :+ x :+ y))
    else if (x == 1 && y == -1) mountainHelper(xs, acc :+ (currentList :+ x), List(y))
    else if (x == -1 && currentList.lastOption.getOrElse(-1) == -1) mountainHelper(xs, acc, currentList :+ x :+ y)
    else if (x == -1 && currentList.lastOption.getOrElse(1) == 1) mountainHelper(xs, acc :+ currentList, List(x, y))
    else mountainHelper(xs, acc, currentList)

  }
}

def repeatedSpan(l: List[Int], acc: List[List[Int]], current: List[Int]): List[List[Int]] = {
  l match {
    case Nil => acc :+ current
    case x :: xs if (x == 0) => repeatedSpan(xs, acc :+ current, List.empty)
    case x :: xs => repeatedSpan(xs, acc, current :+ x)
  }
}
def longestMountain(arr: Array[Int]): Int = {
  val idxs = arr.indices.dropRight(1)
  val transformedLst = (for {
    x <- idxs
    diff = arr(x) - arr(x + 1)
  } yield diff).toList.map(ele => if (ele > 0) 1 else if (ele < 0) -1 else 0)

  val x = repeatedSpan(transformedLst, List.empty, List.empty).filterNot(_.isEmpty)
    .flatMap(lst => mountainHelper(lst, List.empty, List.empty).filterNot(_.isEmpty)).map(_.length)

  if (x.isEmpty) 0 else x.max + 1
}

/*val lsts = repeatedSpan(List(-1,-1,0,0,-1,-1,-1,1,1,1,0,1,1,-1,-1,1),List.empty, List.empty).filterNot(_.isEmpty)

//mountainHelper(List(1, 1,-1,-1,1),List.empty,List.empty)
lsts.flatMap(lst => mountainHelper(lst,List.empty, List.empty)).map(_.length).max*/

longestMountain(Array(3, 2))

//https://leetcode.com/problems/rotate-array/
def rotate(nums: Array[Int], k: Int): Unit = {
  val kmod = k % nums.length
  val r = nums.takeRight(kmod) ++ nums.take(nums.length - kmod)
  r.zipWithIndex.foreach(x => nums(x._2) = x._1)
}

//https://leetcode.com/problems/wiggle-sort-ii/
def wiggleSort(nums: Array[Int]): Unit = {
  val sorted = nums.sortWith(_ > _)
  val len = if (sorted.length % 2 == 0) sorted.length / 2 else sorted.length / 2
  val (odd, even) = sorted.splitAt(len)
  println(s"odd ${odd.toList.mkString(", ")} and even ${even.toList.mkString(", ")}")
  var idx1 = 0
  var idx2 = 0
  while (idx2 < even.length) {
    nums(idx1) = even(idx2)
    idx1 += 2
    idx2 += 1
  }
  idx1 = 1
  idx2 = 0
  while (idx2 < odd.length) {
    nums(idx1) = odd(idx2)
    idx1 += 2
    idx2 += 1
  }
  println(s"${nums.toList.mkString(", ")}")
}
wiggleSort(Array(4, 5, 5, 6))

//https://leetcode.com/problems/continuous-subarray-sum/
// check for -ve k's and k == 0
def checkSubarraySum(nums: Array[Int], k: Int): Boolean = {
  if (nums.length < 2) false
  else if(nums.length == 2 && k != 0) nums.sum%k == 0
  else {
    if (k == 0) {
      nums.sliding(2, 1).toList.exists(arr => arr(0) == 0 && arr(1) == 0)
    }
    else if(k == 1 || k == -1 ) true
    else {
      val modK = math.abs(k)
      val modNums = nums
        .filterNot(x => x % modK == 0 && x != 0)
        .scanLeft(0) { case (acc, num) => (acc + num) % modK }.tail

         modNums.tail.contains(0) ||  {val (left,right) = modNums.tail.span(_ != modNums.head)
           left.nonEmpty && right.nonEmpty}

    }
  }
}
//[0,1,0,3,0,4,0,4,0]
//5
checkSubarraySum(Array(), 6)
//https://leetcode.com/problems/move-zeroes/
def moveZeroes(nums: Array[Int]): Unit = {



}

//https://leetcode.com/problems/array-of-doubled-pairs/

@scala.annotation.tailrec
def helper(A:Array[Int],acc:Map[Int,Boolean],idx:Int):Map[Int,Boolean]={
  if(A.length == 1) Map(0->false)
  else if(idx ==A.length/2 ) acc
  else{
    println(idx, 2*idx+1,2*idx, A(2*idx+1) == 2*A(2*idx))
    if(A(2*idx+1) == 2*A(2*idx)) helper(A,acc+(idx->true),idx+1)
    else helper(A,acc+(idx->false),idx+1)
  }
}
def canReorderDoubled(A: Array[Int]): Boolean = {
  val (negative,positive) = A.partition(_<0)
  // println(negative.toList, positive.toList)

  if(negative.length%2 ==0 && positive.length%2 == 0) {
    val x = helper(negative.sortWith(_ > _), Map.empty, 0).values.count(_ == true) == negative.length / 2
    val y = helper(positive.sorted, Map.empty, 0).values.count(_ == true) == positive.length / 2
    println(positive.length / 2, helper(positive.sorted, Map.empty, 0))
    x && y
  } else false
}

//https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
def sumOddLengthSubarrays(arr: Array[Int]): Int = {
  val oddLength = (1 to (if (arr.length%2==1) arr.length else arr.length-1 )).filter(_%2==1)
 // println(oddLength)
  oddLength.flatMap{len => arr.sliding(len,1).map(arr => arr.sum)}.sum
}

sumOddLengthSubarrays(Array(1,2))

//https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
def shortestSubarray(A: Array[Int], K: Int): Int = {
  val prefixSum = A.scanLeft(0)(_+_).tail
 // val dp = Array.fill(A.length)(0)
  val indexDiff : mutable.HashMap[(Int, Int), Int] = new mutable.HashMap ()

  var i=A.length-1
  var j=0
  while(i >=0){
    indexDiff.getOrElseUpdate((i,j),prefixSum(i))
    while(j<i){
      indexDiff.getOrElseUpdate((i,j+1),prefixSum(i)-prefixSum(j)) // prefixSum(i)-prefixSum(j)
      j+=1
    }
    j=0
    i-=1
  }
  val sumUptoK = indexDiff.filter{case (key,value) => value >=K}
//  println(sumUptoK.toList.map { case (key, value) => (key._1 - key._2 ,value)})
  if(sumUptoK.isEmpty) -1 else {
    val minLen = sumUptoK.toList.minBy { case (key, value) => key._1 - key._2 }._1
    minLen._1 - minLen._2 + 1
  }
}
shortestSubarray(Array(77,19,35,10,-14),19)

//https://leetcode.com/problems/maximum-width-ramp/
def maxWidthRamp(A: Array[Int]): Int = {
  val zipped = A.zipWithIndex
  zipped.scanRight(0){case (ele,acc) =>
    val prevEle =  zipped.find(num => num._1 <= ele._1 && num._2 <= ele._2).getOrElse(0,0)
  if(ele._2-prevEle._2 > acc) ele._2-prevEle._2 else acc}.max
}

maxWidthRamp(Array(6,0,8,2,1,5))

//https://leetcode.com/problems/largest-rectangle-in-histogram/
def largestRectangleArea(heights: Array[Int]): Int = {
  val zipped = heights.zipWithIndex
  val leftMin = zipped.scanLeft(0){case (acc,ele) => (Try {zipped.take(ele._2).minBy(_._1)._2} toOption ).getOrElse(0)}
 // val rightMax =
 /* val x = zipped.scanLeft(Integer.MAX_VALUE, Integer.MIN_VALUE){case (acc,ele) =>
    (zipped.take(ele._2).minBy(_._1)._2,zipped.takeRight(zipped.length-ele._2-1).maxBy(_._1)._2)
  }.zip(zipped).map{case ((left,right),(ele,idx))=> (right+idx+1-left-1)*ele}
  println(x)
  x.max*
  */
  println(leftMin.toList)
  2

}

largestRectangleArea(Array(2,1,5,6,2,3))
//https://leetcode.com/problems/first-missing-positive/
def firstMissingPositive(a: Array[Int]): Int = {
  if(a.isEmpty || a.min > a.length) 1
  else{
    val arrMax = a.max
    val arrMin = a.min

    a.zipWithIndex.foreach(num => if(num._1>= a.length || num._1 < 0) a(num._2) = 0)
 //   println(a.toList)

    a.distinct.foreach{num => val absoluteNum = math.abs(num)
      a(absoluteNum) =  {
        if(a(absoluteNum) == 0)  -1 else if(num != -1) a(absoluteNum) * -1 else a(absoluteNum)}
      println(a.toList)
    }

  //  println(a.toList)
      a.tail.zipWithIndex.find(_._1 >= 0).getOrElse(0, if(a.length==1) {if(arrMin < 0) 0 else arrMin} else { if(arrMax == a.length) a.length else a.length-1})._2+1
  }
}

//https://leetcode.com/problems/find-pivot-index/
def pivotIndex(nums: Array[Int]): Int = {
  val prefixSum = nums.scanLeft(0){case (acc,ele) => acc+ele}.tail.zip(
    nums.scanRight(0){case (ele,acc) => ele+acc}.take(nums.length))
    .zipWithIndex.filter{case((left,right),idx) => left == right}
  println(s"prefixSum ${prefixSum.toList}")
  prefixSum.headOption.getOrElse((0,0),-1)._2
}

pivotIndex(Array(2,1,-1))



