//You are given a string A.
//
//An operation on the string is defined as follows:
//
//Remove the first occurrence of the same consecutive characters. eg for a string "abbcd", the first occurrence of same consecutive characters is "bb".
//
//Therefore the string after this operation will be "acd".
//
//Keep performing this operation on the string until there are no more occurrences of the same consecutive characters and return the final string.
import scala.collection.mutable

def solve(A: String): String  = {

  val stack = A.foldLeft(mutable.Stack[Char]()){case (stck, ch) =>
   // println(s"stck $stck")
  if(stck.nonEmpty){
    if(stck.top == ch) stck.pop()
    else stck.push(ch)
    stck
  }else {
    stck.push(ch)
    stck
  }
  }
  stack.mkString("").reverse//.foldLeft(""){case (acc, ch) => acc+ch}.reverse
}

solve("abbcbbcacx")