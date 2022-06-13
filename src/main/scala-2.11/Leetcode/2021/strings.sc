import scala.collection.mutable
//https://leetcode.com/problems/word-break/

def wordBreakDfs(s: String, wordDict: List[String], set: Set[String]):Boolean= {
  if (s.isEmpty) true
  else if (set.contains(s)) false
  else {
    val x = wordDict.filter(word => s.startsWith(word)).sortWith{case (a,b) =>  a.length > b.length}
    println(s"x $x")
      x.foldLeft(false) { case (flag, word) =>
        if (s.startsWith(word)) {
          lazy val currentFlag = wordBreakDfs(s.substring(word.length), wordDict, set + s)
          flag | currentFlag
        } else flag
      }
  }
}
def wordBreak(s: String, wordDict: List[String]): Boolean = {
   wordBreakDfs(s, wordDict, Set.empty)
}

//wordBreak("catsandog",List("cats","dog","sand","and","cat"))
//wordBreak("applepenapple", List("apple","pen"))
//wordBreak("aaaaaa", List("a","aa","aaa"))

def binarySearchIdx(arr:IndexedSeq[Int],key:Int, left:Int, right:Int):Int={
  if(left<right){
    val mid = (right-left)/2+left
    if(arr(mid) >= key) binarySearchIdx(arr, key, left,mid)
    else binarySearchIdx(arr, key, mid+1, right)

  }else arr(left-1)
}

def traverse(charSet:Set[Char], charMap: Map[Char, IndexedSeq[Int]], str: IndexedSeq[(Char,Int)], currentIdx:Int, currentStr:String, longestStr: String):String = {
  if(currentIdx == str.length) longestStr
  else {
   // println(s"charSet $charSet, currentIdx $currentIdx currentStr  $currentStr longestStr $longestStr")
    val currentChar = str(currentIdx)._1
    val idx = str(currentIdx)._2
    if (!charSet(currentChar)) traverse(charSet + currentChar, charMap, str, currentIdx + 1, currentStr + currentChar, if(longestStr.length < (currentStr + currentChar).length)currentStr + currentChar  else longestStr)
    else {
      val idxArrOfChar = charMap.getOrElse(currentChar, Vector.empty)
      val prevIdxOfChar = binarySearchIdx(idxArrOfChar,idx , 0, idxArrOfChar.size)
      traverse(Set.empty[Char], charMap, str, prevIdxOfChar+1, "", if (longestStr.length > currentStr.length) longestStr else currentStr)
    }
  }
}

def lengthOfLongestSubstring(s: String): Int = {
 val charMap = s.zipWithIndex.groupBy{case (ch,_) => ch}.map{case(key, value) => (key,value.map(_._2))}
 // println(s"charMap $charMap")
  val longestString = traverse(Set.empty, charMap, s.zipWithIndex, 0, "","")
//  println(s"longestString $longestString")
  longestString.length
}

lengthOfLongestSubstring("aaaab")//.groupBy{case (ch,_) => ch}.map{case(key, value) => (key,value.map(_._2))}

//binarySearchIdx(IndexedSeq(1,2,5),2,0,3)