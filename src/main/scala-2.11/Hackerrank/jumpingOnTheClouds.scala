package Hackerrank

/**
  * Created by shivangi on 19/10/16.
  */

object jumpingOnTheClouds {
  //jumping on the clouds

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val noOfClouds = input.head.toInt
      val cloudList = input.last.split(" ").map(_.toInt).toList

      def helper(clouds:List[Int],cloudsAndJumps:List[(Int,Int)]):List[(Int,Int)]={
        (clouds,cloudsAndJumps) match{
          case (y::ys,Nil)=>helper(ys,List((y+1,y+1),(y+1,y+2)).filter{x=>clouds.exists(_==x._2)})
          case(y::ys, x::xs)=> {
            if(x._2==noOfClouds-1) cloudsAndJumps
            else {
              val newPos = {
                for{
                  z<-x._2+ 1 to x._2+2
                }yield (x._1+1,z)}.toList partition{l=>xs.exists(_._2==l._2)}
              helper(ys,(xs:::newPos._2.filter{x=>clouds.exists(_==x._2)}))
            }
          }
        }
      }
      val noOfJumps = helper(cloudList.zipWithIndex.filter(x=>x._1!=1).map(x=>x._2),List()).head._1
      println(noOfJumps)
    }
  }

  val stdinString = "7\n0 0 1 0 0 1 0"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  jumpingOnTheClouds.main(null)
}
