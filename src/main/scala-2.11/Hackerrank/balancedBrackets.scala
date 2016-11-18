package Hackerrank

/**
  * Created by shivangi on 25/10/16.
  */
object balancedBrackets {
  //not correct
  def isBalanced(l:List[Int],count:Int):Boolean={
    (l,count) match{
      case (Nil,0)=>true
      case(Nil,m)=>false
      case(x::xs,0)=>false
      case(x::xs,m)=> {
        if(-x== xs.last) isBalanced(xs.dropRight(1),m-1)
        else false
      }
    }
  }
  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val noOfTestCases = input.head.toInt
      val brakectList = input.tail.map(_.toList)
      val mapBracketToNos = Map('{'->1,'}'-> -1,'('->2,')'-> -2,'['->3,']'-> -3)

      for (bracket<-brakectList) {
        if(bracket.length %2 != 0 || bracket.length==0) println("NO")
        else {
          if (isBalanced(bracket map mapBracketToNos,0)) println("YES")
          else println("NO")
        }
      }
    }
  }

  val stdinString = "5\n}][}}(}][))]\n[](){()}\n()\n({}([][]))[]()\n{)[](}]}]}))}(())("
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  balancedBrackets.main(null)

}
