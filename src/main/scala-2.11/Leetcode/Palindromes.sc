

//https://leetcode.com/problems/shortest-palindrome/
// reverse string and take char at a time, prepend to original string and check if it is a palindrome.
def shortestPalindrome(s: String): String = {
  if(s.length==0 || s.length == 1) s
  else {
    var sv = ""
    s.reverse.takeWhile(c => {
      val isNotPalindrome = sv + s != (sv + s).reverse
      if(isNotPalindrome) sv = sv + c
      println(s"conc ${sv}, isNotPalindrome ${isNotPalindrome}")
      isNotPalindrome
    })

    sv+s
  }
}

shortestPalindrome("")

//https://leetcode.com/problems/palindrome-pairs/

def helper(palindromeMap:List[List[Int]], currentWords:List[(String,Int)], words:Array[String]):List[List[Int]]={
  currentWords match{
  case Nil => palindromeMap
  case x::xs =>  val currentList = words.zipWithIndex.filter{word =>
    word != x && {
    val word1 = word._1+x._1
    word1 == word1.reverse} }.map(y => List(y._2,x._2)).toList
    println(currentList, x)
      helper(palindromeMap:::currentList,xs,words)
}

}
def palindromePairs(words: Array[String]): List[List[Int]] = {
  helper(List.empty, words.zipWithIndex.toList, words)
}

palindromePairs(Array("bat","tab","cat"))
