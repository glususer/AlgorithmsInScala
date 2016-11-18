package Hackerrank

/**
  * Created by shivangi on 24/10/16.
  */
object sherlockAndPairs {
  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val noOfTestCases = input.head.toInt*2
      for (x<-1 until noOfTestCases by 2){
        val l = input.tail(x).split(" ").toList.map(_.toLong)
        val numMap = scala.collection.mutable.Map[Long,Long]()
        for (ele<-l){
          if(numMap contains(ele)) numMap(ele)=numMap(ele)+1
          else numMap+=ele->1
        }
        val pairs = numMap.values.map(x=>(x * (x-1))).sum
 //       val pairs = l.map(x => (x, l.count(z => z == x))).filter(_._2>1).map(x=>(x._1,(x._2*(x._2-1)))).toMap.values.sum
        println(pairs)
      }
    }
  }

  val stdinString = "2\n3\n1 2 3\n3\n1 1 1 1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  sherlockAndPairs.main(null)
}
