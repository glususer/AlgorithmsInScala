/**
  * Created by shivangi on 29/08/16.
  */
class HackerRank {

}

/*object Solution{

  def sumTn(n:Long): Long= {
    n%((math.pow(10,9)+7)).toLong *n%((math.pow(10,9)+7)).toLong
  }

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines.toList.map(_.toLong)
    var output=""
    if(!input.isEmpty) {
      for (e <- 1 to input.head.toInt) {
        output+=sumTn(input(e))+"\n"
      }
    }
    println(output)
  }

  val stdinString = "10\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10"
// val stdinString = "10\n2\n1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution.main(null)
}*/

/*object Solution1{
  def main(args: Array[String]) {
    val stdin = io.Source.stdin.getLines().toList
    val n = stdin(0).toInt
    for (i <- 1 to n) {
      println((stdin(i)))
    }
  }
}*/

/*object Solution{

  def fact(n:Long): Long= {
    n*fact(n-1)
  }

  def main(args: Array[String]) {
    /*val input = io.Source.stdin.getLines.toList.map(_.toLong)
    var output=""
    if(!input.isEmpty) {
      for (e <- 1 to input.head.toInt) {
        output+=sumTn(input(e))+"\n"
      }
    }
    println(output)*/
  }

  val stdinString = "10\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10"
  // val stdinString = "10\n2\n1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution.main(null)
}*/


/*object Solution{

  def find(n:Int): Int= {
    if(n%2==0) 0
    else 1
  }

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines.toArray//.map(_.split(" ")).flatMap(x=>x)
    var output=""
 //   println(input.toList)
    if(!input.isEmpty) {
      val arr=input(1).split(" ").toList.map(_.toInt).toArray
 //     println(arr.deep)
      for (e <- 0 until input(2).toInt) {
        val query=(input(e+3)).split(" ").toList.map(_.toInt)
        if(arr(query.last-1)==0 && arr(query.head-1)==2) println("Even")
        else if(arr(query.last-1)==0) println("Odd")
        else if(arr(query.head-1)%2==0) println("Even")
        else println("Odd")

      }
    }
  }

  val stdinString = "5\n1 2 7 0 8\n10\n2 4\n1 2\n2 3\n2 4\n3 5\n1 3\n3 4\n1 3\n3 5\n3 4"
  // val stdinString = "10\n2\n1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Solution.main(null)
}*/
