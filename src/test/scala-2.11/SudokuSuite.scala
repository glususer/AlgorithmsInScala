import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner


/**
  * Created by shivangi on 07/09/16.
  */
@RunWith(classOf[JUnitRunner])
class SudokuSuite extends FunSuite {
  val board:Array[Array[Int]]= Array(
    Array(0,0,4,8,0,0,0,1,7),Array(6,7,0,9,0,0,0,0,0),
    Array(5,0,8,0,3,0,0,0,4),Array(3,0,0,7,4,0,1,0,0),
    Array(0,6,9,0,0,0,7,8,0),Array(0,0,1,0,6,9,0,0,5),
    Array(1,0,0,0,8,0,3,0,6),Array(0,0,0,0,0,6,0,9,1),
    Array(2,4,0,0,0,1,5,0,0))

  val board1 :Array[Array[Int]]=Array(
    Array(0,8,0,0,7,0,0,0,6),Array(0,0,0,0,0,6,0,0,3),
    Array(2,0,0,0,0,9,0,0,5),Array(7,0,0,0,0,0,0,0,0),
    Array(5,0,0,0,0,0,0,0,8),Array(0,2,4,5,0,0,0,0,0),
    Array(0,5,8,3,0,0,0,2,0),Array(4,0,9,0,0,0,0,7,0),
    Array(0,0,0,0,5,0,0,0,9))

  val board2:Array[Array[Int]]=Array(
    Array(8,0,0,0,6,0,3,9,7),Array(9,1,0,2,0,0,0,0,0),
    Array(0,0,0,4,0,0,0,0,0),Array(0,0,7,9,0,0,0,0,0),
    Array(0,0,4,0,0,0,0,2,5),Array(2,0,0,6,7,0,0,0,0),
    Array(0,0,0,0,9,1,0,0,0),Array(4,0,0,0,0,0,0,3,0),
    Array(0,0,0,0,0,0,6,1,0))
  (Sudoku(board2).solve())

  KnightTour.apply.solve(List())
  /*val maze:Maze = Seq(Seq(Cell(true,false),Cell(true,false),Cell(false,false),Cell(false,false)),
    Seq(Cell(false,false),Cell(true,false),Cell(true,false),Cell(true,false)),
    Seq(Cell(false,true),Cell(false,false),Cell(false,false),Cell(true,true)))
  MazeSolver.findStart2(maze,new Position(0,0),List())*/
}
