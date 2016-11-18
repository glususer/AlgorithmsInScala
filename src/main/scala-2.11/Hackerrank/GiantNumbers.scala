package Hackerrank

import scala.collection.mutable

/**
  * Created by shivangi on 12/11/16.
  */
object GiantNumbers {

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val arrayLength = input.head.toLong
      var array = input.tail.head.split(" ").map(_.toLong)
      val numQueries = input.tail.tail.head.toLong
      val queries = input.tail.tail.tail
      var y=0
     /* val hash = new scala.collection.mutable.HashMap  [Long,Long]()

      for (num<-array){
        if((hash.isDefinedAt(num))) hash(num)+=1
        else hash+=(num->1)
      }*/
      val s = array.map(x=>(x,array.count(_==x))).distinct
     /* while(y<numQueries){
        val query = queries(y).split(" ").map(_.toLong).toList.distinct
        val x = query.head
        val k = query.last
        var sum=0L
        for((key,value)<-hash){
          if(x%key==0) sum=sum+value
        }
        if(sum>=k) println("Yes")
        else println("No")
        y+=1
      }
    }*/

      while(y<numQueries){
        val query = queries(y).split(" ").map(_.toLong).toList
        val x = query.head
        val k = query.last
        var sum=0L
        if((s.filter(z=>x%z._1==0).map(z=>z._2).sum) >=k) println("Yes")
        else println("No")
        y+=1
      }
    }
  }

  val stdinString = "4\n100 200 400 100\n5\n100 2\n200 3\n500 4\n600 4\n800 4"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  GiantNumbers.main(null)

}
