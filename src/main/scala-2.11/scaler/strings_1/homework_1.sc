//Given 2 strings A and B of size N and M respectively consisting of lowercase alphabets,
// find the lexicographically smallest string that can be formed by concatenating non-empty prefixes of A and B (in that order).
//Note: The answer string has to start with a non-empty prefix of string A followed by a non-empty prefix of string B.


def loop (A:String, B:String, acc:String):String={
  if(A.isEmpty) acc+B.head
  else if (B.isEmpty) acc
  else{
    if(A.head.toInt < B.head.toInt)loop(A.tail, B, acc+A.head)
    else if(A.head.toInt ==B.head.toInt) if(acc.isEmpty) ""+A.head+B.head else acc+B.head
    else acc+B.head
  }
}
def smallestPrefix(A: String, B: String): String  = {
  if(A.head.toInt > B.head.toInt) A.head+A.tail.span(ch => ch < B.head)._1+B.head
  else loop(A,B,"")
}
smallestPrefix("twvvsl", "wtcyawv")