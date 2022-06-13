//https://leetcode.com/problems/n-queens-ii/

def totalNQueensHelper(positions:List[Int], queenNo:Int, acc:List[List[Int]] = List.empty, totalQueens:Int):List[List[Int]]={
  if(queenNo >= totalQueens)acc:+positions
  else{
    val invalidPositions : List[Int] = positions.zip(positions.length to 1 by -1).flatMap{case (pos, distanceFromCurrent) =>
    List(pos-distanceFromCurrent,pos+distanceFromCurrent).filter(probable => probable < totalQueens && probable >=0)
    }++positions.toSet

    val validPositions = (0 until  totalQueens).diff(invalidPositions).toList
  //  println(s"for queen $queenNo prevPositions are $positions validPositions $validPositions")
    validPositions.flatMap{nextPosition =>  totalNQueensHelper(positions:+nextPosition, queenNo+1, acc, totalQueens)}
  }
}

def totalNQueens(n: Int): Int = {
 val positions =  (0 until n).flatMap{position => totalNQueensHelper(List(position),1,List.empty,n)}
  positions.foreach(sol => println(s"solution $sol"))
   positions.length
}

totalNQueens(8)
//totalNQueensHelper(List(1), 1, List.empty, 5)