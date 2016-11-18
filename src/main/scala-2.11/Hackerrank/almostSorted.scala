package Hackerrank

/**
  * Created by shivangi on 20/10/16.
  * NOT ACCEPTED
  */
object almostSorted {
  //input: array length, next lin contains elements of array.

  def isSwapped(l:Array[Long]):(Boolean,Int,Int)={
    var indices = List[Int]()
    for (x<-1 until l.length){
      if (l(x) < l(x-1)) {
        indices=(x-1)::indices
        indices = indices :+ x
      }
    }
    if (indices.length==2) (true,indices(0),indices(1))
    else (false,0,0)
  }

  def isReversed(l:Array[Long]):(Boolean,Int,Int)={
    var indices = List[Int]()
    for (e<-1 until l.length-1){
      if(l(e)> l(e+1) && l(e)>l(e-1)) indices = e::indices
      if(l(e)<l(e+1) && l(e)<l(e-1)) indices =indices:+e
    }
    if (indices.length==2)(true,indices(0),indices(1))
    else (false,0,0)
  }

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val l = (-1.toLong +: input.last.split(" ").map(_.toLong) :+ 10000000.toLong)
      val (swap, idx1, idx2) = isSwapped(l)
      if (swap) {
        println("yes")
        println("swap " + idx1 + " " + idx2)
      }
      else {
        val (reverse, ind1, ind2) = isReversed(l)
         if (reverse) {
          println("yes")
          println("reverse " + ind1 + " " + ind2)
        }
        else println("no")
      }
    }
  }

  val stdinString = "3\n3 1 2"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  almostSorted.main(null)
}
