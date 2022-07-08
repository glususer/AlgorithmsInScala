def calculateMinimumHP(A: Array[Array[Int]]): Int  = {
  val rows = A.length
  val col = A(0).length
  val health = Array.fill(rows+1)(Array.fill(col+1)(1))
  health(rows-1)(col-1) = if(A(rows-1)(col-1) >=0) 1 else 1-A(rows-1)(col-1)

  for{i<-rows-1 to 0 by -1}{
    for{j<- col-1 to 0 by -1}{
      val min = {
        if(i+1 >= rows && j+1<= col) health(i)(j+1)
        else if(i+1<=rows && j+1>=col) health(i+1)(j)
        else  health(i+1)(j) min health(i)(j+1)
      }
      health(i)(j) = min-A(i)(j) max 1
    }
  }
 // health.foreach(row => println(s"${row.toList}"))
  health(0)(0)
}

calculateMinimumHP(Array(Array(-2, -3, 3),Array(-5, -10, 1),Array(10, 30, -5)))