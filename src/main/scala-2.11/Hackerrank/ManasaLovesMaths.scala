package Hackerrank

/**
  * Created by shivangi on 10/11/16. ACCEPTED
  */
object ManasaLovesMaths {

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val noOfTestCases =input.head.toInt
      val l = input.tail
      for(num<-l){
        if(num.length==1){
          if(num.toInt %8==0) println("YES") else println("NO")
        }
        else if(num.length<3) {
          val combi = num.permutations.toList.map(_.toInt).filter(_ % 8 == 0)
          if (combi.length > 0) println("YES")
          else println("NO")
        }
        else {
          val combi = num.combinations(3).toList.map(_.permutations).flatMap(x=>x).map(_.toInt).filter(_%8==0)
          if (combi.length > 0) println("YES")
          else println("NO")
        }
      }
    }
  }

  val stdinString = "11\n6\n51003309490753470524125270008606806367328593031305727464566556358\n90\n756995491723225162730517\n85\n79469\n19925032577740588113287839777652765270\n73\n2244622737144073751525292683302638291496220820215599210588030\n20\n34"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  ManasaLovesMaths.main(null)

}
