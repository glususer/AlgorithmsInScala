import scala.collection.mutable
//Given two strings A and B. Each string represents an expression consisting of lowercase English alphabets, '+', '-', '(' and ')'.
//
//The task is to compare them and check if they are similar. If they are identical, return 1 else, return 0.
//
//NOTE: It may be assumed that there are at most 26 operands from ‘a’ to ‘z’, and every operand appears only once.
def getSign(A:String, i:Int):Boolean= {
  if (i == 0) true
  else {
    if (A(i - 1) == '-') false
    else true
  }
}
def helper(A:String,signArr:Array[Int]):Unit={
  val stk = mutable.Stack[Boolean]()
  stk.push(true)
  A.foldLeft(stk,0) {case ((stck,idx), ch) =>
  if(ch == '+' || ch == '-') (stck,idx+1)
   else if (ch == '('){
    if(getSign(A,idx)) stck.push(stck.top)
    else stck.push(!stck.top)
    (stck,idx+1)
  }
  else if (ch == ')'){
    stck.pop()
    (stck, idx+1)
  }
    else{
    if(stck.nonEmpty && stck.top){
      signArr(ch-'a') = if(getSign(A,idx)) 1 else -1
    }else{
      signArr(ch-'a') = if(getSign(A,idx)) -1 else 1
    }
    (stck,idx+1)
  }
  }
}
def solve(A: String, B: String): Int  = {
  val ASigns = Array.fill(26)(0)
  val BSigns = Array.fill(26)(0)

  helper(A,ASigns)
  helper(B,BSigns)
  println(s"A ${ASigns.toList}")
  println(s"B ${BSigns.toList}")
  val isSame = ASigns.zip(BSigns).forall{case (a,b) => a==b}
  println(s"isNotSame ${(ASigns.zip(BSigns)).zipWithIndex.filter{case ((a,b),idx) => a!=b}}")
  if(isSame) 1 else 0
}

solve("(a+b-c-d+e-f+g+h+i)","a+b-c-d+e-f+g+h+i")