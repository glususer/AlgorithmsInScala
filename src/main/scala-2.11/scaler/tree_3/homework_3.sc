import Leetcode.TreeNode
//Given a binary tree. Check whether the given tree is a Sum-binary Tree or not.
//
//Sum-binary Tree is a Binary Tree where the value of a every node is equal to sum of the nodes present in its left subtree and right subtree.
//
//An empty tree is Sum-binary Tree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.
//
//Return 1 if it sum-binary tree else return 0.

def helper(node:TreeNode,isSumBinaryTree:Boolean = false):(Int,Boolean)={
  if(node == null ) (0,true)
  else if(node.left == null &&  node.right == null) (node.value, true )
  else {
    val (leftSum,isSumBinaryTreeLeft)  = helper(node.left, isSumBinaryTree)
    val(rightSum, isSumBinaryTreeRight) = helper(node.right, isSumBinaryTree)
    if(leftSum+rightSum == node.value && isSumBinaryTreeLeft && isSumBinaryTreeRight)(leftSum+rightSum+node.value, true) else (leftSum+rightSum+node.value,false)
  }
}
def solve(A: TreeNode): Int  = {
 val(_, isSumBinary) =  helper(A)
  if(isSumBinary) 1 else 0
}