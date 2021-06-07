import scala.collection.immutable.Map
import scala.collection.mutable

def helper(node: Int, nodeToEdgeMap: Map[Int, Array[Int]], nodeToLabel: Map[Int, Char], parentLabel: Char, count: Int): Int = {
  val children = nodeToEdgeMap.getOrElse(node, Array.empty)
  children.length match {
    case 0 => count + (if (nodeToLabel(node) == parentLabel) 1 else 0)
    case 1 => val leftCount = helper(children.head, nodeToEdgeMap, nodeToLabel, parentLabel, count)
              leftCount + (if (nodeToLabel(node) == parentLabel) 1 else 0)
    case _ => val leftCount = helper(children.head, nodeToEdgeMap, nodeToLabel, parentLabel, count)
              val rightCount = helper(children.last, nodeToEdgeMap, nodeToLabel, parentLabel, count)
              leftCount + rightCount + (if (nodeToLabel(node) == parentLabel) 1 else 0)
  }
}

def countSubTrees(n: Int, edges: Array[Array[Int]], labels: String): Array[Int] = {
  val nodeToLabel = (0 until n zip (labels)).toMap
  val nodeToEdgeMap = edges.groupBy(arr => arr.head).mapValues(arr => arr.map(ele => ele.last)).toMap
  (0 until n).toList.map(node => helper(node, nodeToEdgeMap, nodeToLabel, nodeToLabel(node), 0)).toArray
}

//countSubTrees(4,Array(Array(0,2),Array(0,3),Array(1,2)), "aeed")
val labels = "abaedbd"
val edges = Array(Array(0, 2), Array(0, 3), Array(1, 2))
val nodeToLabel = (0 until 4 zip (labels)).toMap
val nodeToEdgeMap = edges.groupBy(arr => arr.head).mapValues(arr => arr.map(ele => ele.last))
//val node = 1
//helper(node,nodeToEdgeMap, nodeToLabel,nodeToLabel(node),0)

//https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
def maxLength(arr: List[String]): Int = {
  var max = 0
  def isUnique(s: String): Boolean = s.size == s.toSet.size

  def dfs(start: Int, str: String, arr: List[String]): Unit = {
    if(isUnique(str)) {
      max = Math.max(max, str.length)
      for(i <- start until arr.size) {
        println(i,str+arr(i),str)
        dfs(i+1, str + arr(i), arr)
      }
    }
  }
  dfs(0, "", arr)
  max
}
val arr = List("un","iq","ue")

maxLength(arr)

//https://leetcode.com/problems/frog-jump/
def helperCanCrossBFS(stones: Array[Int], visited:Set[Int], toVisit:Int,idx:Int, currentStep:Int):Boolean={
 println(s"visited $visited  idx $idx currentStep $currentStep toVisit ${toVisit}")
  if(visited.contains(stones.last)|| (toVisit == stones.last && visited.contains(toVisit-currentStep))) true
  else if(! visited.contains(stones.last) && idx >= stones.length) false
  else{
    val neighbours = List(currentStep-1, currentStep, currentStep+1)
      .map(step => (toVisit+step,step))
      .filter{case (nextStone,step)=> stones.contains(nextStone)}
      .filterNot{case (nextStone,step)=> visited.contains(nextStone)}
     println(s" neighbours $neighbours")
      neighbours.exists { case (nextStone, step) => helperCanCrossBFS(stones, visited + toVisit, nextStone, idx + 1, step) }
  }
}

def helperCanCrossDFS(stones: Array[Int], visited:Set[Int], toVisit:Int,idx:Int, currentStep:Int):Set[Int]={
  println(s"idx $idx toVisit ${toVisit} step $currentStep")
  if(visited.contains(stones.last)) visited
  else{
    val neighbours = List(currentStep-1, currentStep, currentStep+1)
      .map(step => (toVisit+step,step))
      .filter{case (nextStone,step)=> stones.contains(nextStone) && step >0}
    println(s" neighbours $neighbours")
    neighbours.foldLeft(visited+toVisit)((acc,node) => helperCanCrossDFS(stones,acc,node._1,idx+1, node._2))
  }
}
def updateAcc(stones: Array[Int],acc:mutable.Map[Int,Set[Int]], curr : Int):mutable.Map[Int,Set[Int]]={
 val newStepsWithValidStones = acc.getOrElse(curr,Set.empty)
   .flatMap(step => List(step-1,step,step+1)
   .filter(step => step >0 && stones.contains(step+curr)))

  newStepsWithValidStones.foreach(step => acc.update(curr+step,Set(step)++acc.getOrElse(curr+step,Set())))
  acc

}
def canCross(stones: Array[Int]): Boolean = {

  val result = stones.drop(1).foldLeft(mutable.Map(stones(0)+1->Set(1)))
  {
    case (acc,ele) => updateAcc(stones,acc,ele)
  }
  result.exists{case (stone,steps) => stone == stones.last && steps.nonEmpty}
}

canCross(Array(0,1,2,3,4,8,9,11))

def helperExists(board: Array[Array[Char]], word:String, visited:Set[(Int,Int)], toVisit:(Int,Int), rows:Int,col:Int, wlen:Int,clen:Int):Boolean={
  if(wlen == clen) true
  else if (visited.contains(toVisit)) false
  else{
    val neighbours = List((toVisit._1,toVisit._2+1),(toVisit._1+1,toVisit._2),(toVisit._1-1,toVisit._2),(toVisit._1,toVisit._2-1))
      .filter{case (x,y) => x < rows && x>=0  && y<col && y >=0 && board(x)(y) == word.head && !visited.contains(x,y)}
    if(neighbours.isEmpty) false
    else {
      neighbours.exists(neighbour => helperExists(board, word.tail, visited+toVisit,neighbour,rows,col,wlen,clen+1))
    }
  }
}
def exist(board: Array[Array[Char]], word: String): Boolean = {
  if(board.isEmpty && word.isEmpty) true
  else if(board.isEmpty && word.nonEmpty) false
  else {
    val initalPos = board.zipWithIndex.flatMap { case (row, x) =>
      row.zipWithIndex.filter { case (ch, _) => ch == word.head }.map(y => (x, y._2))
    }.toList
    if(initalPos.isEmpty) false
    else{
      initalPos.exists(pos => helperExists(board, word.tail, Set.empty, pos, board.length, board.head.length,word.length,1))
    }
  }
}

val board = Array(Array('A','B','C','E'),Array('S','F','C','S'),Array('A','D','E','E'))
val word1 = "ABCESEEEFS"
val board2 = Array(Array('A','B','C','E'),Array('S','F','E','S'),Array('A','D','E','E'))
exist(board, "ABCB")
