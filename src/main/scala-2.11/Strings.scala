/**
  * Created by shivangi on 25/09/16.
  */
object Strings {

  def isomorphic(s:String,t:String):Boolean={
    if(s.length != t.length) false
    else {
      if(s.map(x=>(x,s.count(_==x))).toMap.values.toList == t.map(x=>(x,t.count(_==x))).toMap.values.toList){
        true
      }
      else false
    }
  }

  def isScrambled(original:String,scrambled:String):Boolean= {
    if (original.length != scrambled.length) false
    def helper(s: String, t: String): Boolean = {
      (s.length) match {
        case 1 => if (s == t) true else false
        case 2 => if (s == t || s == t.reverse) true else false
        case 3 => {
          val (first, second) = s.splitAt(1)
          if (s == t || s == t.reverse || t == first.concat(second.reverse) || t == second.concat(first)) true else false
        }
        case _ => if (s == t) true
        else {
          for (k <- 1 until s.length) {
            val first = helper(s.splitAt(k)._1, t.splitAt(k)._1)
            if (first && k < s.length) {
              val second = helper(s.splitAt(k)._2, t.splitAt(k)._2)
              if (second) return true
            }
            else false
          }
          false
        }
      }
    }
    helper(original,scrambled)
  }

  /* Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that
  the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
  Example 1:
  Given words = ["bat", "tab", "cat"]
  Return [[0, 1], [1, 0]]
  The palindromes are ["battab", "tabbat"]
  Example 2:
  Given words = ["abcd", "dcba", "lls", "s", "sssll"]
  Return [[0, 1], [1, 0], [3, 2], [2, 4]]
  The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

  https://leetcode.com/problems/palindrome-pairs/
   */

  def isPalindrome(s:String):Boolean={
    var j = s.length-1
    for(i<- 0 until s.length){
      if(s.charAt(i) != s.charAt(j)) return false
      j-=1
    }
    true
  }

  def palindromePairs(l:Array[String]):List[(Int,Int)]={
    {for{
      i<-0 until l.length
      j<-0 until l.length
      if(i != j && isPalindrome(l(i).concat(l(j))))
    }yield (i,j)}.toList
  }


  def interLeavings (s:String,t:String):List[String]={
    def helper(k:String,m:String,l:List[String]):List[String]= {
      (k.length, m.length) match {
        case (0, 0) => List()
        case(0,1)=>m::l
        case(1,0)=>k::l
        case(1,1)=>(k.concat(m))::l
        case(2,1)=>{
          val(first,second)=s.splitAt(1)
          k+m::m+k::first+t+second::l
        }
        case (1,2)=>  {
          val(first,second)=m.splitAt(1)
          m+k::k+m::first+k+second::l
        }
        case(_,_)=> {
          {for {
            i <- 0 until k.length
            j<-0 until m.length
            x=helper(k.drop(i + 1), m, l).map(k.charAt(i) + _)
            y=helper(m.drop(j + 1), k, l).map(m.charAt(j) + _)
          } yield helper(k.drop(i + 1), m, l).map(k.charAt(i) + _) ::: helper(m.drop(j + 1), k, l).map(m.charAt(j) + _)}.flatten.toList
        }
      }
    }
    helper(s,t,List())
  }
}
