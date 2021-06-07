
//https://leetcode.com/problems/daily-temperatures/

//https://1e9.medium.com/monotonic-queue-notes-980a019d5793#bfcd
def dailyTemperatures(T: Array[Int]): Array[Int] = {
  val numTemps = T.length
  val tempsAndIndex = T.zipWithIndex

  tempsAndIndex.map { case (temp, i) =>
    val tempsToTheRight = tempsAndIndex.takeRight(numTemps - 1 - i)
    tempsToTheRight.collectFirst {
      case (rightTemp, j) if rightTemp > temp => j - i
    }.getOrElse(0)
  }
}
dailyTemperatures(Array(73, 74, 75, 71, 69, 72, 76, 73))//1, 1, 4, 2, 1, 1, 0, 0

def findUnsortedSubarray(nums: Array[Int]): Int = {
  val left = nums.scanRight(Int.MaxValue, 0){(n, d) =>
    if (n <= d._1)
      (n, d._2 + 1)
    else
      (d._1, 0)
  }.toList.dropRight(1)

  //._2

  val right = nums.drop(left.head._2).scanLeft(Int.MinValue, 0) { (d, n) =>
    if (n >= d._1)
      (n, d._2 + 1)
    else
      (d._1, 0)
  }.toList.tail

  println(left)
  println(right)//.toList)

  nums.length - left.headOption.getOrElse(0,0)._2 - right.lastOption.getOrElse(0,0)._2

}

findUnsortedSubarray(Array(0,5,3,4,1,2,3))

List(1,2,3,4,5).scanLeft(0)(_+_).tail
List(1,2,3,4,5).scanRight(0)(_+_).dropRight(1)
