//There are N players, each with strength A[i]. when player i attack player j, player j strength reduces to max(0, A[j]-A[i]). \
// When a player's strength reaches zero, it loses the game, and the game continues in the same manner among other players until only 1 survivor remains.

def calcGCD(a:Int, b:Int):Int={
  if(b == 0) a
  else{
    calcGCD(b,a%b)
  }
}

def solve(A: Array[Int]): Int  = {
  if(A.length == 1) A(0)
  else{
    A.drop(2).foldLeft(calcGCD(A(0) max A(1), A(0) min A(1))){case  (gcd, ele) =>calcGCD(gcd max ele, gcd min ele)
    }
  }
}
solve(Array(2,3))

