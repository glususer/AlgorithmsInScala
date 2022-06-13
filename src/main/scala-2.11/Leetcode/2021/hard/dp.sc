//https://leetcode.com/problems/minimum-falling-path-sum-ii/

def minFallingPathSumHelper(grid:Array[Array[Int]], i:Int, j:Int):Unit={
  if(i == grid.length)return
  else{
    grid(i)(j) = grid(0).indices.foldLeft(Int.MaxValue){case (acc, y) =>
      if(y != j)grid(i-1)(y) min acc
      else acc
    }+grid(i)(j)
    val (updatedI, updatedJ) = if(j == grid(0).length-1)(i+1,0) else (i, j+1)
    minFallingPathSumHelper(grid, updatedI, updatedJ)
  }
}
def minFallingPathSum(grid: Array[Array[Int]]): Int = {
  minFallingPathSumHelper(grid,1,0)
  grid(grid.length-1).min
}

minFallingPathSum(Array(Array(1,2,3),Array(4,5,6),Array(7,8,9)))
minFallingPathSum(Array(Array(7)))