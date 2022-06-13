//https://leetcode.com/problems/shortest-path-in-binary-matrix/
def shortestPathBinaryMatrixHelper(grid: Array[Array[Int]], steps:Int, toVisit:Set[(Int,Int)], visited:Set[(Int,Int)]):Int={
//println(s"toVisit $toVisit, visited $visited, steps $steps")
  if (visited.contains((grid.length-1, grid.length-1))) steps
  else if(toVisit.isEmpty && !visited.contains(grid.length-1, grid.length-1)) -1
  else{
    val neighbours = toVisit
      .map{case (x,y) => Set((x,y+1),(x,y-1),
        (x+1,y),(x+1,y+1),(x+1,y-1),
        (x-1,y),(x-1,y-1),(x-1,y+1))
        .filter{case(x,y) => x > -1 && x < grid.length &&
          y > -1 && y < grid.length &&
          !visited.contains((x,y)) && grid(x)(y) == 0 && !toVisit.contains((x,y))
        }
      }

   val stepsFromNeighbours = neighbours.map(neighbour => shortestPathBinaryMatrixHelper(grid, steps+1, neighbour, toVisit++visited))
     .filter(steps  => steps > 0)

    if (stepsFromNeighbours.isEmpty) -1 else stepsFromNeighbours.min

  }
}

def shortestPathBinaryMatrix(grid: Array[Array[Int]]): Int = {
  if(grid(0)(0) == 1) -1
  else shortestPathBinaryMatrixHelper(grid, 0,Set((0,0)), Set.empty)
}

//[[0,0,0,0,0,0,0,0],[0,0,1,0,0,0,0,1],[1,0,0,0,0,0,0,0],[0,0,0,0,0,1,1,0],[0,0,1,0,1,0,1,1],[0,0,0,0,0,0,0,0],[0,0,0,1,1,1,0,0],[1,0,1,1,1,0,0,0]]
//val grid1 = Array(Array(0,0,0),Array(1,1,0),Array(1,1,0))
//val grid2 = Array(Array(1,0,0),Array(1,1,0),Array(1,1,0))
//val grid3 = Array(Array(0,1),Array(1,0))
//val grid4 = Array(Array(0,0,0),Array(0,0,0),Array(0,1,0))
val grid5 = Array(
  Array(0,0,0,0,0,0,0,0),
  Array(0,0,1,0,0,0,0,1),
  Array(1,0,0,0,0,0,0,0),
  Array(0,0,0,0,0,1,1,0),
  Array(0,0,1,0,1,0,1,1),
  Array(0,0,0,0,0,0,0,0),
  Array(0,0,0,1,1,1,0,0),
  Array(1,0,1,1,1,0,0,0))


//shortestPathBinaryMatrix(grid1)
//shortestPathBinaryMatrix(grid2)
//shortestPathBinaryMatrix(grid3)
shortestPathBinaryMatrix(grid5)