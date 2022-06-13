import scala.collection.mutable
//Given a string A denoting an expression. It contains the following operators '+', '-', '*', '/'.
//
//Check whether A has redundant braces or not.
//
//NOTE: A will be always a valid expression and will not contain any white spaces.

def braces(A: String): Int  = {
  val operators = Seq('+','-','/','*','(')
  val (_,hasRedundantBraces) = A.foldLeft(mutable.Stack[Char](),false){case ((stck, hasRedundant),ch) =>
    if(!hasRedundant) {
      if(operators.contains(ch)){
        stck.push(ch)
        (stck, false)
      }
      else{
        if(ch == ')' ){
          if(stck.nonEmpty && stck.top == '(')
            {
              stck.pop()
              (stck,true)
            }
          else{
            while(stck.nonEmpty && stck.top != '(')
              stck.pop()
            stck.pop()
            (stck, false)
          }
        }
        else {
          (stck, false)
        }
      }
    }else(stck, hasRedundant)
  }
  if(hasRedundantBraces) 1 else 0
}
braces("(a+(a+b))")