import Leetcode.TreeNode
//Given a binary tree of integers denoted by root A. Return an array of integers representing the right view of the Binary tree.
//
//Right view of a Binary Tree is a set of nodes visible when the tree is visited from Right side.

def levelOrder(level:List[TreeNode], acc:List[Int]):List[Int]={
  if(level.isEmpty) acc
  else{
    val children = level.flatMap(node => List(node.left, node.right)).filter(_ != null)
    levelOrder(children, acc:+level.map(_.value).last)
  }
}
def solve(A: TreeNode): Array[Int]  = {
  levelOrder(List(A), List.empty).toArray
}