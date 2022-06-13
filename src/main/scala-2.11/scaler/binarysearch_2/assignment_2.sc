//long l = Math.min(num1,num2);
//long r = (long) (1l * N * Math.max(num1,num2));
//
//logic -
//mid/num1 + mid/num2 - mid(LCM (num1,num2)
//You are given three positive integers, num1, num2, and C.
//Any positive integer is magical if divisible by either num2 or C.
//Return the Ath smallest magical number. Since the answer may be very large,
// return modulo 109 + 7.
def calcGCD(a:Int, b:Int):Int={
  if(b == 0) a
  else calcGCD(b,a%b)
}

def lcm(a:Int,b:Int):Int=
  (a*b)/calcGCD(a max b, a min b)

def arghhhBinarySearchAgain(left:Long, right:Long, key:Int,num1:Int, num2:Int, lcm:Int):Long={
  if(left<right){
    val mid = ((right-left)/2)+left
    val condition = (mid/num1)+(mid/num2)-mid/lcm
    if(condition < key) arghhhBinarySearchAgain(mid+1,right, key,num1,num2,lcm)
    else arghhhBinarySearchAgain(left,mid,key,num1,num2,lcm)
  }else left
}

def solve(A: Int, B: Int, C: Int): Int  = {
  ((arghhhBinarySearchAgain(C min B, 1l*A* (C max B),A,C,B,lcm(C,B)))%1000000007).toInt
}

solve(14,10,12)