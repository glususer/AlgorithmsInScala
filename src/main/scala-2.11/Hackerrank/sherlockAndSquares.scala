package Hackerrank

/**
  * Created by shivangi on 20/10/16.
  */
object sherlockAndSquares {
  //sherlock and squares. if (x<y) then sqr(x)<sqr(y) and nos between this range will be the no of perfect squares between x and y.

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val noOfTestCases =input.head.toInt
      for (x<-0 until noOfTestCases){
        val lst = input(x+1).split(" ").map(BigInt(_))
        val (from,to) = (math.ceil(math.sqrt(lst.head.toDouble)),math.floor(math.sqrt(lst.last.toDouble)))
        println((from.toLong to to.toLong ).length)
      }
    }
  }

  val stdinString = "2\n1 16\n17 24"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  sherlockAndSquares.main(null)
}