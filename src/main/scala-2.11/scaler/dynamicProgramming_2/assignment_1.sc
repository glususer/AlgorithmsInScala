case class Cell(x:Int, y:Int)

def helper(A: Array[Array[Int]], target:Cell, toVisit:List[Cell], visited:Set[Cell], numWays:Int):Int={
  //println(s"target ${target} toVisit ${toVisit} visited ${visited} numWays ${numWays} ")
  if(toVisit.isEmpty) numWays
  else{
    val neighbours = toVisit.flatMap{cell =>
      List((cell.x+1,cell.y),(cell.x, cell.y+1))
        .filter{case (x,y) => x>=0 && x <= target.x && y>=0 && y <= target.y && A(x)(y) == 0 }
    }
      .map{case (x,y) => Cell(x,y)}
    helper(A, target, neighbours.filterNot(_ == target), visited++toVisit, numWays+neighbours.count(_ == target))
  }
}

def uniquePathsWithObstacles(A: Array[Array[Int]]): Int  = {
  if(A.length==1 && A(0).length==1) {
    if(A(0)(0) == 0) 1 else 0
  }
  else if(A(0)(0) == 1) 0
  else {
    val target: Cell = Cell(A.length - 1, A(0).length - 1)
    val toVisit = List(Cell(0,0))
    helper(A, target, toVisit, Set.empty, 0)
  }
}