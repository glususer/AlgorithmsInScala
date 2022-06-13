import Leetcode.TreeNode
//Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

def hasPathSum(A: TreeNode, B: Int): Int  = {
  if(A == null) 0
  else if(A.left == null && A.right == null){
   if(B-A.value==0) 1 else 0
  }
  else
    if(hasPathSum(A.left, B-A.value)  == 1 || hasPathSum(A.right, B-A.value) == 1) 1 else 0
}