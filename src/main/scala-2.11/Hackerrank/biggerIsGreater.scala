package Hackerrank

import java.io.{File, FileOutputStream, PrintWriter}

/**
  * Created by shivangi on 19/10/16.
  */
object biggerIsGreater {
  //bigger is greater

  def findIndex(z:List[Int]):Int={
    var idx = 0
    for (x <- 1 until z.length) {
      if (z(x - 1) < z(x)) idx = x - 1
    }
    idx
  }

  def helper(z: List[Int]): List[Int] = {
    if(z.length==1) z
    else if (z==z.sortWith(_>_)) z
    else {
      val (first, second) = z.splitAt(findIndex(z))
      val nextMin = second.partition(_ > second.head)._1.min
      first ::: (nextMin :: ((second diff List(nextMin)).sorted))
    }
  }
// utility to write test case output to file isntead of standard output
  def writeToFile(str:String):Unit={
    val outputFile = "/Users/piyush/Documents/workspace/testCasesHackerrank/bigger-is-greater-testcases/output/actual-output03.txt"
    val pw = new PrintWriter(new FileOutputStream(new File(outputFile),true))
    pw.write(str)
    pw.write("\n")
    pw.close  }

// uncomment the lines wrteToFile if output is to be written in a file and change the input and putput dir paths.
  def parseInput(input:List[String]) = {
    if (!input.isEmpty) {
      val noOfTestCases = input.head.toInt
      val str = input.tail
      val alphabetToNumber = ('a' to 'z').zipWithIndex.toMap
      val numberToAlphabet = Map() ++ alphabetToNumber.map(_.swap)
      for (word <- str) {
        val number = (word map alphabetToNumber).toList
        println(number)
        val nextNumber = helper(number)
        println(nextNumber)
        if (nextNumber==number) {
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

  def main(args: Array[String]) {
    /* uncomment the below code if input has to be read from file instead of std input. Change the input file dir path to the path
    from where input has to be read and also comment out the first line.*/
    val input = parseInput(io.Source.stdin.getLines().toList)
    /* val filePath = "/Users/piyush/Documents/workspace/testCasesHackerrank/bigger-is-greater-testcases/input/input03.txt"
     val input =Source.fromFile(filePath).getLines().toList
     parseInput(input)*/
  }

  val stdinString = "\n"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  biggerIsGreater.main(null)
}
