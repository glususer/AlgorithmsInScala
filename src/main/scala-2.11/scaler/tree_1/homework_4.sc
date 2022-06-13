import Leetcode.TreeNode
//Given the root node of a Binary Tree denoted by A. You have to Serialize the given Binary Tree in the described format.
//
//Serialize means encode it into a integer array denoting the Level Order Traversal of the given Binary Tree.
//
//NOTE:
//
//In the array, the NULL/None child is denoted by -1.
//For more clarification check the Example Input.
def helper(level:List[TreeNode], acc:List[List[Int]]=List.empty):List[List[Int]]={
  if(level.forall(_== null))acc:+level.map(_ => -1)
  else {
    helper(level.filter(_ != null).flatMap(node => List(node.left, node.right)),acc:+level.map{ node => if(node == null) -1  else node.value})
  }
}

def solve(A: TreeNode): Array[Int]  = {
  helper(List(A)).flatten.toArray
}
val tree = TreeNode(1,
  TreeNode(6,
    TreeNode(4,
      TreeNode(5,null,null),
      TreeNode(10,null,null)),
    null),
  TreeNode(2,TreeNode(3,null,null),TreeNode(8,null,null)))
solve(tree)