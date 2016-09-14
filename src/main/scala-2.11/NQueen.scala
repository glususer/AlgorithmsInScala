/**
  * Created by shivangi on 22/08/16.
  */
class NQueen(n: Int) {
  val noOfQueens: Int = n

  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val row = queens.length
    val queensWithRow = (row - 1 to 0 by -1) zip queens
    queensWithRow forall {
      case (r, c) => col != c  && math.abs(col - c) != row - r
    }
  }

  def queens(n: Int): Set[List[Int]] = {
    def placeQueens(k: Int): Set[List[Int]] = {
      if (k == 0) Set(List())
      else
        for {
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
          _=println(k,col,queens)
        } yield col :: queens
    }
    placeQueens(n)
  }
}

object NQueen {
  def apply(n: Int) = new NQueen(n)
}
