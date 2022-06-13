import Leetcode.TreeNode
//Given a binary tree of integers denoted by root A. Return an array of integers representing the top view of the Binary tree.
//
//The top view of a Binary Tree is a set of nodes visible when the tree is visited from the top.
//
//Return the nodes in any order.
def levelOrderWithDist(level:List[(TreeNode,Int)], acc:Map[Int,TreeNode]= Map.empty):Map[Int,TreeNode]={
  if(level.isEmpty) acc
  else{
    val children = level.flatMap{case (node,dist) => List((node.left,dist-1),(node.right, dist+1))}.filter(_._1 != null)
    val distNotInMap = level.filter{case(_,dist) => !acc.contains(dist)}.map{case(node,dist) => (dist,node)} .toMap
    levelOrderWithDist(children, acc++distNotInMap )
  }
}

def solve(A: TreeNode): Array[Int]  = {
  val distVsNodesMap = levelOrderWithDist(List((A,0)))

  val maxDist = distVsNodesMap.keys.max
  val minDist = distVsNodesMap.keys.min

  (minDist to maxDist).foldLeft(List[Int]()){case(acc, dist) =>

    val currrentDistNode = distVsNodesMap.getOrElse(dist,null).value
    acc:+currrentDistNode
  }.toArray
}