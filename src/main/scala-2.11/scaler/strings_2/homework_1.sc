//Given the array of strings A, you need to find the longest string S, which is the prefix of ALL the strings in the array.
//
//The longest common prefix for a pair of strings S1 and S2 is the longest string S which is the prefix of both S1 and S2.
//
//Example: the longest common prefix of "abcdefgh" and "abcefgh" is "abc

def loop(str1:String, str2:String, acc:String="", misMatch:Boolean = false):String={
  if(misMatch) acc
  else if(str1.isEmpty || str2.isEmpty) acc
  else{
    if(str1.head == str2.head) loop(str1.tail, str2.tail, acc:+str1.head, false)
    else loop(str1.tail, str2.tail, acc,true)
  }
}

def loopWithVars (str1:String, str2:String):String={
  var acc = ""
  var misMatch = false
  var str1Copy = str1
  var str2Copy = str2

  while(true){
    if(misMatch) return acc
    else if (str1Copy.isEmpty || str2Copy.isEmpty) return acc
    else{
      if(str1Copy.head == str2Copy.head) {
        acc = acc+str1Copy.head
        str1Copy = str1Copy.tail
        str2Copy = str2Copy.tail
      }
      else {
        str1Copy = str1Copy.tail
        str2Copy = str2Copy.tail
        misMatch = true
      }
    }
  }
  acc
}
def longestCommonPrefix(A: Array[String]): String  = {
  if (A.length == 1) A.head
  else {
    val initialResult = loopWithVars(A(0), A(1))
    (1 until A.length - 1).foldLeft(initialResult) { case (prefix, i) =>
      val currentCommonPrefix = loopWithVars(A(i), A(i + 1))
      val commonPrefixWithPrevCommon = loopWithVars(currentCommonPrefix, prefix)
      // println(s"prefix $prefix, currentCommonPrefix $currentCommonPrefix, commonPrefixWithPrevCommon $commonPrefixWithPrevCommon, i $i")
      commonPrefixWithPrevCommon
    }
  }
}

longestCommonPrefix(Array("abab", "ab","abcd"))
//loopWithVars("abab","ab")