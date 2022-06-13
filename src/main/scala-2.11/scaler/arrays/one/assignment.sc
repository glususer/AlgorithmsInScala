def maxArr(A: Array[Int]): Int  = {
  A.foldRight(0L,A.length-1){case (_,(maxDiff, idx)) =>
    val currentmaxDiff = (idx until A.length).map{i => math.abs(A(i) - A(idx))+math.abs(i-idx)}

    (math.max(maxDiff,if(currentmaxDiff.isEmpty) 0 else currentmaxDiff.max ), idx-1)
  }._1.toInt
}

def maxArr1(A: Array[Int]): Int  = {
 val diffs =  A.foldRight(List[(Int,Int)](), 0, A.length-1) {
    case (ele, (prevDiffs, maxDiff, i)) =>
      println(s"prevDiff $prevDiffs maxDiff $maxDiff i $i  A(i) ${A(i)}")
      if (prevDiffs.isEmpty) (prevDiffs :+ (0, i), maxDiff, i - 1)
      else if (prevDiffs.length == 1) {
        val currentMaxDiff = if (maxDiff > math.abs(ele - A(i + 1))) maxDiff else ele - A(i + 1)
        (prevDiffs :+ ((ele - A(i + 1)), i + 1), currentMaxDiff, i - 1)
      }
      else {
        val (diffOne, idxOne) = (ele - A(i + 1) + prevDiffs.last._1, prevDiffs.last._2)
       // println(s" ele $ele Prev ele ${A(i+1)} prevDiff ${prevDiffs.last._1}")
        val (diffTwo, idxTwo) = (ele - A(i + 2) + prevDiffs.dropRight(1).last._1, prevDiffs.dropRight(1).last._2)

        println(s"diffOne $diffOne  diffTwo $diffTwo")
        if (math.abs(diffOne) > math.abs(diffTwo)) (prevDiffs :+ (diffOne, idxOne), if(math.abs(diffOne) > maxDiff)diffOne else maxDiff , i - 1)
        else (prevDiffs :+ (diffTwo, idxTwo), if(math.abs(diffTwo) > maxDiff) diffTwo else maxDiff, i - 1)
      }
  }._1.reverse
  println(s"diffs $diffs")
   diffs.zipWithIndex.map{case ((diff, maxIdx),currentIdx) => math.abs(diff)+math.abs(maxIdx-currentIdx)}.max
}

maxArr(Array(1,8,-9,4,-10,-5,4))
maxArr1(Array(1,8,-9,4,-10,-5,4))

def maxSubArray(A: Array[Int]): Int  = {
 val a =  A.scanLeft(0,0) { case ((maxSum, prevSum), ele) =>
   val currentSum = if((prevSum max maxSum )+ ele > 0 ) (prevSum max maxSum) + ele else ele
    (currentSum,ele)
  }.tail
  println(s"a ${a.toList}")
   a.map(_._1).max
}

maxSubArray(Array(-163, -20))
maxSubArray(Array(-2, 1, -3, 4, -1, 2, 1, -5, 4))
maxSubArray(Array(1, 2, 3, 4, -10))

def solveBeggars(A: Int, B: Array[Array[Int]]): Array[Int]  = {
  if(A == 0) Array.empty
  else {
    val totalAmount = B.foldLeft(Array.fill(A+1)(0)) { case (arr, interval) =>
      val start = interval(0)
      val end = interval(1)
      val money = interval(2)
      arr(start-1) = arr(start-1)+money
      arr(end) = arr(end)-money
      arr
    }.scanLeft(0){case (acc, ele) => acc+ele}.tail.dropRight(1)
    val x= totalAmount
    // println(s"totalAmount ${x.toList}")
    totalAmount
  }
}

solveBeggars(5,Array(Array(1, 1, 5), Array(2, 2, 5),Array(3, 4, 5) ,Array(5, 5, 5)))
