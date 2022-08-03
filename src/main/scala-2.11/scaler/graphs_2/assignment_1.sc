import scala.collection.mutable.ListBuffer
//Given a matrix of integers A of size N x M consisting of 0, 1 or 2.
//
//Each cell can have three values:
//
//The value 0 representing an empty cell.
//
//The value 1 representing a fresh orange.
//
//The value 2 representing a rotten orange.
//
//Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom) to a rotten orange becomes rotten. Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1 instead.
//
//Note: Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.

def bfs(toVisit:List[(Int,Int)], visited:Set[(Int,Int)], matrix:Array[Array[Int]], totalTime:Int, rows:Int, cols:Int, totalOrangesConverted:Int):(Int,Int)={
  //println(s"toVisit ${toVisit} visited $visited totalTime $totalTime totalOrangesConverted $totalOrangesConverted")
  if(toVisit.isEmpty) (totalTime, totalOrangesConverted)
  else{
    val neighbours = toVisit.flatMap{ case (x,y)=> List((x+1,y),(x-1,y),(x,y+1),(x,y-1))
      .filter{case (x,y) => x >=0 && y>=0 && x<rows && y < cols && matrix(x)(y) == 1}
    }
      .filterNot(visited.contains).distinct
   // println(s"neighbours ${neighbours.toList}")
    neighbours.foreach{case (x,y) => matrix(x)(y) = 2}
    bfs(neighbours, visited++toVisit,matrix, totalTime+{if(neighbours.length>0) 1 else 0}, rows,cols, totalOrangesConverted+neighbours.length)
  }
}
def solve(A: Array[Array[Int]]): Int  = {
  var totalOrangesInMatrix = 0
  val rottenOranges :ListBuffer[(Int,Int)] = ListBuffer.empty
  val rows = A.length
  val cols = A(0).length
  for(i<- 0 until rows ){
    for(j<- 0 until cols){
      if(A(i)(j) == 2) rottenOranges+=((i,j))
      if(A(i)(j) == 1 || A(i)(j) == 2) totalOrangesInMatrix +=1
    }
  }

  val (timeTakenToTurnAllOrangesRotten, totalOrangesConverted) = bfs(rottenOranges.toList, Set.empty, A, 0, rows, cols,rottenOranges.length)
 // println(s"timeTakenToTurnAllOrangesRotten $timeTakenToTurnAllOrangesRotten totalOrangesConverted $totalOrangesConverted totalOrangesInMatrix $totalOrangesInMatrix")
  if(totalOrangesConverted == totalOrangesInMatrix) timeTakenToTurnAllOrangesRotten else -1
}

solve(Array(Array(2, 1, 1),Array(0, 1, 1), Array(1,0, 1)))