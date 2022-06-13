def modForPrime(a:Int, b:Int, m:Int, ans:Int=1):Int={
  println(s"a $a b $b m $m ans $ans")
  if(b < 1) ans
  else{
    val updatedB = if(b%2==1)(b-1)/2 else b/2
    val updatedA = math.pow(a,2).toInt%m
    val updatedAns = math.pow(modForPrime(updatedA,updatedB,m,ans),2).toInt%m
    if(b%2==0) modForPrime(updatedA, updatedB, m, updatedAns)
    else modForPrime(updatedA, updatedB, m, a*updatedAns)
  }
}

def solve(A: Int, B: Int, C: Int): Int  = {
  ???
}

modForPrime(3,20,11)