/**
  * Created by shivangi on 16/09/16.
  */
class OperatorSearch {

  def permutationsWithRepetitions[T](input : List[T], n : Int) : List[List[T]] = {
    require(input.length > 0 && n > 0)
    n match {
      case 1 => for (el <- input) yield List(el)
      case _ => for (el <- input; perm <- permutationsWithRepetitions(input, n - 1)) yield el :: perm
    }
  }

  def covertOperatorListToString(operatorList: List[String],l:List[Int],str:String):String={
    (l,operatorList) match{
      case (x::Nil,Nil)=>str+x
      case (x::xs,y::ys)=>covertOperatorListToString(ys,xs,str+x+y)
    }
  }

  def helper(operatorList: List[String], l: List[Int],target:Int):Int={
    l match {
      case Nil=>target
      case x::xs=>{
        if(operatorList.head=="+")
          helper(operatorList.tail,xs,target+x)
        else if(operatorList.head=="-")
          helper(operatorList.tail,xs,target-x)
        else if(operatorList.head=="*")
          helper(operatorList.tail,xs,target*x)
        else {
          if(target%x==0)
            helper(operatorList.tail, xs, target / x)
          else
            0
        }
      }
    }
  }


  def solve(l:List[Int], target:Int):List[String]={
    val possibleOperatorList = permutationsWithRepetitions(List("+","-","*","/"),l.length-1)
    for {
      operatorList<-possibleOperatorList
      solution = helper(operatorList,l.tail,l.head)
      if(solution== target)
    }yield covertOperatorListToString(operatorList,l,"")
  }
}

object OperatorSearch{

  def apply: OperatorSearch = new OperatorSearch()
}
