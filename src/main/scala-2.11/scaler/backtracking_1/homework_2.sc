//Given a digit string A, return all possible letter combinations that the number could represent.
//A mapping of digit to letters (just like on the telephone buttons) is given below.
//The digit 0 maps to 0 itself. The digit 1 maps to 1 itself.
//NOTE: Make sure the returned strings are lexicographically sorted.

def letterCombinations(A: String): Array[String]  = {
  val map = Map('2'->"abc",'3'->"def",'4'->"ghi",'5'->"jkl",'6'->"mno",'7'->"pqrs",'8'->"tuv",'9'->"wxyz")
  import scala.collection.mutable.ListBuffer

  val acc = ListBuffer.empty[String]

  def dfs( digits:String, index:Int,sb:String): Unit = {
   // println(s"digits $digits index $index sb $sb acc $acc")
    if(digits.length == index) acc+=sb
    else {
      val chars = map.getOrElse(digits.charAt(index),"")
      for(i<- chars.indices){
        dfs(digits,index+1,sb+chars.charAt(i))
      }
    }
  }
  dfs(A,0,"")
  acc.toArray
}

letterCombinations("23")

