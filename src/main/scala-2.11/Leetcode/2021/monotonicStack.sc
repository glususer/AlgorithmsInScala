import scala.collection.mutable

def popFromStack(stck:mutable.Stack[(Int, Int)], ele:Int, acc:List[Int] = List.empty):List[Int]={
  if(stck.isEmpty || stck.top._1 >= ele)acc
  else{
    val poppedValue = stck.pop()
    popFromStack(stck, ele, acc:+poppedValue._2)
  }
}

def getDecreasingMonotonicStack(array:Array[Int]):mutable.Stack[(Int, Int)]={
  array.zipWithIndex.foldLeft(mutable.Stack[(Int, Int)]()){case(stck, (ele, idx)) =>
    if(stck.isEmpty || stck.top._1 > ele)stck.push((ele,idx))
    else{
      popFromStack(stck, ele).foreach(i => array(i) = idx)
      stck.push((ele,idx))
    }
  }
}

def dailyTemperatures(temperatures: Array[Int]): Array[Int] = {
  val setOfIndicesWithNoGreaterElement = getDecreasingMonotonicStack(temperatures).map(_._2).toSet
  //println(s"temperatures ${temperatures.toList} setOfIndicesWithNoGreaterElement $setOfIndicesWithNoGreaterElement")
  temperatures.zipWithIndex.map{case(eleJustGreaterThenIdx,idx) => if(!setOfIndicesWithNoGreaterElement.contains(idx)) eleJustGreaterThenIdx-idx else 0}
}

//https://leetcode.com/problems/next-greater-element-ii/

def toBePoppedforDecreasingStack(stack: mutable.Stack[(Int,Int)], ele:Int,acc:List[Int]=List.empty):List[Int] ={
  if(stack.isEmpty || stack.top._1 >= ele) acc
  else {
    val top = stack.pop()
    toBePoppedforDecreasingStack(stack, ele, acc:+top._2)
  }
}

def monotonicDecreasingStack(arr:Array[Int]):mutable.Stack[(Int,Int)]={
  arr.zipWithIndex.foldLeft(mutable.Stack[(Int,Int)]()){case (stck,(ele,idx)) =>
  if (stck.isEmpty || stck.top._1 > ele) stck.push((ele, idx))
  else{
    toBePoppedforDecreasingStack(stck, ele).foreach{i => arr(i) = ele}
    stck.push((ele, idx))
  }}
}
def nextGreaterElements(nums: Array[Int]): Array[Int] = {
  val circularArr = nums++nums
  val setOfIndicesWithNoGreaterElement = monotonicDecreasingStack(circularArr).map(_._2).toSet

  circularArr.take(nums.length).zipWithIndex.foreach{
    case (eleJustGreater,idx) =>
      if(!setOfIndicesWithNoGreaterElement.contains(idx)) nums(idx) =  eleJustGreater
      else nums(idx) = -1}
  nums
}

//https://leetcode.com/problems/sum-of-subarray-minimums/
def toBePoppedforIncrStack(stack: mutable.Stack[(Int,Int)], ele:Int,acc:List[Int]=List.empty):List[Int] ={
  if(stack.isEmpty || stack.top._1 <= ele) acc
  else {
    val top = stack.pop()
    toBePoppedforIncrStack(stack, ele, acc:+top._2)
  }
}

def monotonicIncreasingStackTowardsRight(arr:Array[Int],nextSmallerNumIndx:Array[Int]):mutable.Stack[(Int,Int)]={
  arr.zipWithIndex.foldLeft(mutable.Stack[(Int,Int)]()) {case(stck, (ele,idx)) =>
  if(stck.isEmpty || stck.top._1 < ele) stck.push((ele, idx))
  else{
    val toBePoppedFromStack :List[Int]= toBePoppedforIncrStack(stck, ele)
    toBePoppedFromStack.foreach(poppedIdx => nextSmallerNumIndx(poppedIdx) = idx)
    stck.push((ele, idx))
  }
  }
}

def monotonicIncreasingStackTowardsLeft(arr:Array[Int],nextSmallerNumIndx:Array[Int]):mutable.Stack[(Int,Int)]={
  arr.zipWithIndex.foldRight(mutable.Stack[(Int,Int)]()) {case((ele,idx),stck) =>
    if(stck.isEmpty || stck.top._1 < ele) stck.push((ele, idx))
    else{
      val toBePoppedFromStack :List[Int]= toBePoppedforIncrStack(stck, ele)
      toBePoppedFromStack.foreach(poppedIdx => nextSmallerNumIndx(poppedIdx) = idx)
      stck.push((ele, idx))
    }
  }
}


def sumSubarrayMins(arr: Array[Int]): Int = {
  val nextLesserEle = Array.fill(arr.length)(-1)
  val prevLesserEle = Array.fill(arr.length)(-1)
  monotonicIncreasingStackTowardsRight(arr, nextLesserEle)
  monotonicIncreasingStackTowardsLeft(arr, prevLesserEle)

  println(s"nextLesserEle ${nextLesserEle.toList}")
  println(s"prevLesserEle ${prevLesserEle.toList}")
  arr.zipWithIndex.foldLeft(0){case(acc, (ele, idx)) =>
  val left = if(nextLesserEle(idx) != -1) nextLesserEle(idx) - idx  else arr.length-idx-1
  val right = if(prevLesserEle(idx) != -1) idx - prevLesserEle(idx)   else idx
  val contribution =   ele * ((if (left > 0 && right > 0) left*right else if (left == -1 && right == -1) arr.length-1 else arr.length-1 ))
  println(s"ele $ele, idx $idx, left $left, right $right contribution $contribution")
    acc+contribution
  }
}

sumSubarrayMins(Array(3,1,2,4))
sumSubarrayMins(Array(11,81,94,43,3))

