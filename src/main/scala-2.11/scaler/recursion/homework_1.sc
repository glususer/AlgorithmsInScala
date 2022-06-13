//Given two integers A and B, return all possible combinations of B numbers out of 1 2 3 ... A.
//
//Make sure the combinations are sorted.
//
//To elaborate,
//
//Within every entry, elements should be sorted. [1, 4] is a valid entry while [4, 1] is not.
//Entries should be sorted within themselves.
def combineHelper(A:Int, remaining:Int, acc:List[List[Int]]):List[List[Int]]={
  if (remaining == 0)acc
  else{
    val updatedAcc = acc.flatMap{comb => (comb.last+1 to A).map(lastEle => comb:+lastEle)}
    combineHelper(A, remaining-1,updatedAcc)
  }
}
def combine(A: Int, B: Int): Array[Array[Int]]  = {
  if(B==0) Array.empty
  else combineHelper(A, B-1, (1 to A).map(ele => List(ele)).toList).map(_.toArray).toArray
}

val x = combine(4,0)

x.foreach(arr => println(arr.toList))


