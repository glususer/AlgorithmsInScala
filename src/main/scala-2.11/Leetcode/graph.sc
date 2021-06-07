import scala.collection.mutable

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

class ListNode(_x: Int = 0, _next: ListNode = null) {
     var next: ListNode = _next
     var x: Int = _x
   }
//https://leetcode.com/problems/linked-list-in-binary-tree/
def dfs(h:ListNode, node:TreeNode):Boolean={
  (h,node) match{
    case (null,_) => true
    case (_,null) => false
    case (head,tree) if(tree.value == head.x)   => dfs(head.next,tree.right)||dfs(head.next, tree.left)
    case (_,_) => false
  }
}

def isSubPath(head: ListNode, root: TreeNode): Boolean = {
  if(root == null) false
  else{
    if(dfs(head,root)) true
    else{
      isSubPath(head, root.left) || isSubPath(head, root.right)
    }
  }
}



//https://leetcode.com/problems/all-paths-from-source-to-target/
def allPathsSourceTarget(graph: Array[Array[Int]]): List[List[Int]] = {
 val nodeToEdge = graph.zipWithIndex.map{case(edges,node)=>(node,edges.toList)}.toMap

  def helper(acc:List[List[Int]]):List[List[Int]]={
    acc match{
      case output if(output.forall(_.last == graph.length-1) || output.forall(lst => nodeToEdge.getOrElse(lst.last,List.empty).isEmpty)) => acc
      case _ => val newEdgeList= acc.flatMap {l =>
        if(nodeToEdge.getOrElse(l.last,List.empty).nonEmpty)nodeToEdge(l.last)map(ele => l:+ele) else List(l)
      }
        helper(newEdgeList)
    }

  }
  helper(List(List(0))).filter(_.last == graph.length-1)
}
//Input: graph = Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
allPathsSourceTarget(Array(Array(4,3,1),Array(3,2,4), Array(3), Array(), Array()))

//https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/


def minTime(n: Int, edges: Array[Array[Int]], hasApple: List[Boolean]): Int = {
  val hasArr = hasApple.toArray
  val edgeArray = edges.map{case Array(from, to) => (from,to)}
    .groupBy(_._1)
    .mapValues(toArr => toArr.map { case (_, to) => to })

  def navigate(node:Int):Int={
    edgeArray.get(node) match{
      case None=> if(hasArr(node)) 2 else 0
        case Some(children) => val childerenTime = children.map(navigate).sum
        if(hasArr(node) || childerenTime!=0) childerenTime+2 else 0
    }
  }
  edgeArray(0).map(navigate).sum
}
minTime(7,Array(Array(0,1),Array(0,2),Array(1,4),Array(1,5),Array(2,3),Array(2,6)),
List(false,false,false,false,false,false,false))

//https://leetcode.com/problems/course-schedule/
//https://www.youtube.com/watch?v=rKQaZuoUR4M, goal is to find, if there is a cycle in the Graph,
// if there is then the course schedule cannot be completed else it canbe.

def moveVertex(current: Int, sourceSet: mutable.HashSet[Int], destinationSet: mutable.HashSet[Int]):Unit = {
  sourceSet.remove(current)
  destinationSet.add(current)
}

def dfsFindCycle(current: Int, whiteSet: mutable.HashSet[Int], greySet: mutable.HashSet[Int], blackSet: mutable.HashSet[Int],edgeArray:Map[Int,Array[Int]]):Boolean = {
  moveVertex(current, whiteSet,greySet)
  val neighbours = edgeArray.getOrElse(current,Array.empty)
  val hasCycle = neighbours.filterNot(blackSet.contains).exists{
    neighbour => greySet.contains(neighbour) || dfsFindCycle(neighbour, whiteSet, greySet,blackSet, edgeArray)
  }
  moveVertex(current, greySet, blackSet)
  println(whiteSet, greySet, blackSet)
  hasCycle
}


def canFinish(numCourses: Int, prerequisites: Array[Array[Int]]): Boolean = {
  val edgeArray = prerequisites.map{case Array(to, from) => (to,from)}
    .groupBy(_._2)
    .mapValues(toArr => toArr.map { case (to, _) => to }).toMap
  println(s"adjacencyList ${edgeArray.mapValues(_.toList)}")

  val whiteSet  :mutable.HashSet[Int]=mutable.HashSet.empty
  val greySet :mutable.HashSet[Int]=mutable.HashSet.empty
  val blackSet :mutable.HashSet[Int] = mutable.HashSet.empty

  edgeArray.keys.foreach{key => whiteSet.add(key)}

  while(whiteSet.nonEmpty){
    val current = whiteSet.iterator.next()
    val cycleExists = dfsFindCycle(current, whiteSet, greySet,blackSet,edgeArray)
    if(cycleExists) return false
  }
   true
}

