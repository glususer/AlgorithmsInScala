package Hackerrank

/**
  * Created by shivangi on 11/11/16.
  */
object SherlockAndMiniMax {

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val lengthOfArray = input.head.toInt
      val arr = input.tail.head.split(" ").map(_.toLong).sortWith(_<_)
      val temp = input.last.split(" ").map(_.toLong).toList
      val mArray = (temp.head to temp.last).toStream
      val diff = temp.last - temp.head
      var x = 0
      var (m, minDiff) = (0L, 0L)
      while (x <= diff) {
        val (smaller, greater) = arr.partition(_ < mArray(x))
        var nearestDiff = 0L

        if (!smaller.isEmpty && !greater.isEmpty) nearestDiff = math.min(math.abs(smaller.last - mArray(x)), math.abs(greater.head - mArray(x)))
        else if (smaller.isEmpty) nearestDiff = math.abs(greater.head - mArray(x))
        else nearestDiff = math.abs(smaller.last - mArray(x))
        if (nearestDiff > minDiff) {
          m = mArray(x)
          minDiff = nearestDiff
        }
        x += 1
      }
      println(m)
    }
  }

  val stdinString = "10\n46 64 26 82 18 106 60 138 194 22\n138 167"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  SherlockAndMiniMax.main(null)

}
