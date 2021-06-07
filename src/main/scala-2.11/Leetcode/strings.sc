import scala.collection.mutable

//https://leetcode.com/problems/permutation-in-string/
def checkInclusion(s1: String, s2: String): Boolean = {
  val x = s1.groupBy(identity).mapValues(_.length)

  s2.sliding(s1.length).toList.map { case str => str.groupBy(identity).mapValues(_.length) }
    .exists{ charCountMap => x.forall{case(key,value) => charCountMap.getOrElse(key,0) == value}
    }
}

//https://leetcode.com/problems/find-all-anagrams-in-a-string/
def findAnagrams(s: String, p: String): List[Int] = {
  val x = p.groupBy(identity).mapValues(_.length)

  s.sliding(p.length).toList.map{case str => str.groupBy(identity).mapValues(_.length)}
    .map{charCountMap => charCountMap.keySet == x.keySet &&
      x.forall{case (key,value) => charCountMap.getOrElse(key,0) == value}}.zipWithIndex
    .filter{case (done,idx)=> done }.map(_._2)
}

def isMinimumWindowMaintained(toBeDeleted: Char, sMap: Map[Char, Int], tMap: Map[Char, Int]):Boolean = {
  sMap.map{case (key,value) => if(key == toBeDeleted) (key,value-1) else(key,value)}.forall{case (key,value) => tMap.getOrElse(key,0) <=value}
}

def canHeadbeChoppedOff(acc:List[Char],sMap:Map[Char,Int],tMap:Map[Char,Int]):(Map[Char,Int], List[Char])= {
  acc match{
    case Nil => (sMap,acc)
    case x::xs if(sMap.contains(x)) => {
      if(isMinimumWindowMaintained(x,sMap,tMap)) canHeadbeChoppedOff(xs,sMap.map{case (key,value) => if(key ==x) (key,value-1) else(key,value)},tMap)
      else  (sMap,acc)
    }
    case x::xs => canHeadbeChoppedOff(xs, sMap, tMap)

  }
}

def helper(s:List[Char],charCount:Map[Char,Int],tMap:Map[Char,Int], acc:List[Char]):List[Char]={
  s match{
    case Nil=> acc
    case x::xs if (!charCount.contains(x))=> helper(xs,charCount,tMap, acc:+x)
    case x::xs =>
      val headBeChoppedOff = canHeadbeChoppedOff(acc:+x,charCount.map{case (key,value) => if(key == x) (key,value+1) else(key,value)},tMap)
      helper(xs,headBeChoppedOff._1, tMap,headBeChoppedOff._2)
  }
}

def createMinimumWindow(s:List[Char], tMap:Map[Char,Int],sMap:Map[Char,Int], acc:List[Char]):List[Char]={
  if(sMap.keySet == tMap.keySet && sMap.forall{case (key,value) => tMap.getOrElse(key,0) == value} || s.isEmpty)
    acc
  else if (tMap.contains(s.head)){
    val updatedSMap = if(sMap.contains(s.head)) sMap.map{case(key,value) => if(key == s.head) (key,value+1) else (key,value)}
    else (sMap.toList:+(s.head,1)).toMap

    createMinimumWindow(s.tail, tMap, updatedSMap,acc:+s.head )
  }  else createMinimumWindow(s.tail, tMap, sMap,acc:+s.head)
}

//https://leetcode.com/problems/minimum-window-substring/
def minWindow(s: String, t: String): String = {
 val sList = s.toList
 val tMap = t.toList.groupBy(identity).mapValues(_.length)
 val minWindow = createMinimumWindow(sList,tMap,Map.empty,List.empty)
 helper(sList.diff(minWindow),tMap,tMap,minWindow).mkString("")
}

//minWindow("ADOBECODEBANC","ABC")

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
def helperlLengthOfLongestSubstring(s:List[(Char,Int)], prevMax:Int, charSet:Map[Char,Int]):Int ={
  s match{
    case Nil => Math.max(prevMax, charSet.size)
    case x::xs if(charSet.contains(x._1))=>
      val idx = charSet.getOrElse(x._1,-1)
      helperlLengthOfLongestSubstring(xs,Math.max(prevMax, charSet.size),
        charSet.filter{case (key,value) =>
          value > idx}+x)
    case x::xs =>   helperlLengthOfLongestSubstring(xs,prevMax,charSet+x)
  }
}

def lengthOfLongestSubstring(s: String): Int = {
 helperlLengthOfLongestSubstring(s.toList.zipWithIndex,0,Map.empty)
}

lengthOfLongestSubstring("dvdf")

def maxLength(arr: List[String]): Int = {
 ???
}

//https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/
//private def find(s: String, set: Nothing): Int =  { var max: Int = 0
/*for (i <- 0 until s.length) {
  val sub = s.substring(0, i + 1)
  if (!set.contains(sub)) {
    set.add(sub)
    max = Math.max(max, 1 + find(s.substring(i + 1), set))
    set.remove(sub)
  }
}
return max
}*/

