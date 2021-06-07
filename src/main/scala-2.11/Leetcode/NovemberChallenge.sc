//https://leetcode.com/problems/unique-morse-code-words/
def uniqueMorseRepresentations(words: Array[String]): Int = {
  val morseCode= List(".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..")
  val lettersToMore = ('a' to 'z' zip morseCode).toMap

  words.map(word => word.map(ch => lettersToMore.getOrElse(ch,"")).mkString("")).toSet.size
}

uniqueMorseRepresentations(Array("gin", "zen", "gig", "msg"))
val m = Map('a'->1,'b'->2,'c'->3)
"abc".toCharArray.map(ch => m.getOrElse(ch,0))
