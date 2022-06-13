import scala.collection.mutable
//Given a string A and a string B, find the window with minimum length in A, which will contain all the characters in B in linear time complexity.
//Note that when the count of a character c in B is x, then the count of c in the minimum window in A should be at least x.
//
//Note:
//
//If there is no such window in A that covers all characters in B, return the empty string.
//If there are multiple such windows, return the first occurring minimum window ( with minimum start index and length)


def loop(A: String, matchCount: Int, charVsFreqB: Map[Char, Int], charVsFreqA: mutable.HashMap[Char, Int], minWindow: String, i: Int, j: Int, currentCount: Int, currentWindow: String): String = {
  if (i > A.length || j > A.length && matchCount == currentCount || i > j) if (minWindow.length > currentWindow.length) currentWindow else minWindow
  else {
  //  println(s"i->$i j->$j currentCount->$currentCount matchCount->$matchCount currentWindow $currentWindow minWindow $minWindow")
    if (currentCount < matchCount) {
      if (j < A.length) {
        val present = charVsFreqA.getOrElse(A(j), 0)
        val needed = charVsFreqB.getOrElse(A(j), 0)

        charVsFreqA.update(A(j), present + 1)

        if (charVsFreqB.contains(A(j))) {

          val updatedCurrentCount = if (needed > present) currentCount + 1 else currentCount
          loop(A, matchCount, charVsFreqB, charVsFreqA, minWindow, i, j + 1, updatedCurrentCount, currentWindow + A(j))
        }
        else loop(A, matchCount, charVsFreqB, charVsFreqA, minWindow, i, j + 1, currentCount, currentWindow + A(j))
      }
      else minWindow
    }
    else {
      val present = charVsFreqA.getOrElse(A(i), 0)
      val needed = charVsFreqB.getOrElse(A(i), 0)

      charVsFreqA.update(A(i), present - 1)

      val updatedMinWindow = if (minWindow.length > currentWindow.length) currentWindow else minWindow

      if (charVsFreqB.contains(A(i)) && present <= needed)
        loop(A, matchCount, charVsFreqB, charVsFreqA, updatedMinWindow, i + 1, j, matchCount - 1, currentWindow.tail)
      else
        loop(A, matchCount, charVsFreqB, charVsFreqA, updatedMinWindow, i + 1, j, matchCount, currentWindow.tail)
    }
  }
}

def loopWithVars(A: String, matchCount: Int, charVsFreqB: Map[Char, Int]):String={
  var i=0
  var j= 0
  var currentCount = 0
  var minWindow = ('a' to 'b').mkString("")*1000000
  var currentWindow = ""
  val charVsFreqA = mutable.HashMap.empty[Char,Int]
  var flag = true

  while(i<=j && flag){
    // println(s"i->$i j->$j currentCount->$currentCount matchCount->$matchCount currentWindow $currentWindow minWindow $minWindow")
    if (currentCount < matchCount) {
      if (j < A.length) {
        val present = charVsFreqA.getOrElse(A(j), 0)
        val needed = charVsFreqB.getOrElse(A(j), 0)

        charVsFreqA.update(A(j), present + 1)
        currentWindow = currentWindow+A(j)

        if (charVsFreqB.contains(A(j))) {
          val updatedCurrentCount = if (needed > present) currentCount + 1 else currentCount
          j=j+1
          currentCount = updatedCurrentCount
         // loop(A, matchCount, charVsFreqB, charVsFreqA, minWindow, i, j + 1, updatedCurrentCount, currentWindow + A(j))
        }
        else {
          j=j+1
         // loop(A, matchCount, charVsFreqB, charVsFreqA, minWindow, i, j + 1, currentCount, currentWindow + A(j))
        }
      } else flag = false
    }
    else {
      val present = charVsFreqA.getOrElse(A(i), 0)
      val needed = charVsFreqB.getOrElse(A(i), 0)

      charVsFreqA.update(A(i), present - 1)

      val updatedMinWindow = if (minWindow.length > currentWindow.length) currentWindow else minWindow
      currentWindow = currentWindow.tail
      minWindow = updatedMinWindow

      //println(s"present $present needed $needed currentWindow $currentWindow A(i)->${A(i)}")
      if (charVsFreqB.contains(A(i)) && present <= needed) {
        currentCount = matchCount-1
      //  loop(A, matchCount, charVsFreqB, charVsFreqA, updatedMinWindow, i + 1, j, matchCount - 1, currentWindow.tail)
      }
      i=i+1
    }
  }
  //println(s"minWindow $minWindow currentWindow $currentWindow")
  if (minWindow.length > currentWindow.length && currentCount == matchCount) currentWindow else minWindow

}

def minWindow(A: String, B: String): String = {
  if (A.length < B.length) ""
  else if (A.length == B.length && A.intersect(B).length != A.length) ""
  else {
    val charVsFreq = B.groupBy(identity).mapValues(_.length)
    val dummyMinString = ('a' to 'b').mkString("")*1000000
   // val probableAnswer = loop(A, charVsFreq.values.sum, charVsFreq, mutable.HashMap.empty,dummyMinString, 0, 0, 0, "")
    val probableAnswer = loopWithVars(A, charVsFreq.values.sum, charVsFreq)
    if (probableAnswer == dummyMinString) "" else probableAnswer
  }
}
val a = "ADOBECODEBANC"
minWindow(a, "ABC")