def maxUniqueSplit(s: String): Int = {
  var max = 0
  def find(str:String, set:Set[String]):Int={
    for (i <- 0 until str.length){
     val sub = str.substring(0,i+1)
      println(set, sub,max)
     if(! set.contains(sub)){
       max = Math.max(max, 1+find(str.substring(i+1),set+sub))
     }
   }
    max
  }

  find(s, Set.empty)
}

println(maxUniqueSplit("ababccc"))

//https://leetcode.com/problems/construct-k-palindrome-strings/
def canConstruct(s: String, k: Int): Boolean = {
  val noOfSingleChars = s.toCharArray.groupBy(identity).mapValues(_.length).filter{case (key,value) => value%2 ==1}
  println(noOfSingleChars)
  noOfSingleChars.size <= k && s.length >=k
}
val s = "cxayrgpcctwlfupgzirmazszgfiusitvzsnngmivctprcotcuutfxdpbrdlqukhxkrmpwqqwdxxrptaftpnilfzcmknqljgbfkzuhksxzplpoozablefndimqnffrqfwgaixsovmmilicjwhppikryerkdidupvzdmoejzczkbdpfqkgpbxcrxphhnxfazovxbvaxyxhgqxcxirjsryqxtreptusvupsstylpjniezyfokbowpbgxbtsemzsvqzkbrhkvzyogkuztgfmrprz"
canConstruct(s,5)
def helper(s1:List[Char], s2:List[Char],s3:List[Char]):Boolean={
  if(s1.length+s2.length != s3.length)false
  else {
    (s1, s2) match {
      case (Nil, Nil) => if (s3.isEmpty) true else false
      case (Nil, y::ys) => s2 == s3
      case (x::xs,Nil) => s1 == s3
      case (x :: xs, y :: ys) => if (x == s3.head && y == s3.head)
        helper(xs, y :: ys, s3.tail) || helper(x :: xs, ys, s3.tail)
      else if (x == s3.head) helper(xs, y :: ys, s3.tail)
      else if (y == s3.head) helper(x :: xs, ys, s3.tail)
      else false
    }
  }
}

/*def isInterleave(s1: String, s2: String, s3: String): Boolean = {
  helper(s1.toCharArray.toList, s2.toCharArray.toList, s3.toCharArray.toList)
}*/

def isInterleave(s1: String, s2: String, s3: String): Boolean = {
  if (s1.length + s2.length != s3.length) {
    return false
  }
  val cache : mutable.HashMap[(Int, Int), Boolean] = new mutable.HashMap ()

  cache ((s1.length, s2.length)) = true

  def go(s1Index: Int, s2Index: Int) : Boolean = {
    cache getOrElseUpdate ((s1Index, s2Index), {
      if (s1Index == s1.length) {
        s2(s2Index) == s3(s1Index + s2Index) && go(s1Index, s2Index + 1)
      } else if (s2Index == s2.length) {
        s1(s1Index) == s3(s1Index + s2Index) && go(s1Index + 1, s2Index)
      } else {
        (s1(s1Index) == s3(s1Index + s2Index) && go(s1Index + 1, s2Index)) ||
          (s2(s2Index) == s3(s1Index + s2Index) && go(s1Index, s2Index + 1))
      }
    })
  }
  go(0, 0)
}

//isInterleave("","","")

//https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
//s = "abpcplea", d = ["ale","apple","monkey","plea"]
def isValid(str1:String, str2:String):Boolean={
  if(str2.isEmpty)  true
  else if(str1.isEmpty && str2.nonEmpty) false
  else if(str1.head == str2.head) isValid(str1.tail, str2.tail)
  else isValid(str1.tail, str2)
}

//isValid("abpcplea","apple")
def findLongestWord(s: String, d: List[String]): String = {
  val x = d.filter(word => isValid(s, word))
    .sortBy(_.length)
  val maxLength = x.lastOption.getOrElse("").length
  x.filter(word => (word.length == maxLength)).sortWith(_ < _).headOption.getOrElse("")
}

findLongestWord("abpcplea", List("zxy"))

//https://leetcode.com/problems/valid-palindrome-ii/
//aabbcdbbaa

def helper(str:String, deletedOnce:Boolean):Boolean={
 // println(str)
  if(str.isEmpty || str.length == 1) true
  else if(str.head == str.last) helper(str.tail.dropRight(1),deletedOnce)
  else{
    println(s"is deleted $deletedOnce $str")
    if(! deletedOnce) helper(str.tail,true) || helper(str.dropRight(1),true)
    else false
  }
}

def validPalindrome(s: String): Boolean = {
  helper(s,false)
}

val x = validPalindrome("eeccccbebaeeabebccceea")

println(s"We can create palindrom by deleting 1 char : $x")