//canFinish(2,Array(Array(1,0),Array(0,1)))

//https://leetcode.com/problems/is-graph-bipartite/
def helperBiPartite(visited:Array[Int], toVisit:Int, edgeArr:Map[Int,Array[Int]]):Boolean={
  val neighbours = edgeArr.getOrElse(toVisit,Array.empty)

  if(visited(toVisit) == 0) visited(toVisit) = 1

  val unvisitedNeighbours = neighbours.filter(neighbour => visited(neighbour) == 0)

  unvisitedNeighbours.foreach{neighbour =>if(visited(toVisit) == 1) visited(neighbour) =2 else visited(neighbour) = 1}

  if (neighbours.exists{neighbour => visited(toVisit) == visited(neighbour)}) false
    else unvisitedNeighbours.forall{neighbour => helperBiPartite(visited, neighbour, edgeArr)}
}

def isBipartite(graph: Array[Array[Int]]): Boolean = {
  val edgeArray = graph.zipWithIndex.map{case (toArr, from) => (from,toArr)}.toMap
  val visited = Array.fill(graph.length)(0)
  edgeArray.keys.forall(node => helperBiPartite(visited, node, edgeArray))
}

isBipartite(Array(Array(1,3),Array(0,2),Array(1,3),Array(0,2)))

//https://leetcode.com/problems/accounts-merge/
//TLE ,  https://www.youtube.com/watch?v=0jNmHPfA_yE
def helperAccountsMerge(accounts: List[List[String]], emailName:mutable.HashMap[String, (String,List[Int])], idx:Int):mutable.HashMap[String, (String,List[Int])]={
  accounts match{
    case Nil => emailName
    case _ =>   val (name, emails) = (accounts.head.head, accounts.head.tail)
      val(present,absent) = emails.partition(emailName.contains)
      present.foreach{email => val prevList = emailName(email)
        emailName(email) = (prevList._1,prevList._2:+idx)}
      absent.foreach{email => emailName += (email -> (name,List(idx)))}
      helperAccountsMerge(accounts.tail, emailName, idx+1)
  }
}
def find(parentList:Array[Array[Int]],toVisit:Array[Int],visited:Set[Int]):Set[Int]={
  if(toVisit.isEmpty) visited
  else{
    val neighbours = toVisit.
      flatMap(node => parentList(node).filter(_ > 0))
      .filterNot(visited.contains)
    find(parentList, neighbours, visited++toVisit)
  }
}
def accountsMerge(accounts: List[List[String]]): List[List[String]] = {

  val edgeArr = Array.fill(accounts.length,accounts.length)(0)

  helperAccountsMerge(accounts, mutable.HashMap.empty,0).values.groupBy(lst => lst._2.head)
      .mapValues(lst => lst.flatMap(_._2).toList.distinct).values.foreach{lst =>
    val from = lst.head
    val to = lst.tail
    if(to.nonEmpty){
      to.foreach{node => edgeArr(from)(node) = node
      edgeArr(node)(from) =from}
    }
  }
  val outputArr = accounts.indices.toArray
  for(i <- outputArr.indices){
    val neighbours = find(edgeArr,edgeArr(i).filter(_>0), Set.empty)
        .filter(neighbour => outputArr(neighbour) != outputArr(i))
    neighbours.foreach(neighbour => outputArr(neighbour) = i)
  }

  accounts.map(account => account.tail).zip(outputArr).groupBy{case (emails, idx) => idx}
    .mapValues{value => value.flatMap(lst => lst._1).distinct.sorted}.toList
      .map{case (idx,emails) => accounts(idx).head+:emails}

}

