/**
  * Created by shivangi on 06/09/16.
  */
class Sudoku(board: Array[Array[Int]]) {

  def isUsedinGrid(num: Int, startXIdx: Int, startYIdx: Int): Boolean = {
    val gridX = startXIdx - startXIdx % 3
    val gridY = startYIdx - startYIdx % 3
    for (x <- gridX to gridX + 2) {
      for (y <- gridY to gridY + 2) {
        if (board(x)(y) == num) return true
      }
    }
    false
  }

  def isUsedInCol(num: Int, yIdx: Int): Boolean = {
    for (x <- 0 to 8) {
      if (board(x)(yIdx) == num)
        return true
    }
    false
  }

  def isSolved(): Boolean = {
    val z = for {
      row <- board
      num <- row
      if (num == 0)
    } yield (false)
    z.headOption.getOrElse(true)
  }

  def cellIsEmpty(x: Int, y: Int): Boolean = {
    if (board(x)(y) == 0) true
    else false
  }

  def isUsedInRow(num: Int, xIdx: Int): Boolean = {
    for (y <- 0 to 8) {
      if (board(xIdx)(y) == num) return true
    }
    false
  }

  def possibleSol(xIdx: Int, yidx: Int): List[Int] = {
    (1 to 9).toList.filter(z => (!(isUsedInCol(z, yidx) || isUsedInRow(z, xIdx) || isUsedinGrid(z, xIdx, yidx))))
  }

  override def toString: String = {
    var output: String = "\n"
    for (x <- 0 to 8) {
      for (y <- 0 to 8) output = (board(x)(y) + " ")
      output += "\n"
    }
    output
  }

  def canPlace(num: Int, x: Int, y: Int): Boolean = {
    /*if(!(isUsedInCol(num, y) || isUsedInRow(num, x) || isUsedinGrid(num, x, y))){
      updateBoard(num,x,y)
      true
    } else false*/
    updateBoard(num, x, y)
    true
  }

  def updateBoard(soln: Int, x: Int, y: Int) = {
    board(x)(y) = soln
  }

  /*def solve():String={
    def helper(positionsExplored:List[Int]):List[Int]= {
      if(isSolved()) positionsExplored
      else{
        for{
          row<-0 to 8
          col<-0 to 8
          soln<- possibleSol(row,col)
          if(cellIsEmpty(row,col) && possibleSol(row,col).nonEmpty && canPlace(soln,row,col))
          _=println(row,col,soln,possibleSol(row,col))
        } yield soln
    }.toList
  }
    helper(List())
    println(toString)
    this.toString
  }*/

  def solve(): String = {
    def solve2(explored: List[Int], visited: List[(Int, Int)], x: Int, y: Int, possible: List[List[Int]]): List[Int] = {
      if (isSolved()) explored
      else if (cellIsEmpty(x, y) || visited.contains((x, y))) {
        if (visited.contains(x, y)) {
          possible.head match{
            case (Nil)=>{
              board(visited.tail.head._1)(visited.tail.head._2)=0
              solve2(explored.tail,visited.tail,visited.tail.head._1,visited.tail.head._2,possible.tail.head.tail::possible.tail.tail)
            }
            case (s::xs)=>{
              board(x)(y)=s
              solve2(s::explored,visited,x + ((y+1)/9), (y+1) % 9,possible)
            }
          }
        }
        else {
          val z = possibleSol(x, y)
          z match {
            case (Nil) => {
              board(visited.head._1)(visited.head._2)=0
              solve2(explored.tail, visited, visited.head._1, visited.head._2, possible.head.tail::possible.tail)
            }
            case (s :: xs) => {
              board(x)(y) = s
              solve2(s :: explored, (x, y) :: visited, x + ((y+1)/9), (y+1) % 9, z :: possible)
            }
          }
        }
      }
      else {
        solve2(explored, visited,  x + ((y+1)/9), (y+1) % 9, possible)
      }
    }
    solve2(List(),List(),0,0,List())

    for (x <- 0 to 8) {
      for (y <- 0 to 8)
        print(board(x)(y)+" ")
      println("\n")
    }
    board.toString
  }
}

// for each cell generate list of possible solutions and check if the isSolved is true or Not. If not then proceed and
// fill up for other cells. If possible solutions for a cell returns empty list and isSolved is false then go back to previous
// cell and try next option form the list of possible soln.


object Sudoku {
  def apply(board: Array[Array[Int]]) = new Sudoku(board)
}
