package Hackerrank

/**
  * Created by shivangi on 19/10/16.
  */

object CuttingPaperSquares {
  //Cutting Paper Squares

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val lst =input.head.split(" ").map(BigInt(_))
      println((lst.head-1)+((lst.last-1)*lst.head))
    }
  }

  val stdinString = "12 12"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  CuttingPaperSquares.main(null)
}
