//Given string A denoting an infix expression. Convert the infix expression into a postfix expression.
//
//String A consists of ^, /, *, +, -, (, ) and lowercase English alphabets where lowercase English alphabets are operands and ^, /, *, +, - are operators.
//
//Find and return the postfix expression of A.
def isGreaterThan(a: Char, b: Char): Boolean = {
  (a, b) match {
    case ('^','^') => false
    case ('^',_) | ('*', '+') |  ('*', '-') |  ('/', '+') | ('/', '-') => true
    case (_,_) => false
    /*case ('^','^') =>false
    case ('^',_) => true
    case (_,'^') => false
    case ('*', '*') => false
    case ('*', '/') => false
    case ('*', '+') => true
    case ('*', '-') => true
    case ('/', '*') => false
    case ('/', '/') => false
    case ('/', '+') => true
    case ('/', '-') => true
    case ('-', '*') => false
    case ('-', '/') => false
    case ('-', '+') => false
    case ('-', '-') => false
    case ('+', '*') => false
    case ('+', '/') => false
    case ('+', '+') => false
    case ('+', '-') => false*/
  }
}

def solve(A: String): String = {
  import scala.collection.mutable
  val ValueMap = Map('^'->1,'/'->2,'*'->2,'+'->3,'-'->3)

  val (operands, operator) = A.foldLeft("", mutable.Stack[Char]()) { case ((operands, operator), ch) =>
    if (ch >= 'a' && ch <= 'z')
      (operands + ch, operator)
    else if (ch == '(') {
      operator.push(ch)
      (operands, operator)
    }
    else if (ch == ')') {
      var temp = ""
      while (operator.top != '(') {
        temp = temp + operator.top
        operator.pop()
      }
      operator.pop()
      (operands + temp, operator)
    }
    else {
      var temp = ""
      while (operator.nonEmpty && operator.top != '(' && ValueMap.getOrElse(operator.top,0) <= ValueMap.getOrElse(ch,0)) {
        temp = temp + operator.top
        operator.pop()
      }
      operator.push(ch);
      (operands + temp, operator)
    }
  }
  operands+operator.mkString("")
}

println(solve("x^y/(a*z)+b") )
assert(solve("x^y/(a*z)+b") == "xy^az*/b+")

// "qct*o*+gg*+qia-*p*+il*-"
assert(solve("t+(i+y*h*v)*q*(t+m*h*f)/s/(x+n)*c-(e-y^x+d^e)-v-(v^c^l+z-v)/h*(c^w^r/a)/c-(l^n+s-u/g*t)*p+(n*j^a)*l/(a+c*n/t+d+j)/h^(j*w*k*y*o)-t-(m*c)-p/(m+b^m)*y^(a+s+h*u-z)") == "tiyh*v*+q*tmh*f*+*s/xn+/c*+eyx^-de^+-v-vc^l^z+v-h/cw^r^a/*c/-ln^s+ug/t*-p*-nja^*l*acn*t/+d+j+/hjw*k*y*o*^/+t-mc*-pmbm^+/yas+hu*+z-^*-")
assert(solve("a+b*(c^d-e)^(f+g*h)-i") == "abcd^e-fgh*+^*+i-")

'b'>='a'