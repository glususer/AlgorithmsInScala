//https://leetcode.com/problems/max-area-of-island/
def dfsOnGrid(grid: Array[Array[Int]], toVisit:(Int,Int), currentArea :Int, xMax:Int, yMax:Int):Int={
  if(grid(toVisit._1)(toVisit._2) == 0) currentArea
  else{
    val neighbours = List((toVisit._1-1,toVisit._2),(toVisit._1+1,toVisit._2),(toVisit._1,toVisit._2-1),(toVisit._1,toVisit._2+1))
      .filter{case(x,y) => x>=0 && y >=0 && x <= xMax && y <= yMax && grid(x)(y) == 1}
    grid(toVisit._1)(toVisit._2) = 0
    val areaFromNeighbours = neighbours.map(neighbour => dfsOnGrid(grid,neighbour, currentArea, xMax,yMax))
    areaFromNeighbours.sum+1
  }
}

def maxAreaOfIsland(grid: Array[Array[Int]]): Int = {
  val xMax = grid.length-1
  val yMax = grid(0).length-1
 val areaArray =  {for{
    x <- 0 to xMax
    y <- 0 to yMax
  }yield dfsOnGrid(grid, (x,y), 0, xMax, yMax)}
  if(areaArray.isEmpty) 0
  else areaArray.max

}

val grid = Array(Array(0,0,1,0,0,0,0,1,0,0,0,0,0),
  Array(0,0,0,0,0,0,0,1,1,1,0,0,0),
  Array(0,1,1,0,1,0,0,0,0,0,0,0,0),
  Array(0,1,0,0,1,1,0,0,1,0,1,0,0),
  Array(0,1,0,0,1,1,0,0,1,1,1,0,0),
  Array(0,0,0,0,0,0,0,0,0,0,1,0,0),
  Array(0,0,0,0,0,0,0,1,1,1,0,0,0),
  Array(0,0,0,0,0,0,0,1,1,0,0,0,0))

val grid2 = Array(Array(1,1),Array(1,1))

//maxAreaOfIsland(grid)

//https://leetcode.com/problems/path-with-maximum-gold/
def dfsGold(grid: Array[Array[Int]], toVisit: (Int,Int), xMax:Int, yMax:Int):Int={
  if(grid(toVisit._1)(toVisit._2) <=0 ) 0
  else{
    val (currentX, currentY) = toVisit
    val neighbours = List((currentX-1,currentY),
      (currentX+1,currentY),
      (currentX,currentY-1),
      (currentX,currentY+1))
      .filter(neighbour => neighbour._1 >=0 && neighbour._2 >=0 &&
        neighbour._1 <= xMax && neighbour._2 <= yMax &&
        grid(neighbour._1)(neighbour._2)> 0)

    println(s"cell is ${toVisit} and neighburs are $neighbours")

    grid(toVisit._1)(toVisit._2) = - grid(toVisit._1)(toVisit._2)
    val goldFromNeighbpurs = neighbours.map(neighbour => dfsGold(grid, neighbour, xMax, yMax))
    grid(toVisit._1)(toVisit._2) = - grid(toVisit._1)(toVisit._2)
    val goldReturnedFromThisCell = (if(goldFromNeighbpurs.nonEmpty)goldFromNeighbpurs.max else 0) +grid(toVisit._1)(toVisit._2)
    println(s"neighburs are $neighbours gold from neighbours is $goldFromNeighbpurs, toVisit : " +
      s"$toVisit, gold in current cell is ${grid(toVisit._1)(toVisit._2)} goldReturnedFromThisCell ${goldReturnedFromThisCell}")
    goldReturnedFromThisCell
  }
}

def getMaximumGold(grid: Array[Array[Int]]): Int = {
val xMax = grid.length-1
val yMax = grid(0).length-1

val startPositions = {
  for{
    x <- 0 to xMax
    y <- 0 to yMax
    if grid(x)(y) > 0
  }yield (x,y)
}.toList

 // dfsGold(grid, (2,1),xMax, yMax)
//  dfsGold(grid, (0,1),xMax, yMax)
val z =  startPositions.map{pos => val gold = dfsGold(grid, pos, xMax, yMax)
   println(s"cell is ${pos} and gold is $gold")
   gold
 }

 // println(s"z is $z")
  z.max
}

val grid2 = Array(Array(23,21,38,12,18,36,0,7,30,29,20,3,28),Array(23,3,19,2,1,11,4,8,9,24,6,5,35))
val grid1 = Array(Array(0,6,0),Array(5,8,7),Array(0,9,0))
getMaximumGold(grid2)
