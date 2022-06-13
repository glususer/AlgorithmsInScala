//An arithmetic expression is given by a character array A of size N. Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
//Valid operators are +, -, *, /. Each character may be an integer or an operator.

def evalRPN(A: Array[String]): Int  = {
  import scala.collection.mutable
//["2", "1", "+", "3", "*"]
  val operators = Seq("+", "-", "*", "/")
  val stack = A.foldLeft(mutable.Stack[Int]()){case (stck, op) =>
    if(operators.contains(op)){
      val op2 = stck.pop()
      val op1 = stck.pop()
      val result = op match{
        case "-" => op1-op2
        case "/" => op1/op2
        case "*" => op1*op2
        case "+"=> op1+op2
      }

      stck.push(result)
      stck
    }else stck.push(op.toInt)
  }
  stack.top
}

evalRPN(Array("4", "13", "5", "/", "+"))