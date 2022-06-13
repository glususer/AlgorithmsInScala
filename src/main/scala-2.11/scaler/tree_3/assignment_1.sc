import Leetcode.TreeNode
//Given a binary tree A, invert the binary tree and return it.
//
//Inverting refers to making the left child the right child and vice versa.
def invertTree(A: TreeNode): TreeNode  = {
  if(A == null) null
  else {
    val temp = A.left
    A.left = invertTree(A.right)
    A.right = invertTree(temp)
    A
  }
}