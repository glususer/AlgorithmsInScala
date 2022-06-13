//https://leetcode.com/problems/unique-paths-iii/
def uniquePathsIIIHelper(grid: Array[Array[Int]], emptyCellsCount: Int, x: Int, y: Int, currentCellCount: Int): Int = {
  if (grid(x)(y) == 2) {
   // println(s"currentCellCount $currentCellCount emptyCellsCount $emptyCellsCount")
    if (currentCellCount-1 == emptyCellsCount) 1
    else 0
  }
  else {
    grid(x)(y) = -1
    val neighbours = List((x + 1, y), (x, y + 1), (x, y - 1), (x - 1, y)).filter { case (i, j) => i >= 0 && j >= 0 && i < grid.length && j < grid(0).length && grid(i)(j) != -1 }
    val paths = neighbours.map(nextMove => uniquePathsIIIHelper(grid, emptyCellsCount, nextMove._1, nextMove._2, currentCellCount+1)).sum
    grid(x)(y) = 0
    paths
  }
}

def getColIndex(arr:Array[Int]):Int={
  arr.zipWithIndex.find{case (v, idx) => v== 1}.map(_._2).getOrElse(-1)
}

def uniquePathsIII(grid: Array[Array[Int]]): Int = {
  val emptyCells = grid.map(row => row.count(_ == 0)).sum
  val startingCell = grid.zipWithIndex
    .collectFirst { case (row, rIndex) if(getColIndex(row) != -1) => (rIndex, getColIndex(row)) }
    .getOrElse(0,0)
  uniquePathsIIIHelper(grid, emptyCells, startingCell._1, startingCell._2, 0)
}

//[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
val grid = Array(Array(0, 0, 0, 0), Array(0, 0, 0, 0), Array(1, 0, 2, -1))

val startingCell = grid.zipWithIndex
  .collectFirst { case (row, rIndex) if(getColIndex(row) != -1) => (rIndex, getColIndex(row)) }
  .getOrElse(0,0)

//uniquePathsIII(grid)


