//Divide two integers without using multiplication, division and mod operator.
//
//Return the floor of the result of the division.
//
//Also, consider if there can be overflow cases i.e output is greater than INT_MAX, return INT_MAX.
//
//NOTE: INT_MAX = 2^31 - 1

@scala.annotation.tailrec
def divideHelper(A:Int, B:Int, acc:Int):Int={
  if(A<B) acc
  else divideHelper(A-B,B,acc+1)
}
def divide(A: Int, B: Int): Int  = {
  if(A>0) divideHelper(A,B,0)
  else if(A<0) -divideHelper(-A,B,0)
  else 0
}

def divide1(A: Int, B: Int): Int  = {
  var updatedA = math.abs(A.toLong)
  val updatedB = math.abs(B.toLong)
  var ans = 0
  if(updatedA > Integer.MAX_VALUE && B == 1) return A
  if(updatedA > Integer.MAX_VALUE && B == -1) return Integer.MAX_VALUE
  while(updatedA >=updatedB){
    updatedA =  updatedA-updatedB
    ans = ans+1
  }
  if(A<0 && B>0)-ans
  else if(A==0)0
  else ans
}

divide1(-2147483648,1) // expected -2147483648
divide1(-2147483648, -10000000) // expected 214
divide1(-2147483648, -1)// expected 2147483647
