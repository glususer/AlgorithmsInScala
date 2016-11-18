package Hackerrank

/**
  * Created by shivangi on 17/11/16.
  */
object AshsishHugeDonation {

  def binary(low: Double, high: Double, num: Double): Double = {
    if (high - low == 1.0) return low
    else {
      val mid = (low + (high - low) / 2).round
      val midSumOfSquare = (mid * (mid + 1) * (2 * mid + 1)) / 6
      if(midSumOfSquare==num.round) return mid
      else if (num.round > midSumOfSquare) binary(mid, high, num)
      else binary(low, mid, num)
    }
  }

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val noOfTestcases = input.head.toInt
      val l = input.tail.map(x => x.toDouble)
      for (ele <- l) {
        val high = "10000000000000000".toDouble
        val low = 1
        val highSum = math.pow(high, 1.0 / 3.0).round + 1
        println(binary(low, highSum, ele).toLong)
      }
    }

  }

  val stdinString = "3\n1\n5\n13"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  AshsishHugeDonation.main(null)

}
