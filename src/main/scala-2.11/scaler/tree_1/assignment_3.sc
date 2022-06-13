import Leetcode.TreeNode
//Given a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

def inOrderTraversal(tree: TreeNode, acc: List[Int] = List.empty): List[Int] = {
  if (tree == null) acc
  else {
    inOrderTraversal(tree.left, acc) ::: (tree.value :: acc) ::: inOrderTraversal(tree.right, acc)
  }
}
def helper(level:List[TreeNode], acc:List[List[Int]]=List.empty):List[List[Int]]={
  //println(s"acc $acc level ${level.map(_.value)}")
  if(level.isEmpty)acc
  else
    helper(level.flatMap(node => List(node.left, node.right)).filter(_ !=null),acc:+level.map(_.value))
}

def levelOrder(A: TreeNode): Array[Array[Int]]  = {
  helper(List(A)).map(_.toArray).toArray
}
val ancestorTree = TreeNode(8,
  TreeNode(3,TreeNode(1,null,null),TreeNode(6,TreeNode(4,null,null),TreeNode(7,null,null))),
  TreeNode(10,null, TreeNode(14,TreeNode(13,null,null),null)))


levelOrder(ancestorTree)