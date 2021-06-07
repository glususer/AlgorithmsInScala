

//https://leetcode.com/problems/remove-k-digits/
def helper(num: List[Int], str:List[Int],counter:Int):List[Int]= {
 println(num,str,counter)
  num match{
   case Nil => str
   case x if counter <=0 => str:::x
   case x::Nil => str:+x
   case x::y::xs if(x>y) => if(y != 0)helper(y::xs,str,counter-1) else helper(xs,str,counter-2)
   case x::y::xs if(x <= y && x !=0) => helper(y::xs,str:+x,counter)
   case x::y::xs =>   helper(xs,str,counter-2)
  }
}

def removeKdigits(num: String, k: Int): String = {
 helper(num.map(_.toString.toInt).toList,List.empty,k).mkString("")
}



removeKdigits("100200",3)
//https://leetcode.com/problems/most-profit-assigning-work/
// Memory limit exceded
def maxProfitAssignment(difficulty: Array[Int], profit: Array[Int], worker: Array[Int]): Int = {
 val diffWithProfit =  difficulty.zip(profit).filter{case (diff, _) => (diff <= worker.max)}.sortBy(_._1)
 scala.util.Sorting.quickSort(worker)

 if(diffWithProfit.isEmpty) 0
 else
   worker.map{work => {
  val doableWork = diffWithProfit.filter(diff => diff._1<=work).map(_._2)
  if(doableWork.isEmpty) 0 else doableWork.max}}.sum
}
maxProfitAssignment(Array(4,4,6,8,10),Array(10,20,30,40,50),Array(3,5,6,7))

//https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
def generateFibonacci(s :List[Int],k:Int, lastButOne:Int):List[Int]={
 s match {
  case  _::xs if (xs.last >k) => s.dropRight(1)
  case _::xs if (xs.last == k) => s
  case _::xs => generateFibonacci(s:+(lastButOne+xs.last),k,xs.last)
 }
}
def findLargestElem(l:List[Int], num:Int, count:Int):Int={
 num match{
  case 0 => count
  case _ => findLargestElem(l.filter(_<=num-l.last),num-l.last,count+1)
 }
}
def findMinFibonacciNumbers(k: Int): Int = {
 val fibo = generateFibonacci(List(1,1),k,1)
 findLargestElem(fibo,k,0)
}

findMinFibonacciNumbers(19)

//https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/

def maxEvents(events: Array[Array[Int]]): Int = {
 case class Event(startTime:Int, endTime:Int)

 def nonOverlapping(event1:Event, event2:Event):Boolean=
  event1.startTime <= event2.startTime && event1.endTime <= event2.endTime

 val eventSeq = events.map(event => Event(event.head, event.last)).sortWith((a,b) => a.endTime < b.endTime || a.endTime == b.endTime && a.startTime < b.startTime)
  println(s"eventSeq ${eventSeq.toList}")
   eventSeq.distinct.foldLeft(0,Event(0,0)){case (acc,ele) => if(nonOverlapping(acc._2,ele)) (acc._1+1,ele)
   else (acc._1,acc._2)}._1
}

maxEvents(Array(Array(1,4),Array(4,4),Array(2,2),Array(3,4),Array(1,1)))
maxEvents(Array(Array(1,1),Array(1,2),Array(1,3),Array(1,4),Array(1,5),Array(1,7),Array(1,7)))
maxEvents(Array(Array(1,2),Array(2,3),Array(3,4),Array(1,2)))
