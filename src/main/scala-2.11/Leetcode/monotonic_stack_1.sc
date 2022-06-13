import scala.collection.mutable
import scala.collection.Seq
import scala. collection.immutable.Seq

def getPoppedValues(stck: mutable.Stack[(Int, Int)], ele: Int,acc:List[(Int,Int)] = List.empty)(comparisonFn:(Int,Int) => Boolean):List[(Int,Int)] = {
  if(stck.isEmpty || comparisonFn(stck.top._1, ele)) acc
  else {
    val popped = stck.pop()
    getPoppedValues(stck, ele, acc:+popped)(comparisonFn)
  }
}

def getPrevBiggerArr(arr:Array[Int], biggerArray:Array[Int]):Unit={
  arr.zipWithIndex.foldRight(mutable.Stack[(Int,Int)]()){case ((ele,idx), stck) =>
  if(stck.isEmpty || stck.top._1 > ele) stck.push((ele,idx))
  else{
    val toBePoppedfromDecStck = getPoppedValues(stck, ele)((x,y) => x >= y)
    toBePoppedfromDecStck.foreach{case (_,poppedIdx) => biggerArray(poppedIdx) = idx}
    stck.push((ele,idx))
  }
  }
}

def getNextSmallerArr(arr:Array[Int], smallerArr:Array[Int]):Unit={
  arr.zipWithIndex.foldLeft(mutable.Stack[(Int,Int)]()){case(stck, (ele,idx)) =>
   // println(s"stck  $stck")
  if(stck.isEmpty || stck.top._1 < ele) stck.push((ele,idx))
    else{
    val toBePoppedFromIncStck = getPoppedValues(stck, ele)((x,y) => x <= y)
   // println(s"toBePoppedFromIncStck $toBePoppedFromIncStck for ele $ele, idx  $idx")
    toBePoppedFromIncStck.foreach{case (_,poppedIdx) => smallerArr(poppedIdx) = idx}
    stck.push((ele,idx))
  }
  }
}

def goodDaysToRobBank(security: Array[Int], time: Int): List[Int] = {
  if (time == 0) (security.indices.toList)
  else {
    val nextSmaller = Array.fill(security.length)(-1)
    nextSmaller.zipWithIndex.foreach { case (ele, idx) => nextSmaller(idx) = idx }
    val prevBigger = Array.fill(security.length)(-1)
    prevBigger.zipWithIndex.foreach { case (ele, idx) => prevBigger(idx) = idx }

    getNextSmallerArr(security, nextSmaller)
    getPrevBiggerArr(security, prevBigger)
     println(s"nextSmaller ${nextSmaller.toList}")
     println(s"prevBigger ${prevBigger.toList}")
    security.zipWithIndex.filter { case (ele, idx) =>
      val preBiggerIdx = prevBigger(idx)
      val nextSmallerIdx = nextSmaller(idx)
      idx - preBiggerIdx >= time && nextSmallerIdx - idx - 1 >= time
    }.map { case (_, idx) => idx }.toList
  }
}

//goodDaysToRobBank(Array(4,3,2,1),1)
//goodDaysToRobBank(Array(1,1,1,1),0)
goodDaysToRobBank(Array(1,2,5,4,1,0,2,4,5,3,1,2,4,3,2,4,8),2) // expected (5, 10, 14)


/*val arr = Array(5,3,3,3,5,6,2)
val nextSmaller = Array(0,1,2,3,4,5,6)
val prevBigger = Array(0,1,2,3,4,5,6)

getNextSmallerArr(arr, nextSmaller)
println(s"nextSmaller Indices ${nextSmaller.toList}")

getPrevBiggerArr(arr, prevBigger)

println(s"prevBigger indeices ${prevBigger.toList}")*/
