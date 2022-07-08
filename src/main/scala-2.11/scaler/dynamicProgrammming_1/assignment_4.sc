//Given a 2 x N grid of integer, A, choose numbers such that the sum of the numbers is maximum and no two chosen numbers are adjacent horizontally, vertically or diagonally, and return it.
//
//Note: You can choose more than 2 numbers

def adjacent(A: Array[Array[Int]]): Int  = {
  A.foreach(row => println(row.toList.mkString(", ")))
  println()
val rows = 2
val cols = A(0).length
  if(cols > 2) {

    for (i <- 2 until cols) {
      for (j <- 0 until rows) {
        val probableNeighbours = List((j,i-2),(j+1,i-2),(j,i-3),(j+1,i-3),(j,i-2),(j-1,i-2),(j,i-3),(j-1,i-3))
      //  println(s"probableNeighbours $probableNeighbours row ${j} col ${i}")
         val maxNeighbour =  probableNeighbours.filter{case (j,i) => j >=0 && j<rows && i>=0 && i < cols}
          .map{case(j,i) => A(j)(i)}
        A(j)(i) =  if(maxNeighbour.nonEmpty) maxNeighbour.max+ A(j)(i) else A(j)(i)

      }
    }
    A.foreach(row => println(row.toList.mkString(", ")))

    A(0)(cols-1) max A(1)(cols-1) max A(0)(cols-2) max A(1)(cols-2)
  }
  else if(cols == 1)  A(0)(0) max A(1)(0)
  else A(0)(0) max A(1)(0) max A(0)(1) max A(1)(1)

}

adjacent(Array(Array(14,10),Array(14,12)))