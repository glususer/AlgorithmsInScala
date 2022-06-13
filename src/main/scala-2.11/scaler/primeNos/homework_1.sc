def helper(primeFactors:Array[Set[Int]], currentNum:Int):Unit={
  if(currentNum == primeFactors.length) return
  else{
    if(primeFactors(currentNum).nonEmpty) helper(primeFactors, currentNum+1)
    else{
      for(j <- currentNum until primeFactors.length){
        if(j%currentNum == 0){
          val currentSet = primeFactors(j)
          primeFactors(j)  = currentSet+currentNum
        }
      }
      helper(primeFactors, currentNum+1)
    }
  }
}
//A lucky number is a number which has exactly 2 distinct prime divisors.
//You are given a number A and you need to determine the count of lucky numbers between the range 1 to A (both inclusive).
def solve(A: Int): Int  = {
  val primeFactorsArr = Array.fill(A+1)(Set[Int]())
helper(primeFactorsArr, 2)
 // println(s"arr  ${primeFactorsArr.toList.zipWithIndex}")
  primeFactorsArr.count(s => s.size == 2)
}

solve(50000)