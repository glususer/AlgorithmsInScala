import Leetcode.TreeNode
//You are given a binary tree represented by root A.
//
//Assume a BST is defined as follows:
//
//1) The left subtree of a node contains only nodes with keys less than the node's key.
//
//2) The right subtree of a node contains only nodes with keys greater than the node's key.
//
//3) Both the left and right subtrees must also be binary search trees.

def getMinMax(root:TreeNode):(Long,Long)={
 // println(s"root ${if(root!=null) root.value else "NULL"}")
  lazy val (rightMin, rightMax) = getMinMax(root.right)
  lazy val (leftMin, leftMax) = getMinMax(root.left)

  if(root.left == null && root.right == null)(root.value, root.value)
  else if(root.left == null){
    if(root.value < rightMin ) (root.value.toLong  min rightMin, root.value.toLong max rightMax)
    else (Long.MinValue, Long.MaxValue)
  }
  else if(root.right==null){
    if(root.value > leftMax) (root.value.toLong min leftMin, root.value.toLong max leftMax)
    else (Long.MinValue, Long.MaxValue)
  }
  else{
    if(root.value > leftMax && root.value < rightMin ) (root.value.toLong min leftMin min rightMin, root.value.toLong max leftMax max rightMax)
    else (Long.MinValue, Long.MaxValue)
  }
}

def isValidBST(A: TreeNode): Int  = {
  if(A == null) 0
  else {
    val (min, max) = getMinMax(A)
    if (min == Long.MinValue || max == Long.MaxValue) 0 else 1
  }
}
//7 4 -1 5 3 -1 -1 -1

val tree = TreeNode(7,TreeNode(4, TreeNode(3,null,null),TreeNode(5,null,null)),null)

isValidBST(tree)
Long.MaxValue