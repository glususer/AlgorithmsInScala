def calcGCD(a:Int, b:Int):Int={
  if(b == 0) a
  else{
    calcGCD(b,a%b)
  }
}
def solve(A: Array[Int]): Int  = {
  val prefixGCD = A.scanLeft(A.head){case(gcd,ele) => calcGCD(ele, gcd)}.tail
  val suffixGCD = A.scanRight(A.last){case (ele, gcd) => calcGCD(ele, gcd)}.dropRight(1)
 /* println(s"array ${A.toList}")
  println(s"prefix ${prefixGCD.toList}")
  println(s"suffix ${suffixGCD.toList}")*/
  A.indices.foldLeft(1){case (gcd, idx) =>
    val gcdExcludingCurrentEle = calcGCD(if (idx < 1) A(1) else prefixGCD(idx-1),
      if(idx>A.length-2) A(A.length-2) else suffixGCD(idx+1))
  if (gcd < gcdExcludingCurrentEle)  gcdExcludingCurrentEle else gcd
  }
}

solve(Array(14, 17, 28, 70))
