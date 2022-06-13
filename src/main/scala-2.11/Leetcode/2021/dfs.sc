//https://leetcode.com/problems/number-of-islands/
def dfsNumsIslands(grid: Array[Array[Char]], i:Int, j:Int):Unit={
  if(i<0 || i>= grid.length || j <0 || j>= grid(0).length || grid(i)(j)=='0') return
  grid(i)(j) = '0'
  dfsNumsIslands(grid, i-1,j)
  dfsNumsIslands(grid, i+1,j)
  dfsNumsIslands(grid,i,j-1)
  dfsNumsIslands(grid, i, j+1)
}

def numIslands(grid: Array[Array[Char]]): Int = {
  var count = 0
  for (row <- grid.indices) {
    for (col <- grid(0).indices) {
      if (grid(row)(col) == '1'){
        count +=1
        dfsNumsIslands(grid, row, col)
        grid.foreach(row => println(row.mkString(", ")))
        println(s"------------------- i $row and j: $col")
      }
    }
  }
  count
}
//[["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]]
val test1 = Array(Array('0','0','0','0','0'),
  Array('0','0','0','0','0'),
  Array('0','0','0','0','0'),
  Array('0','0','0','1','1'))

//numIslands(test1)

def connectedToSomeZeroOnBorder(board: Array[Array[Char]], i: Int, j: Int):Boolean = {
 List((i-1,j),(i+1,j),(i,j-1),(i,j+1))
   .exists{case (k,l) => (k == 0 || k == board.length-1 || l ==0 || l == board.length-1) && (board(k)(l) == 'O') }
}

//https://leetcode.com/problems/surrounded-regions/
//[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
def dfsSolve(board:Array[Array[Char]], i:Int, j:Int):Unit={
  if(i<=0 || i>= board.length-1 ||j <=0 ||j>= board(0).length-1 || board(i)(j) == 'X' || connectedToSomeZeroOnBorder(board, i,j) ||  board(i)(j) == 'E') return
  board(i)(j) = 'E'
  dfsSolve(board, i-1,j)
  dfsSolve(board, i+1,j)
  dfsSolve(board, i,j-1)
  dfsSolve(board,i, j+1)
}

def solve(board: Array[Array[Char]]): Unit = {
for(row <- board.indices){
  for(col <- board(0).indices){
    if(board(row)(col) == 'O') {
      dfsSolve(board, row, col)
      board.foreach(row => println(row.mkString(", ")))
      println(s"------------------- i $row and j: $col")
    }

  }
}
}
val test2 = Array(Array('X','X','X','X'),
  Array('X','O','O','X'),
  Array('X','X','O','X'),
  Array('X','O','X','X'))

val test3 = Array(Array('O','O','O'),Array('O','O','O'),Array('O','O','O'))
val test4 = Array(
  Array('O','X','X','O','X'),
  Array('X','O','O','X','O'),
  Array('X','O','X','O','X'),
  Array('O','X','O','O','O'),
  Array('X','X','O','X','O'))

//solve(test4)
//test4.foreach(row => println(row.mkString(", ")))

//https://leetcode.com/problems/all-paths-from-source-to-target/

def allPathsSourceTargetHelper(graph: Array[Array[Int]], allPaths: List[List[Int]] = List.empty,toVisit:Int, edgeMap: Map[Int, List[Int]]):List[List[Int]]={
 println(s"allPaths  $allPaths, edgeMap $edgeMap")
  if (allPaths.nonEmpty && allPaths.forall(paths => paths.last == graph.length-1)) allPaths
  else{
    if(allPaths.isEmpty){
      val neighbours = edgeMap.getOrElse(toVisit, List.empty)
      neighbours.flatMap{vertex => allPathsSourceTargetHelper(graph, List(List(toVisit, vertex)), vertex, edgeMap)}
    }else {
      val pathsToExplore = allPaths.filterNot(path => path.last == graph.length - 1)
        .flatMap { path =>
          val endVertex = path.last
          val neighbours = edgeMap.getOrElse(endVertex, List.empty)
          val updatedPaths = neighbours.map { neighbour => (neighbour, path :+ neighbour) }
          updatedPaths
        }
      pathsToExplore.flatMap { case (vertex, paths) => allPathsSourceTargetHelper(graph, List(paths), vertex, edgeMap) }
    }

  }
}

def allPathsSourceTarget(graph: Array[Array[Int]]): List[List[Int]] = {
val edgeMap = graph.zipWithIndex.map{case (neighbours, index) => index -> neighbours.toList}.toMap
  if(edgeMap.getOrElse(0, List.empty).isEmpty) List.empty
  else allPathsSourceTargetHelper(graph,List.empty, 0, edgeMap)
}
//4,3,1],[3,2,4],[3],[4],[]
//[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]
val graph2 =  Array(Array(1,2),Array(3),Array(3),Array.empty[Int])
val graph1 = Array(Array(4,3,1),Array(3,2,4),Array(3),Array(4), Array.empty[Int])
val graph3 = Array(Array.empty)
allPathsSourceTarget(graph2)