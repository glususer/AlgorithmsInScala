package Hackerrank

import java.io.{File, FileOutputStream, PrintWriter}

/**
  * Created by shivangi on 19/10/16. NOT ACCEPTED
  */

object biggerIsGreaterPlan {
  // TLE for one of the test cases
  def parseInput(input:List[String]) = {
    if (!input.isEmpty) {
      val noOfTestCases = input.head.toInt
      val str = input.tail
      val alphabetToNumber = ('a' to 'z').zipWithIndex.toMap
      val numberToAlphabet = Map() ++ alphabetToNumber.map(_.swap)
      for (word <- str) {
        val number = (word map alphabetToNumber).toList
        println(number)
        val permutation = (number).permutations.toList
        if (permutation.length==1) println("no answer")
        else {
          val nextNumber = permutation.tail.head
          val flag = (nextNumber zip number).span(x => x._1 == x._2)._2.map(x => x._1 >= x._2)
          if (!flag.head) {
            println("no answer")
            //          writeToFile("no answer")
          }
          else {
            println((nextNumber map numberToAlphabet).mkString(""))
            //        writeToFile((nextNumber map numberToAlphabet).mkString(""))
          }
        }
      }
    }
  }



  def main(args: Array[String]) {
    /* uncomment the below code if input has to be read from file instead of std input. Change the input file dir path to the path
    from where input has to be read and also comment out the first line.*/
    val input = parseInput(io.Source.stdin.getLines().toList)
    /* val filePath = "/Users/piyush/Documents/workspace/testCasesHackerrank/bigger-is-greater-testcases/input/input03.txt"
     val input =Source.fromFile(filePath).getLines().toList
     parseInput(input)*/
  }

  val stdinString = "5\nab\nbb\nhefg\ndhck\ndkhc"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  biggerIsGreaterPlan.main(null)
}
