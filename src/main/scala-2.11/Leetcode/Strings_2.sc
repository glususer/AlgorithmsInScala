//https://leetcode.com/problems/longest-palindromic-substring/
def  palindromeHelper(s:String, i:Int,j:Int):String={
 // println(s"i $i and j is $j and s is $s")
  if(i >=  0 && j <= s.length-1){
    if(s.charAt(i) == s.charAt(j)) palindromeHelper(s,i-1,j+1)
    else s.subSequence(i+1,j).toString
  }
  else {
    if(j > s.length-1 && i < 0)s
    else if(j > s.length-1) s.subSequence(i+1,j).toString
    else if (i < 0) s.subSequence(0,j).toString
  else s.subSequence(i,j+1).toString
  }
}

def longestPalindrome(s: String): String = {
 s.zipWithIndex.foldLeft(""){case (acc, ele) =>
 val str1 = palindromeHelper(s,ele._2, ele._2)
 val str2 = palindromeHelper(s,ele._2, ele._2+1)
  // println(s"str1 is $str1 and str2 is $str2")
 if(str1.length > str2.length && str1.length > acc.length) str1
 else if(str2.length > str1.length && str2.length > acc.length) str2
 else acc
 }
}
longestPalindrome("babad")


//palindromeHelper("daabaad",6,7)

//palindromeHelper("a",0,1)

//https://leetcode.com/problems/add-binary/
def add(a: String, b: String, carry: String): (String, String) = {
  println(s"a is $a, b is $b and carry is $carry")
  (a.toInt, b.toInt, carry.toInt) match {
    case (0, 0, 0) => ("0", "0")
    case (0, 1, 0) | (1, 0, 0) | (0, 0, 1) => ("1", "0")
    case (1, 1, 1) => ("1", "1")
    case (1, 1, 0) | (1, 0, 1) | (0, 1, 1) => ("0", "1")
  }
}

def addBinary(a: String, b: String): String = {
  val (longer, shorter) = if (a.length >= b.length) (a, b) else (b, a)
  val indices = (0 until (longer.length)).reverse
  val (totalSum, carry, _) = longer.zip(indices).foldRight("", "0", shorter) { case (ele, (totalSum, carry, second)) =>

    if (second.nonEmpty) {
      val (currentSum, currentCarry) = add(ele._1.toString, second.last.toString, carry)
      (currentSum + totalSum, currentCarry, second.dropRight(1))
    }
    else {
      val (currentSum, currentCarry) = add(ele._1.toString, "0", carry)
      (currentSum + totalSum, currentCarry, "")
    }
  }

  if (carry == "1") carry + totalSum else totalSum
}

addBinary("100","110010") //"110110"
