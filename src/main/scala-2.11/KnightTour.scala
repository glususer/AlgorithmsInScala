

/**
  * Created by shivangi on 02/09/16.
  */
class KnightTour {
  def isTourFinished(visited:List[(Int,Int)]):Boolean={
    if(visited.distinct.length==64) true
    else false
  }

  def canMoveAhead(moves:List[(Int,Int)],visited: List[(Int,Int)]):Boolean={
    for(move<-moves)
      if(!visited.contains(move)) return true
    return false
  }

  def moveAheadPos(moves:List[(Int,Int)],visited:List[(Int,Int)]):List[(Int,Int)]={
    moves.filter(!visited.contains(_))
  }

  def immediateNeighbors(x:Int,y:Int):List[(Int,Int)]={
    val north1 = (x-2,y-1)
    val north2 = (x-2,y+1)
    val south1=(x+2,y-1)
    val south2 =(x+2,y+1)
    val west1= (x-1,y-2)
    val west2 = (x+1,y-2)
    val east1= (x-1,y+2)
    val east2= (x+1,y+2)
    val neighbors:List[(Int,Int)]=north1::north2::east1::east2::south1::south2::west1::west2::Nil

     def isValid(x:Int,y:Int):Boolean={
      if(x>=0 && x<=7 && y>=0 && y<=7) true
      else false
    }
    neighbors.filter(x=>isValid(x._1,x._2))
  }

  def solve(visited:List[(Int,Int)]) = {
    def solve2(visited:List[(Int,Int)],probableMoves:List[List[(Int,Int)]]):List[(Int,Int)]={
      if(isTourFinished(visited)) visited
      else{
        println(visited)
        if(canMoveAhead(probableMoves.head,visited)){
          val z = moveAheadPos(probableMoves.head,visited)
          z match {
            case(x::xs)=>solve2(x :: visited, immediateNeighbors(x._1,x._2) :: probableMoves)
          }
        }
        // backTrack, no valid position available
        else{
          probableMoves.tail.head match{
            case(x::Nil)=>solve2(visited.tail,probableMoves.tail.tail)
            case(x::xs)=>solve2(visited.tail,probableMoves.tail.head.tail::probableMoves.tail.tail)
          }
        }
      }
    }
    val moves = (solve2(List((0,0)),List(immediateNeighbors(0,0))))
    println(moves.distinct.length)
    for(move<-moves)
      println(move)
  }
}

class Position(x:Int,y:Int){
  val row :Int=this.x
  val col :Int=this.y

  override def toString: String = x+" "+y
}

object KnightTour{
  def apply: KnightTour = new KnightTour()
}
