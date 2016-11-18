package Hackerrank

/**
  * Created by shivangi on 21/10/16.
  */
object serviceLane {

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
     if (!input.isEmpty) {
       val splitInput = input.head.split(" ").map(_.toLong)
       val (length,noOfQueries)=(splitInput.head,splitInput.last)
       val road = input.tail.head.split(" ").map(_.toInt)
       for(x<-2 until input.length){
         var query = input(x).split(" ").map(_.toInt)
         var (start,end) = (query(0),query(1))
         println(road.slice(start,end+1).min)
       }
     }
  }

  val stdinString = "5 5\n1 2 2 2 1\n2 3\n1 4\n2 4\n2 4\n2 3"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  serviceLane.main(null)
}