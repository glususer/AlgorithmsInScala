def dfs(toVisit:Int, edgeMap:Map[Int,Array[Int]], count:Int, setOfNodes :Array[Int]):Int={
  if (setOfNodes(toVisit) == 0) count
  else{
    val neighbours = edgeMap.getOrElse(toVisit, Array.emptyIntArray).filter(neighbour => setOfNodes(neighbour) !=0)
    setOfNodes(toVisit) = 0
    neighbours.map(neighbour => dfs(neighbour, edgeMap, count, setOfNodes)).sum+1
  }
}

def connectedComponent(n:Int, edges:Array[String]):Int={
  val edgeMap = edges.map(edge => edge.split(" "))
    .flatMap(arr => List((arr.head.toInt, arr.last.toInt), (arr.last.toInt, arr.head.toInt)))
    .groupBy{case (from, to) => from}
    .mapValues(adjList => adjList.map(_._2))

  val setOfNodes  = (0 to n).toArray

 val z =  setOfNodes.zipWithIndex.tail.foldLeft(0){case (acc, ele) => if(setOfNodes(ele._2) != 0) {
    val count = dfs(ele._1, edgeMap, 0, setOfNodes)
    val sqrt = math.ceil(math.sqrt(count*1.0)).toInt
   println(s"coutn is $sqrt for ${ele} and array is ${setOfNodes.toList}")
    acc+sqrt
  } else acc}


  z
}

connectedComponent(8, Array("4","8 1","5 8","7 3","8 6"))

//https://leetcode.com/problems/k-similar-strings/
def swap(i:Int, j:Int, source:String):String ={
  val temp = source(i)
  val x = source.toCharArray
  x(i) = x(j)
  x(j) = temp
  x.toList.mkString("")
}
def getNeighbours(target: String, source: String): List[String] = {
  val neighbours = target.zipWithIndex.foldLeft(List[String]()) { case (acc, ele) =>
    val i = ele._2
    if (target.charAt(i) == source.charAt(i)) acc
    else {
      val j = source.slice(i, source.length).zipWithIndex.filter(_._1 == ele._1)
        .map{case(ch, idx) => (ch, idx + i)}
        .filterNot{case(ch, idx) =>  source(idx) == target(idx)}
        .headOption.map(_._2).getOrElse(-1)
      //  val j = source.indexOf(ele._1)
      if (j != -1) {
        val x = acc :+ swap(i, j, source)
        //  println(s"source is $source")
        x
      }
      else acc
    }
  }.filter(_ != source)

  println(s"neighbours for $source are $neighbours")
  neighbours
}

def bfs(toVisit:List[String],visited:Set[String], count:Int, target:String):Int={
  println(s"toVisit $toVisit and count is $count, visited is $visited")
  if(toVisit.isEmpty)count
  else if (toVisit.contains(target))count+1
  else{
    val neighbours = toVisit.
      flatMap(node => getNeighbours(target,node).
        filter(_.nonEmpty).filterNot(visited.contains)).take(20)
   // if(neighbours.contains(target)) count+1
      bfs(neighbours, visited++toVisit.toSet, count+1, target)
  }
}
def kSimilarity(A: String, B: String): Int = {
  val initialNeighbours = getNeighbours(A,B)

  bfs(initialNeighbours, Set.empty, 0, A)
}

kSimilarity("abcdeabcdeabcdeabcde","aaaabbbbccccddddeeee")
"abcdeabcdeabcdeabcde"
"aaaabbbbccccddddeeee"
