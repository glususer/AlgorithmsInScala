//Given a M x N grid A of integers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
//
//Return the minimum sum of the path.
//
//NOTE: You can only move either down or right at any point in time.
// down -> (i-1)(j), right -> (i)(j-1)
def minPathSum(A: Array[Array[Int]]): Int  = {
  val rows = A.length
  val col = A(0).length
  val dp = Array.fill(A.length)(Array.fill(A(0).length)(0))

  for{i<-0 until rows}{
    for{j<- 0 until col}{
      val minFromNieghbours = {
        if(i-1 >=0 && j-1 >=0 )dp(i-1)(j) min dp(i)(j-1)
        else if(i-1<0 && j-1 >=0)dp(i)(j-1)
        else if(i-1>=0 && j-1<0) dp(i-1)(j)
        else 0
      }
      //println(s"minFromNieghbours $minFromNieghbours i $i j $j")
      dp(i)(j) =minFromNieghbours + A(i)(j)
    }
  }
 // dp.foreach(row => println(s"${row.toList}"))
  dp(rows-1)(col-1)
}

minPathSum(Array(Array(1, -3, 2),Array(2, 5, 10),Array(5, -5, 1)))