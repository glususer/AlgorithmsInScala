//The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
//Given an integer A, return all distinct solutions to the n-queens puzzle.
//
//Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
//The final list should be generated in such a way that the indices of the queens in each list should be in reverse lexicographical order
// private static ArrayList<ArrayList<Integer>>  nQueen(int row,
//                                                         ArrayList<Integer> queens,
//                                                         int N,
//                                                         ArrayList<ArrayList<Integer>> acc) {
//        if (row == N){
//            acc.add(queens);
//            return acc; // Base
//        }
//        for (int c = 0; c < N; c++) { // all combinations
//            ArrayList<Integer> validPositions = getValidPositions(row, queens, N);
//            validPositions.stream().map(position -> {
//                queens.set(row, position); // set
//                 return nQueen(row + 1, queens, N, acc); // recursion
//            });
//        }
//        return acc;
//    }

def isValid(row:Int, col:Int, queens:Array[Int]):Boolean={
  for(i<- 0 until row){
    val j = queens(i)
    if(j==col || (i-row) == (j-col) || (i-row) == (col-j)) return false
  }
  true
}

def getValidPositions(row: Int, queens:Array[Int]):List[Int]={
  import  scala.collection.mutable.ListBuffer
  val positions = ListBuffer.empty[Int]
  for(j <- queens.indices) {
    if(isValid(row,j,queens)) positions += j
  }
  positions.toList
}

def helperSolveNQueens(row:Int, queens:Array[Int], acc:List[List[Int]]):List[List[Int]]={
 // println(s"row $row, queens ${queens.toList} acc $acc")
  if(row == queens.length) {
    val newList = queens.toList
    acc:+newList
  }
  else{
      val validPositions = getValidPositions(row, queens)
      validPositions.flatMap{pos =>
        queens(row) = pos
        val result = helperSolveNQueens(row+1, queens,acc)
        queens(row) = -1
        result
      }
  }
}
def solveNQueens(A:Int):List[List[Int]]={
  helperSolveNQueens(0,Array.fill(A)(-1),List.empty)
}

solveNQueens(5).length