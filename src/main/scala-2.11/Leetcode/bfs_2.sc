

//https://leetcode.com/problems/map-of-highest-peak/
//Runtime: 3216 ms, faster than 100.00% of Scala online submissions for Map of Highest Peak.
//Memory Usage: 181.1 MB, less than 100.00% of Scala online submissions for Map of Highest Peak.
def printGrid(arr:Array[Array[Int]]):Unit={
  arr.map(row => row.map(value => value).mkString(" ")).foreach(row => println(row))
  println(s"```````````")
}

def BFSHighestPeak(isWater: Array[Array[Int]], toVisit:Array[(Int,Int)], visited:Set[(Int,Int)], xMax:Int, yMax:Int):Unit={
  if(toVisit.isEmpty) return
  else{
    val neighbours = toVisit.flatMap {
      case (x, y) =>
        val neighbourList = List((x + 1, y), (x - 1, y), (x, y - 1), (x, y + 1))
          .filter { case (x, y) => x >= 0 && y >= 0 && x <= xMax && y <= yMax && isWater(x)(y) < 0 }
        neighbourList.foreach{case(n,m) => if (isWater(n)(m) < 0) isWater(n)(m) = isWater(x)(y) +1 }
        neighbourList
    }.distinct

    BFSHighestPeak(isWater, neighbours, visited++toVisit, xMax, yMax)
    }
  }
def highestPeak(isWater: Array[Array[Int]]): Array[Array[Int]] = {
val xMax = isWater.length-1
val yMax = isWater.head.length-1
 val startPos =  (for{
    x<- 0 to xMax
    y<- 0 to yMax
  }yield (x,y)).filter{case(x,y) =>
   isWater(x)(y) == 1
 }.toArray

 (for{
    x<- 0 to xMax
    y<- 0 to yMax
  }yield (x,y)).foreach{case(x,y) =>
    if(isWater(x)(y)== 1) isWater(x)(y)= 0 else isWater(x)(y) = -1
  }

  //printGrid(isWater)
  BFSHighestPeak(isWater, startPos, Set.empty, xMax, yMax)
    printGrid(isWater)
  isWater
}
val isWater = Array(Array(0,0,1),Array(1,0,0),Array(0,0,0))
highestPeak(isWater)
