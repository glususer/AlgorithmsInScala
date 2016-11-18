package Hackerrank

/**
  * Created by shivangi on 12/11/16.
  */
object JessieLovesCandy {

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val nAndK =input.head.split(" ").map(_.toInt)
      val n= nAndK.head
      val k = nAndK.last
      var l = input.last.split(" ").map(_.toInt).zipWithIndex.filter(z=>z._1==1)
      var time=Integer.MAX_VALUE
      for(y<-0 until  l.length-1){
        val currentTime = l(y)._2*1+((l(y+1)._2-l(y)._2)*k)
        if(currentTime < time) time = currentTime
      }
      println(time)

    /*  var (idx1,idx2,y)=(0,0,0)
      val z = l.indexOf(1)
      println(z,l.indexOf(1,z))
      for(y<-0 until l.length){
          if(l(y)==1) idx1=y

      }*/

    }
  }

  val stdinString = "3 2\n0 1 0 0 0 0 1 0 1 1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  JessieLovesCandy.main(null)

}