def accountsMerge2(accounts: List[List[String]]): List[List[String]] = {
  val edges = accounts.zipWithIndex.flatMap{case (account,idx) => account.tail.map(email => (email,idx))}
    .groupBy{case (email,_) => email}
    .mapValues(lst => lst.map(_._2).sorted.sliding(2,1).filter(_.size > 1))
    .filter(_._2.nonEmpty)
    .map{case (_,value) => value.map(_.toArray).toArray}.toArray.flatten

  val groupArr = Array.fill(edges.flatten.max+1)(-1).zipWithIndex.map(ele => (Array(ele._1, ele._2)))

  val redundantEdgesArr = edges.foldLeft(List.empty[(Int,Int)]){case (acc,ele) => val(to,from) = (ele.head,ele.last)
    if(groupArr(to)(0) != -1 && groupArr(to)(0) == groupArr(from)(0) ) {
      acc:+(to,from)
    }
    else {
      val fromNieghbours = groupArr.tail.filter{ele => groupArr(from).head != -1 && ele.head == groupArr(from).head}
      val toNeighbours = groupArr.tail.filter{ele => groupArr(to).head != -1 && ele.head == groupArr(to).head}
      (fromNieghbours ++ toNeighbours).foreach{neighbour => groupArr(neighbour.last)(0)= to}
      groupArr(from)(0) = to
      groupArr(to)(0) = to
      acc
    }
  }
  val x: Map[Int, Array[Array[Int]]] = groupArr.groupBy{seq => seq.head}
      //.mapValues(arr => a)
  x.foreach(_._2.foreach(a => println(a.toList)))
  //    .mapValues{arr => a}
  println(s"groupArr ${groupArr.map(_.toList).toList}")
  List.empty

}
val input1=List(
  List("a","abc.com","azz.com","add.com","mnn.com"),
  List("a","bcd.com","bnn.com"),
  List("a","azz.com","mnn.com"),
  List("a","bcd.com","jkl.com")
  ,List("a","mnn.com","kkk.com"),
  List("m","lll.com"))
  val input2 = List(
    List("D","D0@m.co","D1@m.co"),
    List("D","D3@m.co","D4@m.co"),
    List("D","D4@m.co","D5@m.co"),
    List("D","D2@m.co","D3@m.co"),
    List("D","D1@m.co","D2@m.co"))
accountsMerge2(input2)

def findRedundantConnection(edges: Array[Array[Int]]): Array[Int] = {
  val groupArr = Array.fill(edges.flatten.max+1)(0).zipWithIndex.map(ele => (Array(ele._1, ele._2)))
  val redundantEdgesArr = edges.foldLeft(List.empty[(Int,Int)]){case (acc,ele) => val(to,from) = (ele.head,ele.last)
    if(groupArr(to)(0) != 0 && groupArr(to)(0) == groupArr(from)(0) ) {
    acc:+(to,from)
  }
  else {
   val fromNieghbours = groupArr.tail.filter{ele => groupArr(from).head !=0 && ele.head == groupArr(from).head}
   val toNeighbours = groupArr.tail.filter{ele => groupArr(to).head !=0 && ele.head == groupArr(to).head}
      (fromNieghbours ++ toNeighbours).foreach{neighbour => groupArr(neighbour.last)(0)= to}
    groupArr(from)(0) = to
    groupArr(to)(0) = to
      acc
  }
 }
  List(redundantEdgesArr.last._1, redundantEdgesArr.last._2).toArray
}
val arr1= Array(Array(9,10),Array(5,8),Array(2,6),Array(1,5),Array(3,8),Array(4,9),Array(8,10),Array(4,10),Array(6,8),Array(7,9))
//findRedundantConnection(arr1)

/*val accounts = input1
val merged = accounts.zipWithIndex.flatMap{case (account,idx) => account.tail.map(email => (email,idx))}
  .groupBy{case (email,_) => email}
  .mapValues(lst => lst.map(_._2).sorted.sliding(2,1).filter(_.size > 1))
  .filter(_._2.nonEmpty)
  .map{case (_,value) => value.map(_.toArray).toArray}.toArray.flatten


 val z =  Array(Array(Array(1,2),Array(2,3),Array(3,4)),
    Array(Array(4,5),Array(5,6)),
    Array(Array(6,7),Array(7,8),Array(8,9)),
    Array(Array(9,10)))
z.flatten*/

//https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/
// no of vertices with 0 inbound edges
def findSmallestSetOfVertices(n: Int, edges: List[List[Int]]): List[Int] = {
  (0 until n).diff(edges.groupBy(lst => lst.head).mapValues(lst => lst.map(_.last)).values.flatten.toList.distinct).toList
}

findSmallestSetOfVertices(6,List(List(0,1),List(0,2),List(2,5),List(3,4),List(4,2)))

//https://leetcode.com/problems/count-servers-that-communicate/

def countServers(grid: Array[Array[Int]]): Int = {
  val serversInCol = grid(0).indices.map(row => grid.indices.map(col => grid(col)(row)).sum).toArray
  grid.map{row =>
    val serversInRow = row.sum
    row.zipWithIndex.foldLeft(0){ case (acc1, col) =>
    if (col._1 == 1 && serversInCol(col._2) + serversInRow > 2) acc1+1 else acc1
  }}.sum

}

countServers(Array(Array(1,1,0,0,1,1),Array(0,0,1,0,0,0),Array(0,0,1,0,1,1)))

