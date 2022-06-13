//Given an array of integers A, calculate the sum of A [ i ] % A [ j ] for all possible i, j pairs. Return sum % (109 + 7) as an output.
def solve(A: Array[Int]): Int  = {

  val elemVsFreq = A.groupBy(identity).mapValues(_.length).toArray

  elemVsFreq.foldLeft(0,0){case((sum, idx),(ele, freq)) =>

    val updatedSum =  (idx+1 until elemVsFreq.length).foldLeft(sum ){case (sum ,j)=>
      val jele = elemVsFreq(j)._1
      val jfreq = elemVsFreq(j)._2
      val prodFreq = jfreq*freq

      sum+((ele%jele)*prodFreq+(jele%ele)*prodFreq)
    }
    (updatedSum, idx+1)

  }._1
}

solve(Array(1,2,4,4,4))