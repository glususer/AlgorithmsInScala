def minimumJumps(forbidden: Array[Int], a: Int, b: Int, x: Int): Int = {
  val forward = a
  val backward = b
  val target = x

  @scala.annotation.tailrec
  def helper(current: List[Int], acc: List[List[Int]]): List[List[Int]] = {
    println(current)
    current match {
      case Nil => acc
      case curr if (curr.contains(target)) => acc :+ curr
      case curr if acc.forall(lst => lst.forall(num => num -backward > target)) => List.empty
      case  curr =>
        val children = curr.flatMap(ele => List(ele + forward, ele - backward)).filterNot(child => forbidden.contains(child) || child <= 0)
        helper(children, acc :+ curr)
    }
  }

  if (forward == target) 1
  else if(forbidden.contains(forward) && forbidden.contains(backward)) -1
  else {
    val initialCurr = List(forward, backward).filterNot(forbidden.contains)
      .flatMap(child => List(child + forward, child - backward))
      .filterNot(child => forbidden.contains(child) || child < 0)

    val children = List(List(forward, backward).filterNot(child => forbidden.contains(child)))

  //  println(initialCurr, children)

    val sol = helper(initialCurr,children)
    if (sol.nonEmpty) sol.length else -1
  }
}


//https://leetcode.com/problems/jump-game-iii/

def helper(arr:Array[Int], targetIndices:Array[Int], visited:Set[Int],toVisit:Array[Int]):Boolean={
  if(targetIndices.exists(visited.contains)) true
  else if(toVisit.isEmpty && !targetIndices.exists(visited.contains)) false
  else{
    val neighbours = toVisit.flatMap(current => Array(current-arr(current),current+arr(current)))
      .filterNot(visited.contains)
      .filter(idx => idx < arr.length && idx > -1)
    helper(arr,targetIndices,visited++toVisit,neighbours)
  }
}
def canReach(arr: Array[Int], start: Int): Boolean = {
  helper(arr, arr.zipWithIndex.withFilter(_._1==0).map(_._2),Set.empty,Array(start))
}

canReach(Array(3,0,1,2),0)

//https://leetcode.com/problems/rotting-oranges/

def helper(grid:Array[Array[Int]],toVisit:Array[(Int,Int)], visited:Set[(Int,Int)],time:Int):Int={
  if(grid.forall(row => !row.contains(1))){
    time
  }
  else if(grid.exists(row => row.contains(1)) && toVisit.isEmpty) -1
  else{
    val neighbours = toVisit.flatMap{case(i,j) => List((i-1,j),(i+1,j),(i,j-1),(i,j+1))
      .filter{case(i,j) => i > -1  && j > -1 && i < grid.length && j < grid.head.length && grid(i)(j) == 1}}
      .filterNot(visited.contains)
    println(s"neighbours ${neighbours.toList}")
    neighbours.foreach{case (i,j) => grid(i)(j)=2}
    helper(grid,neighbours,visited++toVisit,time+1)
  }
}
def orangesRotting(grid: Array[Array[Int]]): Int = {
 val rottenOrangesIndex =  (for{
    i<- grid.indices
    j <- grid.head.indices
    if(grid(i)(j) == 2)
  }yield (i,j)).toArray
  helper(grid,rottenOrangesIndex,Set.empty,0)
}

orangesRotting(Array(Array(2,1,1),Array(0,1,1),Array(1,0,1)))

//https://leetcode.com/problems/word-ladder/

def findNeigbours(word:String, wordList:Set[String]):List[String]={
  (for {
    i<-0 until word.length
    (left,right) = word.splitAt(i)
    y = ('a' to 'z').toList.map(char => left+char+right.tail).filter(str => wordList.contains(str))//.filter(wordList.contains)
  } yield y).flatten.toList
}
def helper(visited:Set[String], toVisit:List[String], numOfStep:Int, target:String,nextValidNeighbours:Map[String, List[String]]):Int={
  if(visited.contains(target)) numOfStep
  else{
    if(toVisit.isEmpty && !visited.contains(target)) 0
    else{
      val neighbours = toVisit.flatMap(currWord => nextValidNeighbours(currWord))
        .filterNot(probable => visited.contains(probable))
          .distinct
      println(s" neighbours $neighbours toVisit $toVisit visited $visited")
      helper(visited++toVisit, neighbours, numOfStep+1, target,nextValidNeighbours)
    }
  }
}
def ladderLength(beginWord: String, endWord: String, wordList: List[String]): Int = {
  val nextValidNeighbours = (wordList:+beginWord).map(word =>(word,findNeigbours(word, wordList.toSet))).toMap
  if(!wordList.contains(endWord)) 0
  else {
   helper(Set.empty, List(beginWord),0,endWord,nextValidNeighbours)
  }
}
//"leet"
//"code"
//["lest","leet","lose","code","lode","robe","lost"]
//"talk"
//"tail"
//["talk","tons","fall","tail","gale","hall","negs"]
val beginWord1 = "talk"
val endWord1 = "tail"
val wordList1 = List("talk","tons","fall","tail","gale","hall","negs")

val beginWord2 = "leet"
val endWord2 = "code"
val wordList2 = List("leet","lest","lose","code","lode","robe","lost")

val beginWord3 = "a"
val endWord3 = "c"
val wordList3 = List("a","b","c")
ladderLength(beginWord3, endWord3, wordList3)

//https://leetcode.com/problems/word-search/

def helperExists(board: Array[Array[Char]], word:String, visited:Set[(Int,Int)], toVisit:List[(Int,Int)], rows:Int,col:Int):Boolean={
 // println(s"toVisit $toVisit")
  if(toVisit.isEmpty && word.nonEmpty) false
  else if(word.isEmpty) true
  else{
    val neighbours = toVisit.flatMap{case (x,y) => List((x,y+1),(x+1,y),(x-1,y),(x,y-1))}
      .filter{case (x,y) => x < rows && x>=0  && y<col && y >=0 && board(x)(y) == word.head && !visited.contains(x,y)}
    if(neighbours.nonEmpty)
    helperExists(board, word.tail, visited++toVisit, neighbours, rows, col)
    else
      false
  }
}
def exist(board: Array[Array[Char]], word: String): Boolean = {
  if(board.isEmpty && word.isEmpty) true
  else if(board.isEmpty && word.nonEmpty) false
  else {
    val initalPos = board.zipWithIndex.flatMap { case (row, x) =>
      row.zipWithIndex.filter { case (ch, _) => ch == word.head }.map(y => (x, y._2))
    }.filter { case (x, y) => x >= 0 && y >= 0 }.toList
    helperExists(board, word.tail, Set.empty, initalPos, board.length, board.head.length)
  }
}
val board = Array(Array('A','B','C','E'),Array('S','F','C','S'),Array('A','D','E','E'))
exist(Array(Array('B')), "A")

val idx = Array(1,2,3,1,0,1).indexWhere(_ ==1)

