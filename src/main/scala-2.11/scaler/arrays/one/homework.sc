def solveQ4(A: Array[Int]): Int  = {
  val prefixSum = A.scanLeft(0){case (acc, ele) => acc+ele}.tail
  val suffixSum = A.scanRight(0){case (acc, ele) => acc + ele}.dropRight(0)

  prefixSum.zip(suffixSum).zipWithIndex.collectFirst{case ((x1,x2),idx) if (x1 == x2) => idx}.getOrElse(-1)
}

solveQ4(Array())

def solveQ3(A:Array[Int]):Int={
  A.foldLeft(A.head, A.head, 0,0,Integer.MAX_VALUE,0){case ((maxValue, minValue, maxIdx, minIdx, smallestArrayLen,currentIdx),ele) =>
  //  println(s"maxValue $maxValue, minValue $minValue maxIdx $maxIdx minIdx $minIdx smallestArrayLen $smallestArrayLen currentIdx  $currentIdx ele $ele")
  if(maxValue < ele) (ele, minValue,currentIdx, minIdx,math.abs(currentIdx - minIdx),currentIdx+1)
  else if(minValue > ele)(maxValue, ele,maxIdx, currentIdx,math.abs(currentIdx - maxIdx) ,currentIdx+1)
  else if(maxValue == ele)(maxValue, minValue,currentIdx, minIdx,if(math.abs(currentIdx - minIdx) < smallestArrayLen) math.abs(currentIdx-minIdx) else smallestArrayLen ,currentIdx+1)
  else if (minValue == ele)  (maxValue, minValue,maxIdx, currentIdx,if(math.abs(currentIdx - maxIdx) < smallestArrayLen) math.abs(currentIdx-maxIdx) else smallestArrayLen ,currentIdx+1)
  else (maxValue, minValue, maxIdx, minIdx, smallestArrayLen,currentIdx+1)
  }._5+1
}

solveQ3(Array(1))

def solveQ1(A: Array[Int]): Array[Int]  = {
 val(sum, carry) =  A.foldRight("0",1){case (ele, (sum, carry)) =>
  if (ele + carry > 9)("0"+sum,1)
  //else if(ele == 0 && carry == 0) (sum,carry)
  else ((ele+carry).toString+sum,0)
  }
  (if(carry == 0) sum else "1"+sum).toCharArray.map(_.toInt-'0').dropRight(1).span(_ == 0)._2
}

solveQ1(Array(0,0,1))

case class DiffHelper(maxIndx:Int, minIdx:Int)
def solveQ2Helper(arr:Array[Int]):DiffHelper={
 val  (maxIdx, minIdx,max, min, _) =  arr.foldLeft(0,0,arr.head, arr.head, 0)
 {case ((maxIdx, minIdx,max, min, currentIdx ), ele) =>
  // println(s"maxIdx $maxIdx minIdx  $minIdx max $max min $min currentIdx $currentIdx ele $ele")
    if(ele > max ) (currentIdx, minIdx, ele, min, currentIdx+1)
    else if(ele < min)(maxIdx, currentIdx, max, ele, currentIdx+1)
    else(maxIdx, minIdx,max, min, currentIdx+1)
  }

  DiffHelper(maxIdx, minIdx)
}

def solveQ2(A: Array[Int], B: Array[Int], C: Array[Int], D: Array[Int]): Int  = {
  val diffHelperA = solveQ2Helper(A)
  val diffHelperB = solveQ2Helper(B)
  val diffHelperC = solveQ2Helper(C)
  val diffHelperD = solveQ2Helper(D)
  println(s"diffHelperA $diffHelperA diffHelperB  $diffHelperB diffHelperC $diffHelperC diffHelperD $diffHelperD ")

  val diffs = List(diffHelperA,diffHelperB,diffHelperC,diffHelperD).map{
    indices =>
    val maxIdx = indices.maxIndx
    val minIdx = indices.minIdx

    println(s"ADiff ${math.abs(A(maxIdx)-A(minIdx))} BDiff ${math.abs(B(maxIdx)-B(minIdx))} CDiff ${math.abs(C(maxIdx)-C(minIdx))} DDiff ${math.abs(D(maxIdx)-D(minIdx))} IndexDiff ${math.abs(maxIdx-minIdx)}" )

   val maxDiffFromTheseIndices = (math.abs(A(maxIdx)-A(minIdx))
    + math.abs(B(maxIdx)-B(minIdx))
    +math.abs(C(maxIdx)-C(minIdx))
    +math.abs(D(maxIdx)-D(minIdx))
    +math.abs(maxIdx-minIdx))

      maxDiffFromTheseIndices
  }
    println(s"diffs $diffs")
   diffs.max

}

val A = Array( -3, -41, -48, -4)
val B = Array(-3, -12, -13, -43)
val C = Array(-40, -50, -38, -49)
val D = Array(-41, -25, -31, 0)
solveQ2(A,B,C,D)

//solveQ2(Array(1,2), Array(3,6),Array(10,11),Array(1,6))


