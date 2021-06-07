
import scala.collection.mutable

//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
def kthSmallest(matrix: Array[Array[Int]], k: Int): Int = {
  import scala.collection.mutable

  val pq  = mutable.PriorityQueue[Int]()(Ordering.Int).reverse
  matrix.flatten.foreach(ele => pq.enqueue(ele))
  (for{
    x<- 0 until  k
    ele = pq.dequeue()
  }yield ele).last
}


kthSmallest(Array(Array(1,5,9),Array(10,11,13),Array(12,13,15)),8)
//https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
def kthSmallestSum(mat: Array[Array[Int]], k: Int): Int = {
  ???

}

//https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
object Solution {
  import scala.collection.mutable
  case class SumWithPair(total:Int, a:Int,b:Int)

  def kSmallestPairs(nums1: Array[Int], nums2: Array[Int], k: Int): List[List[Int]] = {

    val pq  = mutable.PriorityQueue.empty[SumWithPair](Ordering.by(sumWithPair =>sumWithPair.total)).reverse

    (for{
      ele1 <- nums1
      x = ele1
      ele2 <- nums2
      y = ele2
    }yield (x,y)).foreach{case (a,b) => pq.enqueue(SumWithPair((a+b),a,b))}

    if(pq.length < k) {
      val x = pq.dequeueAll
      x.map(sumWithPair => List[Int](sumWithPair.a, sumWithPair.b)).toList
    }
    else{
      (for{
        x<- 0 until  k
        ele = pq.dequeue()
      }yield ele).map(sumWithPair => List[Int](sumWithPair.a, sumWithPair.b)).toList
    }
  }
  // nums1 = Array(2,4,6), nums2 = Array(1,7,11)
  def kSmallestPairs2(nums1: Array[Int], nums2: Array[Int], k: Int):List[List[Int]] ={

    val pq  = mutable.PriorityQueue.empty[SumWithPair](Ordering.by(sumWithPair =>sumWithPair.total)).reverse

    val lists = nums1.toList.zipWithIndex.map(num1 => nums2.toList.zipWithIndex.map(num2 => SumWithPair(num1._1+num2._1,num1._2,num2._2)))
    val idxToNums = nums1.toList.zipWithIndex
      .flatMap(num1 => nums2.toList.zipWithIndex.map(num2 => ((num1._2,num2._2),(num1._1,num2._1)))).toMap

    lists.foreach(list => pq.enqueue(list.head))
    helper(lists.map(_.tail),List.empty, pq,k)
      .map{case (idx1,idx2) => idxToNums.getOrElse((idx1,idx2),(-1,-1))}
      .map{case (num1,num2) => List(num1,num2)}

  }

  def helper(lists:List[List[SumWithPair]],acc:List[(Int,Int)],pq:mutable.PriorityQueue[SumWithPair],k:Int):List[(Int,Int)] ={
    if(lists.isEmpty && k==0){
      acc
    } else if(lists.isEmpty && k > -0){
      pq.dequeueAll.take(k).toList.map(sumWithPair => (sumWithPair.a,sumWithPair.b)):::acc
    }
    acc.length match{
      case x if(k == 0) => acc
      case _ => val currentElement = pq.dequeue()
        val currentElementIdx = currentElement.a
        val eleToBeInserted = lists(currentElementIdx).headOption//.getOrElse(SumWithPair(Integer.MAX_VALUE,-1,-1))
        if (eleToBeInserted.isDefined)pq.enqueue(eleToBeInserted.get)
        helper(lists.filterNot(_.isEmpty).map(list => if(list.head.a == currentElementIdx) list.tail else list),
          acc:+(currentElement.a,currentElement.b),pq,k-1)
    }
  }
}

case class SumWithPair(total:Int, a:Int,b:Int)

//Solution.kSmallestPairs2(Array(1,4,11), Array(2), k = 3)
/*val nums1 = Array(1,4,11)
val nums2 = Array(2)
val lists = nums1.toList.zipWithIndex.map(num1 => nums2.toList.zipWithIndex.map(num2 => SumWithPair(num1._1+num2._1,num1._2,num2._2)))
val idxToNums = nums1.toList.zipWithIndex
  .flatMap(num1 => nums2.toList.zipWithIndex.map(num2 => ((num1._2,num2._2),(num1._1,num2._1)))).toMap*/

//https://leetcode.com/problems/hand-of-straights/
val W=2
def takeMin(pq:mutable.PriorityQueue[Int], acc:List[List[Int]], count:Int, currentList:List[Int]):List[List[Int]]={
  println(pq,currentList, count)
  if(pq.isEmpty) acc:+currentList
  else{
    count match{
      case 0 => takeMin(pq,acc:+currentList, W, List.empty)
      case _ =>
        var currEle = pq.dequeue()
        if(currEle > currentList.lastOption.getOrElse(-1)) takeMin(pq,acc,count-1, currentList:+currEle)
        else {
          var isEmpty = false
          val lastElem = currentList.lastOption.getOrElse(-1)
          var lstEqualOrSmallerthenCurrent : List[Int]= List.empty
          while(pq.nonEmpty && currEle <= lastElem){
            lstEqualOrSmallerthenCurrent = lstEqualOrSmallerthenCurrent:+currEle
            currEle = pq.dequeue()
            if(pq.isEmpty)
              isEmpty = true
          }
          if(isEmpty){
            if(currEle <= lastElem){
              pq.enqueue(currEle)
              takeMin(pq,acc,count,currentList)
            } else  takeMin(pq,acc,count-1,currentList:+currEle)
          }else {
            pq.enqueue(lstEqualOrSmallerthenCurrent: _*)
            takeMin(pq, acc, count - 1, currentList :+ currEle)
          }
        }
    }
  }
}

def isNStraightHand(hand: Array[Int], W: Int): Boolean = {
  if(hand.length%W != 0) false
  else{
    val pq = mutable.PriorityQueue.empty(Ordering.Int).reverse
    hand.foreach(num => pq.enqueue(num))
    val reached = takeMin(pq,List.empty, W,List.empty[Int])
    println(reached)
      val x = reached.forall(lst => lst.length ==W &&
      (lst, lst.drop(1)).zipped.forall{case (num1,num2)=> num2-num1 ==1 })
    x
  }
}

isNStraightHand(Array(0,1),2)
//    val pq  = mutable.PriorityQueue.empty[SumWithPair](Ordering.by(sumWithPair =>sumWithPair.total)).reverse
//https://leetcode.com/problems/top-k-frequent-elements/
def topKFrequent(nums: Array[Int], k: Int): Array[Int] = {
  case class numWithFreq(num:Int,freq:Int)
val pq = mutable.PriorityQueue.empty[numWithFreq](Ordering.by(numWithFreq => numWithFreq.freq))
nums.groupBy(identity).mapValues(_.length).map(x => numWithFreq(x._1,x._2)).foreach{ numFreq => pq.enqueue(numFreq)}
  (for{
    x<- 1 to k
  }yield pq.dequeue).map(_.num).toArray

}

topKFrequent(Array(1),1)


