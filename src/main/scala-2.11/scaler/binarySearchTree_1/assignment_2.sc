import Leetcode.TreeNode
//You are given a Binary Tree A with N nodes.
//
//Write a function that returns the size of the largest subtree, which is also a Binary Search Tree (BST).
//
//If the complete Binary Tree is BST, then return the size of the whole tree.
//
//NOTE:
//
//The largest subtree is the subtree with the most number of nodes.
def getMinMax(root:TreeNode):(Long,Long,Int)={
  lazy val (rightMin, rightMax,sizeRight) = getMinMax(root.right)
  lazy val (leftMin, leftMax,sizeLeft) = getMinMax(root.left)

  if(root.left == null && root.right == null) (root.value, root.value,1)
  else if(root.left == null){
    if(root.value < rightMin ) (root.value.toLong  min rightMin, root.value.toLong max rightMax, sizeRight+1)
    else (Long.MinValue, Long.MaxValue,sizeRight)
  }
  else if(root.right==null){

    if(root.value > leftMax) (root.value.toLong min leftMin, root.value.toLong max leftMax, sizeLeft+1)

    else (Long.MinValue, Long.MaxValue,  sizeLeft )
  }
  else {
    if (root.value > leftMax && root.value < rightMin)
      (root.value.toLong min leftMin min rightMin,
        root.value.toLong max leftMax max rightMax,
        sizeLeft + sizeRight + 1)
    else

      (Long.MinValue, Long.MaxValue, sizeRight max sizeLeft)
  }
}

def solve(A: TreeNode): Int  = {
  if(A == null) 0
  else if(A.left == null && A.right == null)1
  else {
    val (min, max, size) = getMinMax(A)
    size
  }
}

val tree = TreeNode(7,
  TreeNode(2,
    TreeNode(10,null,null),
    TreeNode(5,null,null)),
  null)

solve(tree)