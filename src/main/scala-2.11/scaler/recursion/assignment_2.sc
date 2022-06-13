import scala.collection.mutable.ListBuffer
//Given an integer A pairs of parentheses, write a function to generate all combinations of well-formed parentheses of length 2*A.

def generateParenthesis(A: Int): Array[String]  = {
  def go(acc:ListBuffer[String], curr:String,left:Int, right:Int):Unit={
   // println(s"acc $acc curr $curr, left  $left right $right")
    if(left == 0 && right  == 0) acc.append(curr)
    else{
      if(left > 0) go(acc, curr+"(", left-1, right)
      if(right > left) go (acc, curr+")", left, right-1)
    }
  }

  val acc = ListBuffer[String]()
  go(acc, "", A, A)
  acc.toArray
}

generateParenthesis(2)