def calcGCD(a:Int, b:Int):Int={
  if(b == 0) a
  else{
    calcGCD(b,a%b)
  }
}

def gcd(A: Int, B: Int): Int  = {
  calcGCD(A max B, A min B)
}