import Leetcode.TreeNode
//Given two binary trees, check if they are equal or not.
//
//Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

def isSameTree(A: TreeNode, B: TreeNode): Int  = {
  if(A==null && B==null) 1
  else if(A == null && B != null || A!= null && B==null)  0
  else{
    if(isSameTree(A.left, B.left)==1 && isSameTree(A.right, B.right) ==1 && A.value == B.value)1
    else 0
  }
}