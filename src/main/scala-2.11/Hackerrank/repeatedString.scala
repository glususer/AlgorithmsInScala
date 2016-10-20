package Hackerrank

/**
  * Created by shivangi on 19/10/16.
  */

object repeatedString {
  //repeated string

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val str = input.head
      val repetition = BigInt(input.last)
      val noOfa:BigInt = BigInt(str.filter(_=='a').length)*(repetition/str.length)
      val remainder:BigInt = (repetition%str.length)
      if(remainder==0) println(noOfa)
      else {
        var x=0
        var totalNoOfaInRemainder=0
        while(x<remainder){
          if(str(x) == 'a') totalNoOfaInRemainder+=1
          x+=1
        }
        val totalNoOfa:BigInt = totalNoOfaInRemainder+noOfa
        println(totalNoOfa)
      }
    }
  }

  val stdinString = "cfimaakj\n554045874191"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  repeatedString.main(null)
}
