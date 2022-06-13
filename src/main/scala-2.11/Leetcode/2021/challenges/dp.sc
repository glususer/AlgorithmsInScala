def createExcludedMap(questions: Array[Array[Int]]):Map[Int, Set[Int]]= {
  val x = questions.zipWithIndex.map{case (arr, idx) => val excludedPoints = arr(1)
    val lastIdxToBeExcluded = excludedPoints+idx
  idx -> (idx+1 to lastIdxToBeExcluded).filter(_ < questions.length).toSet
  }.toMap

  (questions.indices).map{idx => idx -> x.collect{case (key, values) if values.contains(idx) => key}.toSet}.toMap
 // Map(1->Set(0), 2->Set(0,1), 3->Set(2), 4->Set(2,3), 5->Set(2,4),6->Set(4,5))
}

def mostPoints(questions: Array[Array[Int]]): Long = {

  val dp = Array.fill(questions.length+1)(0L)
  (questions.length-1 to 0 by -1).foreach{idx =>
    val brainPower = questions(idx)(1)
    val points = questions(idx)(0)
    val prevIdx = idx+1+brainPower
    if(prevIdx < questions.length) dp(idx) = points + dp(prevIdx) max dp(idx +1)
    else dp(idx) =  dp(idx+1) max points}

 println(s"${dp.toList}")
  dp.max
}
 // [[21,5],[92,3],[74,2],[39,4],[58,2],[5,5],[49,4],[65,3]]
//mostPoints(Array(Array(3,2),Array(4,1),Array(8,3),Array(2,1),Array(1,2),Array(3,1),Array(4,2), Array(10,1)))
mostPoints(Array(Array(21,5),Array(92,3),Array(74,2),Array(39,4),Array(58,2),Array(5,5),Array(49,4),Array(65,3)))