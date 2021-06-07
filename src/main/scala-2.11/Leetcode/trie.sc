import scala.collection.mutable
import scala.math.Ordering

//https://leetcode.com/problems/top-k-frequent-words/
//  val pq = mutable.PriorityQueue.empty[ListNode](Ordering.by(node => node.x)).reverse
def repeatedPartition(l:List[(String,Int)],acc:List[List[(String,Int)]]):List[List[(String, Int)]]={
 l match{
  case Nil => acc
  case x::Nil => acc:+List(x)
  case x::y::xs => if(x._2 == y._2){
   val (left,remaining )= l.partition{ case (word,freq) => freq == x._2}
   repeatedPartition(remaining, acc:+left)
  } else repeatedPartition(y::xs,acc:+List(x))
 }
}


def dequeue(pq: mutable.PriorityQueue[(String, Int)], counter: Int, l: List[(String, Int)]): List[(String, Int)] = {
 println(counter, l)
 if (counter == 0) l
 else{
  val prevEle = l.lastOption.getOrElse("",0)
  val currEle = pq.dequeue()

  if(prevEle._2 == currEle._2) dequeue(pq,counter, l:+currEle)
  else{
   if(l.isEmpty)dequeue(pq,counter, l:+currEle)
   else {
    pq.enqueue(currEle)
    if(counter)
    dequeue(pq,counter-1, l:+currEle)
   }
  }
 }
}

def topKFrequent(words: Array[String], k: Int): List[String] = {

 var pq = mutable.PriorityQueue.empty[(String,Int)](Ordering.by{case(word,freq)=> freq})
  words.groupBy(identity).mapValues(_.length).foreach{case (word,freq) => pq.enqueue((word,freq))}
  val topKWords = dequeue(pq, k, List.empty)
 println(topKWords)
  repeatedPartition(topKWords.toList,List.empty).flatMap{lst => lst.sorted}.map(_._1)

}

topKFrequent(Array("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is") ,4)
