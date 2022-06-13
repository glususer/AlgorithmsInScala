//https://www.geeksforgeeks.org/lexicographic-rank-of-a-string/


def calcFac(n:Int, acc:Int=1, mod:Int):Int={
  if(n==0) acc
  else calcFac(n-1, (acc*n)%mod,mod)
}
def findRank(A: String): Int  = {
  val charToSmallerCharMap = A.map { x => (x, A.partition(_ < x)._1.toList) }.toMap

  (A.zipWithIndex.foldLeft(0, A.length - 1) { case ((rank, noOfPositionsToBeFilled), (ele, idx)) =>
     val  smallerCharsSeenPreviously = charToSmallerCharMap(ele).count{smaller => A.take(idx).contains(smaller)}
   // println(s"rank $rank noOfPositionsToBeFilled $noOfPositionsToBeFilled smallerCharsSeenPreviously $smallerCharsSeenPreviously")
    val fac = calcFac(noOfPositionsToBeFilled,1,1000003)
    val toBeAdded =fac*(charToSmallerCharMap(ele).length-smallerCharsSeenPreviously)
    (rank+toBeAdded,
      noOfPositionsToBeFilled-1)}._1+1)%1000003
  }
findRank("DTNGJPURFHYEW")
//val s = "STRING"
//"STRING".map{x => (x, s.partition(_<x)._1.toList)}