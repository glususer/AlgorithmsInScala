import Leetcode.TreeNode
//Given a binary tree of integers. Return an array of integers representing the left view of the Binary tree.
//
//Left view of a Binary Tree is a set of nodes visible when the tree is visited from Left side
//
//NOTE: The value comes first in the array which have lower level.

def levelOrder(level:List[TreeNode], acc:List[Int]):List[Int]={
  if(level.isEmpty) acc
  else{
    val children = level.flatMap(node => List(node.left, node.right)).filter(_ != null)
    levelOrder(children, acc:+level.map(_.value).head)
  }
}
def solve(A: TreeNode): Array[Int]  = {
  levelOrder(List(A), List.empty).toArray
}