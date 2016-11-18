package Hackerrank


/**
  * Created by shivangi on 12/11/16.
  */
object Pairs {

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val nAndK =input.head.split(" ").map(_.toLong)
      val n= nAndK.head
      val k = nAndK.last
      val l = input.last.split(" ").map(_.toLong)
      val s = collection.SortedSet(l:_*)
      var pairs = scala.collection.mutable.Set[(Long,Long)]()
      for(num<-s){
          if(s.contains(num-k)) pairs+=((num-k,num))
          if(s.contains(num+k)) pairs+=((num,num+k))
      }
      println(pairs.size)
    }
  }

  val stdinString = "5 2\n1 5 3 4 2"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  Pairs.main(null)

}
