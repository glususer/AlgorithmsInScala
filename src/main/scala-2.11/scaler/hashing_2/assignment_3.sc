//Given a string B, find if it is possible to re-order the characters of the string B so that it can be represented as a concatenation of A similar strings.
//
//Eg: B = aabb and A = 2, then it is possible to re-arrange the string as "abab" which is a concatenation of 2 similar strings "ab".
//
//If it is possible, return 1, else return -1.

def solve(A: Int, B: String): Int  = {
  if(B.isEmpty) -1
  else {
    val charVsFreq = B.groupBy(identity).mapValues(_.length)
    val isPossible = charVsFreq.forall { case (ch, freq) => freq % A == 0 }
    if (isPossible) 1 else -1
  }
}