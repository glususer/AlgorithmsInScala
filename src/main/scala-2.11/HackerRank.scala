import java.io._

import scala.io.Source
/**
  * Created by shivangi on 29/08/16.
  */
class HackerRank {

}

object Solution1 {

  def sumTn(n: Long): Long = {
    n % ((math.pow(10, 9) + 7)).toLong * n % ((math.pow(10, 9) + 7)).toLong
  }

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines.toList.map(_.toLong)
    var output = ""
    if (!input.isEmpty) {
      for (e <- 1 to input.head.toInt) {
        output += sumTn(input(e)) + "\n"
      }
    }
    println(output)
  }

  val stdinString = "10\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10"
  // val stdinString = "10\n2\n1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution1.main(null)
}

object Solution2 {
  // sum input array
  def main(args: Array[String]) {
    val stdin = io.Source.stdin.getLines().toList
    if(!stdin.isEmpty) {
      val arr = stdin(1).split(" ").toList.map(_.toInt)
      println(arr.sum)
    }
  }

  val stdinString = "5\n1 3 5 7 9"
  // val stdinString = "10\n2\n1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution2.main(null)
}

object Solution3{
  //compare the triplets

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if(!input.isEmpty){
      val alice = input.head.split(" ").map(_.toInt)
      val bob = input.last.split(" ").map(_.toInt)
      val (aliceScore,bobScore)= alice.zip(bob).filter(x=>x._1!= x._2).partition(x=>x._1>x._2)
      println(aliceScore.length,bobScore.length)
    }
  }

  val stdinString = "1 3 5 7 9\n3 3 7 10 3"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution3.main(null)
}

object Solution4{
  //a very big sum

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if(!input.isEmpty){
      val noOfElem = input.head.toInt
      val arr = input.last.split(" ").map(BigInt(_))
      println((arr.sum))

    }
  }

  val stdinString = "3\n302030102040 9018384818 79292929292 1000000001 3898989898"
  // val stdinString = "10\n2\n1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution4.main(null)
}


object Solution5{
  //diagonal difference

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if(!input.isEmpty){
      val arrSize = input.head.toInt
      val arr = Array.ofDim[Int](arrSize,arrSize)
      for(i<- 0 until arrSize){
        arr(i)=input(i+1).split(" ").map(_.toInt)
      }
      val primaryDiag = (0 until arrSize).toList.zipWithIndex
      val secondaryDiag = ((0 until arrSize).toList).zip((arrSize-1 to 0 by -1).toList)
      println(math.abs(primaryDiag.map(x=>arr(x._1)(x._2)).sum - secondaryDiag.map(x=>arr(x._1)(x._2)).sum))
    }
  }

  val stdinString = "3\n11 2 4\n4 5 6\n10 8 -12"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution5.main(null)
}

object Solution6 {
  //plus minus

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val arrSize = input.head.toInt
      val l = input.last.split(" ").map(_.toInt)
      val (negative, positive) = l.partition(_ < 0)
      val (zero, greaterThanZero) = positive.partition(_ == 0)
      println("%.6f".format((math rint ((greaterThanZero.length.toFloat / l.length.toFloat)) * 1000000) / 1000000))
      println("%.6f".format((math rint (negative.length.toFloat / l.length.toFloat) * 1000000) / 1000000))
      println("%.6f".format((math rint (zero.length.toFloat / l.length.toFloat) * 1000000) / 1000000))

    }
  }

  val stdinString = "3\n-4 4 -9 0 4 1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution6.main(null)
}

object Solution7 {
  //staircase

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val arrSize = input.head.toInt
      for(i<-0 until arrSize){
        for(j<-arrSize-1 to 0 by -1){
          if(i<j) print(" ") else print("#")
        }
        print("\n")
      }

    }
  }

  val stdinString = "07:05:45PM"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution7.main(null)
}

object Solution8 {
  //staircase

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val arrSize = input.head.toInt
      for(i<-0 until arrSize){
        for(j<-arrSize-1 to 0 by -1){
          if(i<j) print(" ") else print("#")
        }
        print("\n")
      }

    }
  }

  val stdinString = "07:05:45PM"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution8.main(null)
}

object Solution9 {
  //time conversion

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val time = input.head.split(":")
      if (time.last.endsWith("AM"))
        if(time.head.toInt==12) println("00"+":"+time.tail.head + ":" + time.last.splitAt(time.last.length - 2)._1)
        else println(time.head + ":" + time.tail.head + ":" + time.last.splitAt(time.last.length - 2)._1)
      else {
        if(time.head.toInt==12) println("12"+":"+time.tail.head + ":" + time.last.splitAt(time.last.length - 2)._1)
        else println((time.head.toInt + 12) + ":" + time.tail.head + ":" + time.last.splitAt(time.last.length - 2)._1)
      }
    }
  }

  val stdinString = "12:45:54PM"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution9.main(null)
}

object Solution10 {
  //angry professor

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val noOftestCases = input.head.toInt
      val testCases = input.tail.toArray
      for(x<- 0 until testCases.length by 2){
        val n = testCases(x).split(" ").head.toInt
        val threshold = testCases(x).split(" ").last.toInt
        val (students)=testCases(x+1).split(" ").map(_.toInt)
        if ((students.filter(_<=0).length) >= threshold) println("NO")
        else println("YES")
      }
    }
  }

  val stdinString = "2\n4 3\n-1 -3 4 2\n4 2\n0 -1 2 1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution10.main(null)
}







//actual- jkscckttaeifiksgkxm, expected -jkscckttaeifiksgkxmx, actual-tbanvjmwixr, expected-tbanvjmwixrx




