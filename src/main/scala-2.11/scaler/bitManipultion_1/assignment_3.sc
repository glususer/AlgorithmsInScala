def prependZeroes(n:String, numOfZeroes:Int):String={
  (0 until numOfZeroes).map(_ => 0).mkString("")+n
}
def addBinary(A: String, B: String): String  = {
  val (updatedA, updatedB) = if(A.length > B.length)(A, prependZeroes(B, A.length-B.length)) else (prependZeroes(A, B.length-A.length),B)
  val (carry, sum) = updatedA.indices.reverse.foldLeft('0',""){case ((carry,sum),idx) =>
    val (currentCarry, currentSum) = (updatedA(idx), updatedB(idx), carry) match {
      case ('1', '1', '1') => ('1', '1')
      case ('0', '0', '1') | ('0', '1', '0') | ('1', '0', '0') => ('0', '1')
      case ('0', '0', '0') => ('0', '0')
      case _ => ('1', '0')
    }
    (currentCarry, currentSum.toString+sum)
  }

  if(carry == '1') carry.toString+sum else sum
}
val x = addBinary("1000","1000")
println(s"sum is ${Integer.parseInt(x,2)}")

4^4^4