import Leetcode.TreeNode
//Given a binary tree, return a 2-D array with vertical order traversal of it. Go through the example and image for more details.
def levelOrderWithDist(level:List[(TreeNode,Int)], acc:List[(TreeNode,Int)]= List.empty):List[(TreeNode,Int)]={
  if(level.isEmpty) acc
  else{
    val children = level.flatMap{case (node,dist) => List((node.left,dist-1),(node.right, dist+1))}.filter(_._1 != null)
    levelOrderWithDist(children, acc++level)
  }
}

def verticalOrderTraversal(A: TreeNode): Array[Array[Int]]  = {
  val queueWithDist = levelOrderWithDist(List((A,0)))
  val maxDist = queueWithDist.maxBy(_._2)._2
  val minDist = queueWithDist.minBy(_._2)._2

  val distVsNodesMap = queueWithDist.groupBy(_._2).mapValues(lst => lst.map(_._1))

  (minDist to maxDist).foldLeft(List[List[Int]]()){case(acc, dist) =>

    val currrentDistNodes = distVsNodesMap.getOrElse(dist,List.empty).map(_.value)
    (acc:+currrentDistNodes)
  }.map(_.toArray).toArray
}