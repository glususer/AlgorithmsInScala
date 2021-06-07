
//https://leetcode.com/problems/spiral-matrix/

def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {
  def helper(rowStart: Int, rowEnd: Int, colStart: Int, colEnd: Int, acc:List[Int]): List[Int] = {
    println(s"rowStart $rowStart rowEnd $rowEnd, colStart $colStart and colEnd $colEnd")
    if (rowStart > rowEnd || colStart > colEnd) return acc
    var x = rowStart
    var y = colStart
    var currentList:List[Int] = List.empty
    while (y <= colEnd) {
      print(x, y)
      print(s"${matrix(x)(y)} a")
      currentList = currentList:+matrix(x)(y)
      y = y + 1
      println("\n")

    }
    x = x + 1
    y = y - 1
    while (x <= rowEnd) {
       print(x, y)
      print(s"${matrix(x)(y)} b")
      currentList = currentList:+matrix(x)(y)
      x = x + 1
      println("\n")

    }
    x = x - 1
    y = y - 1

    if(rowStart != rowEnd) {
      while (y >= colStart) {
        print(x, y)
        print(s"${matrix(x)(y)} c")
        currentList = currentList :+ matrix(x)(y)
        y = y - 1
        println("\n")

      }
    }
    y = y + 1
    x = x - 1
    if(colStart != colEnd) {
      while (x > rowStart) {
        print(x, y)
        print(s"${matrix(x)(y)} d")
        currentList = currentList :+ matrix(x)(y)
        x = x - 1
        println("\n")

      }
    }
    helper(rowStart + 1, rowEnd - 1, colStart + 1, colEnd - 1,acc:::currentList)
  }
  helper(0,matrix.length-1,0,matrix.head.length-1, List.empty)
}
//spiralOrder(Array(Array(1,2,3,4),Array(5,6,7,8),Array(9,10,11,12)))

def searchMatrix(matrix: Array[Array[Int]], target: Int): Boolean = {
  var potRows :List[Int]= List.empty
  var potCol:List[Int] = List.empty
  var rowStart = 0
  var colStart = 0
  val rowEnd = matrix.length-1
  val colEnd = matrix.head.length-1

  while(rowStart <= rowEnd &&target >= matrix(rowStart)(colStart)){
    if(target >= matrix(rowStart)(colStart) && target <= matrix(rowStart)(colEnd)) {
      potRows = potRows :+ rowStart
    }
    rowStart = rowStart + 1
    println(s"Row $rowStart,$colStart, $rowEnd,$colEnd")
  }
  rowStart = 0
  colStart = 0

  while(colStart <= colEnd && target >= matrix(rowStart)(colStart)){
    if(target >= matrix(rowStart)(colStart) && target <= matrix(rowEnd)(colEnd)) {
      println(s"Col $rowStart,$colStart,$rowEnd,$colEnd")
      potCol = potCol :+ colStart
    }
    colStart = colStart+1
  }

  val intersection =for{
    x<- potRows
    y<- potCol
  }yield (x,y)

  intersection.map{case(row,col) => matrix(row)(col)}.contains(target)

}
searchMatrix(Array(Array(4),
                   Array(6)),8)

case class Task(id:String, arrival:Long, execution:Long)

/*def executionOrder(tasks:List[Task]):List[String]={
  val pq = mutable.PriorityQueue[Task].empty(Ordering.by(task => task.execution)).reverse
  pq.enqueue(tasks.head)
  helper(pq,tasks,List.empty,tasks.head.execution).map(_.id)
}

def helper(pq:mutable.PriorityQueue[Task],tasks:List[Task],executionTime:Long, acc:List[Task]):List[Task]={
  tasks match{
    case Nil => acc:+pq.dequeueAll()
    case => val current = pq.dequeue
      val toBeEnqueues = tasks.filter(task => task.arrival <= executionTime)
      pq.enqueu(toBeEnqueues:_*)
      helper(pq,tasks.diff(toBeEnqueues),executionTime+current.executionTime, acc:+current)

  }
}*/
//https://leetcode.com/problems/sort-the-matrix-diagonally/
def diagonalSort(mat: Array[Array[Int]]): Array[Array[Int]] = {
   ???
}

//https://leetcode.com/problems/count-submatrices-with-all-ones/
def numSubmat(mat: Array[Array[Int]]): Int = {
  val rowCount = mat.map(row => row.scanLeft(0){case(acc,num) => if(num == 0) num else num+acc}.tail).map(_.toArray)
  var i=0
  var j=0
  val rowLength = mat.length
  val colLength = mat.head.length
  var res = 0
  while(i<rowLength){
    while(j<colLength){
     var min = rowCount(i)(j)
      res +=min
      var k = i+1
      while(k < rowLength && rowCount(k)(j) !=0){
        min = Math.min(min,rowCount(k)(j))
        res+=min
        k+=1
      }
      j+=1
    }
    i+=1
    j=0
  }
  res
}
numSubmat(Array(Array(0,1,1,0), Array(0,1,1,1), Array(1,1,1,0)))

Array(0,1,1,0).scanRight(0){case(acc,num) => if(num == 0) acc+0 else num+acc}

//https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/
def maxProductPath(grid: Array[Array[Int]]): Int = {
val row = grid.length
val col = grid.head.length
val arr :Array[Array[(Long,Long)]]=Array.fill(grid.length)(Array.fill(grid.head.length)(0,0))
var i=0
var j=0
val Mod = 1e9+7
while(i<row){
  while(j<col){
    val indices = List((i,j-1),(i-1,j)).filter{case (r,c) => r>=0 && c >=0}
    val probable = indices.map{case (r,c) => arr(r)(c)}.flatMap{case (a,b) => List(a,b)}
    if(probable.isEmpty) arr(i)(j) = (grid(i)(j),grid(i)(j))
    else arr(i)(j) = (probable.min*grid(i)(j),probable.max*grid(i)(j))
    j+=1
  }
  j=0
  i+=1
}
  val last = arr(row-1)(col-1)
  println(last)
  val exists = Math.max(last._1,last._2)
  if(exists >= 0) (exists%Mod).toInt else -1
}

val arr = Array(Array( 1,3),
Array(0,-4))

maxProductPath(arr)

def diagonalSort(mat: Array[Array[Int]]): Array[Array[Int]] = {
  val row = mat.length
  val col = mat(0).length
  var i = col
  var j = 0
  var k = 0
  while(i >=0){
    while(j >0){
      var r = mat(i)(j)
    }
    mat(i)(j)
    i = i-1
  }


}


