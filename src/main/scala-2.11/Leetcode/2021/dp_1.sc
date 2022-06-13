//https://leetcode.com/problems/integer-break/
def integerBreak(n: Int): Int = {
  val dp = Array.fill(n+1){1}
  dp(0) = 0
  dp(1) = 0
  dp(2) = 1

  dp.zipWithIndex.drop(2).foldLeft(0){case(dummyAcc, (_, index)) =>
    if(index > 2)
      dp(index) = (1 until index).map(i => math.max(dp(i) * (index - i), i*(index-i))).max
    dummyAcc
  }
  dp(n)
}

//integerBreak(2)
//  val dpArr = Array.fill(A.length)(Array.fill(B.length)(0))
//https://leetcode.com/problems/longest-common-subsequence/
def longestCommonSubsequence(text1: String, text2: String): Int = {
  val dp = Array.fill(text2.length)(Array.fill(text1.length)(0))

  for(i<- text2.indices){
    for(j <- text1.indices){
      val fromAbove = if(i-1>=0) dp(i-1)(j) else 0
      val fromPrev = if(j-1>=0) dp(i)(j-1) else 0
      val fromDiag = if(i-1>=0 && j-1 >=0) dp(i-1)(j-1) else 0
      dp(i)(j) = (if(text2.charAt(i) == text1.charAt(j)) fromDiag + 1 else math.max(fromAbove , fromPrev))
    }
  }
 // println(dp.map(row => row.mkString(", ") ).mkString("\n"))

dp(text2.length-1)(text1.length-1)

}

//"bsbininm"
//"jmjkbkjkv"
longestCommonSubsequence("abcba", "abcbcba")