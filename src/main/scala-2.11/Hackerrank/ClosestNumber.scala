package Hackerrank

/**
  * Created by shivangi on 08/11/16.
  * NOT ACCEPTED TLE
  */
object ClosestNumber {

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val noOfTestCases =input.head.toInt
      for (y<-0 until noOfTestCases){
        val lst=input.tail(y).split(" ").map((_).toLong)
        val (a,b,x) = (lst.head,lst.tail.head,lst.last)
        val ab = math.pow(a,b)
        if (a==1 || b==0) {
          if(x==1) println(1) else println(0)
        }
        else if (b<0) println(0)
        else if(a==x) println(ab.toLong)
        else {
          val mod = ab%x
          if(mod > x/2)println((ab+(x-mod)).toLong) else println((ab-mod).toLong)
        }
      }
    }
  }

  val stdinString = "3\n349 10 4\n395 1 7\n4 -2 2"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  ClosestNumber.main(null)

}
