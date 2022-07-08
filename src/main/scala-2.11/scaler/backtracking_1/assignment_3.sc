//Given a matrix of integers A of size N x M . There are 4 types of squares in it:
//
//1. 1 represents the starting square.  There is exactly one starting square.
//2. 2 represents the ending square.  There is exactly one ending square.
//3. 0 represents empty squares we can walk over.
//4. -1 represents obstacles that we cannot walk over.
//Find and return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
//
//Note: Rows are numbered from top to bottom and columns are numbered from left to right.
def uniquePathsIIIHelper(grid: Array[Array[Int]], emptyCellsCount: Int, x: Int, y: Int, currentCellCount: Int): Int = {
  if (grid(x)(y) == 2) {
    if (currentCellCount-1 == emptyCellsCount) 1
    else 0
  }
  else {
    grid(x)(y) = -1
    val neighbours = List((x + 1, y), (x, y + 1), (x, y - 1), (x - 1, y))
      .filter { case (i, j) => i >= 0 && j >= 0 && i < grid.length && j < grid(0).length && grid(i)(j) != -1 }

    val paths = neighbours
      .map(nextMove => uniquePathsIIIHelper(grid, emptyCellsCount, nextMove._1, nextMove._2, currentCellCount+1)).sum
    grid(x)(y) = 0
    paths
  }
}

def getColIndex(arr:Array[Int]):Int=
  arr.zipWithIndex.find{case (v, idx) => v== 1}.map(_._2).getOrElse(-1)

def solve(A: Array[Array[Int]]): Int  = {
  val emptyCells = A.map(row => row.count(_ == 0)).sum
  val startingCell = A.zipWithIndex
    .collectFirst { case (row, rIndex) if(getColIndex(row) != -1) => (rIndex, getColIndex(row)) }
    .getOrElse(0,0)
  uniquePathsIIIHelper(A, emptyCells, startingCell._1, startingCell._2, 0)
}

solve(Array(Array(1, 0, 0, 0),Array(0, 0, 0, 0),Array(0, 0, 2, -1)))

