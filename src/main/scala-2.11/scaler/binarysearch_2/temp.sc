val y = Array(1,1,2,2,3)
val windowSize = 4
val k = 4
y.zipWithIndex.foldLeft(0, Integer.MAX_VALUE) {case ((prevSum, minNum),(ele,idx)) =>
if(idx+windowSize <= y.length) {
  val actualSum = if(idx == 0) y.take(windowSize-1).sum else prevSum - y(idx - 1) + y(windowSize + (idx - 1))
  val penultimateNum = y(windowSize + (idx - 2))
  val expectedSum = penultimateNum * (windowSize - 1)
  val lastNum = y(windowSize+(idx-1))
  //println(s"ele $ele i $idx prevSum $prevSum actualSum ${actualSum+k}  expectedSum $expectedSum ${windowSize + (idx - 1)} minNum $lastNum")
  if (actualSum <= expectedSum + k) (actualSum, minNum min lastNum )
  else (actualSum, minNum)
}else (prevSum, minNum)
}