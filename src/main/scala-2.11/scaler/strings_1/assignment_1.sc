import scala.collection.immutable.TreeMap
//You are given a string A of lowercase English alphabets.
// Rearrange the characters of the given string A such that there is no boring substring in A.
//
//A boring substring has the following properties:
//
//Its length is 2.
//Both the characters are consecutive, for example - "ab", "cd", "dc", "zy" etc.(If the first character is C then the next character can be either (C+1) or (C-1)).
//Return 1 if it is possible to rearrange the letters of A such that there are no boring substrings in A else, return 0.

def solve(A: String): Int  = {
  val count = Array.fill(26)(0)

  A.foldLeft(count) { case (arr, ch) =>
    val indexInAlphabetArray = ch.toInt - 'a'.toInt
    count(indexInAlphabetArray) = count(indexInAlphabetArray) + 1
    count
  }

  val len = A.length
  val (_, canBeBoring) = count.tail.foldLeft(count.head, false) { case ((prevFreq, canBeBoring), currentFreq) =>
    if (!canBeBoring) {
      if (prevFreq + currentFreq > (len + 1) / 2) (currentFreq, true)
      else (currentFreq, false)
    } else (prevFreq, canBeBoring)
  }

  if(canBeBoring) 0  else 1
}

solve("abcd")