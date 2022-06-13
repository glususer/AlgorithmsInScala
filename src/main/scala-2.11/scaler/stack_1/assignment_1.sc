import scala.collection.mutable
//Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]” are correct in A.
//
//Refer to the examples for more clarity.

def solve(A: String): Int  = {
  val openingBrackets = Seq('(','[','{')

  val (stack, isValid) = A.foldLeft(mutable.Stack[Char](),true) { case ((stck, isValid), ch) =>
    if (isValid) {
      if (openingBrackets.contains(ch)) (stck.push(ch),true)
      else {
        if(stck.nonEmpty){
          val top = stck.pop()
          if(top == '[' && ch == ']') (stck,true)
          else if(top == '{' && ch =='}') (stck,true)
          else if(top == '(' && ch == ')') (stck, true)
          else (stck,false)
        }else (stck, false)
      }
    }else (stck,false)
  }
 // println(s"stack ${stack}")
  if(stack.isEmpty && isValid) 1 else 0
}

solve("{([])}")
solve("(){")
solve("()[]")
solve(")}")