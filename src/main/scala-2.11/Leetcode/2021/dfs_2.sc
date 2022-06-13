def findCircleNumDfs(i: Int, j: Int, isConnected: Array[Array[Int]]): Unit = {
  isConnected(i)(j)=0
  for(x <- isConnected.indices){
    if(isConnected(j)(x) ==1){
      /*isConnected.foreach(row => println(row.mkString(", ")))
       println(s"-------------------")*/
      findCircleNumDfs(j,x, isConnected)
    }
  }
}

def findCircleNum(isConnected: Array[Array[Int]]): Int = {
  var count = 0
  for (i <- isConnected.indices) {
    for (j <- isConnected.indices) {
      if (isConnected(i)(j) == 1) {
        count = count + 1
        findCircleNumDfs(i,j, isConnected)
      }
    }
  }
  count
}

/*
val array1 = Array(Array(1,1,0),Array(1,1,0),Array(0,0,1))
val array2 = Array(Array(1,1,0,0,0),Array(1,1,1,0,0),Array(0,1,1,0,0),Array(0,0,0,1,1),Array(0,0,0,1,1))
val notConnected = Array(Array(1,0,0), Array(0,1,0),Array(0,0,1))
val stronglyConnected = Array(Array(1,1,1,1,1),Array(1,1,1,1,1), Array(1,1,1,1,1), Array(1,1,1,1,1),Array(1,1,1,1,1))
val array3 = Array(Array(1,0,0,1),Array(0,1,1,0),Array(0,1,1,1),Array(1,0,1,1))
*/

//findCircleNum(notConnected)

def dfsClosedIsland(grid:Array[Array[Int]], i:Int, j:Int):Boolean={
  if(i <= 0 || i >= grid.length - 1 || j <= 0 || j >= grid(0).length - 1 ) return false
  if(grid(i)(j) == 1) return true
  grid(i)(j) = 1
  List((i+1,j),(i, j+1), (i,j-1),(i-1,j)).foldLeft(true){case (isIsland, coordinates) =>
   dfsClosedIsland(grid, coordinates._1, coordinates._2) && isIsland}
}

def closedIsland(grid: Array[Array[Int]]): Int = {
  var count = 0
  for(i <- 1 until grid.length-1){
    for(j<- 1 until grid(0).length-1){
      if(grid(i)(j) == 0)
        if(dfsClosedIsland(grid, i, j)) count = count+1
    }
  }
  count
}

val array1 = Array(Array(1,1,1,1,1,1,1,0),Array(1,0,0,0,0,1,1,0),Array(1,0,1,0,1,1,1,0),
  Array(1,0,0,0,0,1,0,1),Array(1,1,1,1,1,1,1,0))

//closedIsland(array1)

//https://leetcode.com/problems/keys-and-rooms/
def canVisitAllRoomsDfs(edgeMap: Map[Int, Set[Int]], toVisit: Int, visited: Set[Int]):Set[Int]={
  if(visited.contains(toVisit)) visited
  else {
    val neighbours = edgeMap.getOrElse(toVisit, List.empty)
      .filter(neighbour => neighbour != toVisit && !visited.contains(neighbour))

    if (neighbours.isEmpty) visited + toVisit
    else {
      neighbours.foldLeft(visited){case(alreadyVisited, currentRoom) =>
      val roomsVisitedFromthisRoom = canVisitAllRoomsDfs(edgeMap, currentRoom, alreadyVisited+toVisit)
        roomsVisitedFromthisRoom++alreadyVisited
      }
    }
  }
}

def canVisitAllRooms(rooms: List[List[Int]]): Boolean = {
  val edgeMap = rooms.zipWithIndex.map { case (keys, index) => index -> keys.toSet }.toMap
  val visitedRooms = canVisitAllRoomsDfs(edgeMap, 0, Set.empty)
  if(visitedRooms.size == rooms.length) true else false
}
//canVisitAllRooms(List(List(1,3), List(3,0,1), List(2), List(0)))
//canVisitAllRooms(List(List(1), List(2), List(3), List()))
def findSmallestSetOfVerticesDfs(cuurentNode:Int, edgeMap:Map[Int, Set[Int]], visited: Set[Int], toVisit: Int, minimumSet:Set[Int]): Set[Int]={
  if(visited.contains(toVisit))
}
def findSmallestSetOfVertices(n: Int, edges: List[List[Int]]): List[Int] = {

}


