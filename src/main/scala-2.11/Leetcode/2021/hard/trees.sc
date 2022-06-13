
def depth (parentToChildren:Map[Int,List[Int]], toVisit:List[Int], acc: List[List[Int]] = List.empty):List[List[Int]]= {
  if(toVisit.isEmpty) acc
  else{
    val children = toVisit.flatMap{node => parentToChildren.getOrElse(node, List.empty)}
    println(s"toVisit $toVisit, children $children")
    depth(parentToChildren, children, acc:+toVisit)
  }
}

def getAllAncestors(childToParents:Map[Int,Int], node:Option[Int], k:Int, current:Int, acc:List[Int]=List.empty):List[Int]={
  println(s"node $node, k $k current $current, acc $acc")
  if(current==k || node.isEmpty) acc
  else{
    val parent = {
      val p = node.map(childToParents)
      if(p.contains(-1)) None else p
    }
    getAllAncestors(childToParents,parent, k, current+1,if (parent.isDefined) acc:+parent.get else acc )
  }
}


  def intToBinary(num:Int):String= num.toBinaryString

def getJumps(depth: Int, i:Int = 1,acc:List[Int]= List.empty):List[Int] = {
  if(depth <= math.pow(2,i).toInt) acc
  else getJumps(depth, i+1,acc:+i)
}

def preProcessTree(dp:Array[Array[Int]], depthToNode:Map[Int, List[Int]]):Unit={
    depthToNode.foreach{case(depth, nodes) => nodes.foreach{ node =>
      val binaryJumpsTillDepth : List[Int]= getJumps(depth)
    //  println(s"binaryJumpsTillDepth $binaryJumpsTillDepth depth $depth")
      binaryJumpsTillDepth.foreach{jump => println(s" node $node jump $jump dp ${dp(jump-1)(node)}")
        dp(jump)(node) = if(jump > 0) dp(jump-1)(dp(jump-1)(node)) else dp(jump)(node)}
    }
    }
  }


def getkAsSumOfPowerOfTwo(k:Int, acc:List[Int]=List.empty, i:Int):List[Int]={
  if(k==0 || i <0 ) acc
  else{
    val diff = k-math.pow(2,i).toInt
    val (updatedK, updatedI) = if(diff >=0) (k-math.pow(2,i).toInt, i-1) else(k, i-1)
    getkAsSumOfPowerOfTwo(updatedK, if(updatedK != k)acc:+i else acc , updatedI)
  }
}

def getKthAncestor(dp:Array[Array[Int]], node:Int, k:Int):Int={
  val initialGuess = (0 to k).collectFirst{case x if math.pow(2,x)  > k => x}.getOrElse(0)-1
  val kAsSumOfPowerOfTwo = getkAsSumOfPowerOfTwo(k, List.empty, initialGuess)
  //println(s"kAsSumOfPowerOfTwo ${kAsSumOfPowerOfTwo}")
  kAsSumOfPowerOfTwo.tail.foldLeft(dp(kAsSumOfPowerOfTwo.head)(node)){case (acc, ele) => dp(ele)(acc)}
}

def startHere()

val parentToChildren = Map(0->List(1,2), 1->List(3,4), 2->List(5,6), 3->List(7), 4->List(8), 8->List(9), 6->List(10), 10->List(11), 11->List(12), 12->List(18))
//depth(parentToChildren, 0, Some(0))


val parents = Array(-1, 0, 0, 1, 1, 2, 2)
val parentToChildren2 = parents.zipWithIndex.groupBy{case(parent, _) => parent}.map{case(parent, parentWithChildren) => (parent, parentWithChildren.map(_._2).toList)}
println(s"parentToChildren2 $parentToChildren2")
getAllAncestors(parents.zipWithIndex.map{case (parent, child) => (child, parent)}.toMap, Some(2),2, 0)

val dp = Array(Array(-1,0,0,1,3,2,5,5,4,6,7), Array(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),Array(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1))
//dp.foreach(row => println(row.mkString(", ")))
val levelOrderTraversal = depth(parentToChildren, List(0))
preProcessTree(dp,levelOrderTraversal.zipWithIndex.map{case(nodes, level) => (level+1, nodes)}.toMap)
//dp.foreach(row => println(row.mkString(", ")))

getKthAncestor(dp, 1, 3)

class TreeAncestor(_n: Int, _parent: Array[Int]) {

  val parentToChildren = _parent.zipWithIndex.groupBy{case(parent, _) => parent}.map{case(parent, parentWithChildren) => (parent, parentWithChildren.map(_._2).toList)}
  val levelOrderTraversal = depth(parentToChildren, List(0))


  def getKthAncestor(node: Int, k: Int): Int = {
   ???
  }

}








