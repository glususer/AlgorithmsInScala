package Hackerrank

/**
  * Created by shivangi on 19/10/16.
  */


object findDigits {
  //find digits

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val noOftestCases = input.head.toInt
      val numbers = input.tail.map(BigInt(_))
      for(num<-numbers){
        var count=0
        var temp = num
        while(temp>0){
          val divisor = temp%10
          if(divisor!=0) {
            val remainder = num%divisor
            if(remainder==0) count+=1
          }
          temp = temp/10
        }
        println(count)
      }
    }
  }

  val stdinString = "2\n12\n1012834616"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  findDigits.main(null)
}
