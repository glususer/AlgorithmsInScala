import Leetcode.TreeNode
//Given a root of binary tree A, determine if it is height-balanced.
//
//A height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
def isBalanced(A: TreeNode): Int  = {
  def height(root:TreeNode):Int={
    if(root == null) -1
    else{
      val l = height(root.left)
      val r = height(root.right)
      if(l == Integer.MAX_VALUE || r == Integer.MAX_VALUE ||  (math.abs(l-r) > 1)) Integer.MAX_VALUE
      else (l max r)+1
  }
  }
  val  hgt = height(A)
  if(hgt == Integer.MAX_VALUE) 0 else 1
}

val tree = TreeNode(1,
  TreeNode(2,null,null),
  TreeNode(3,
    TreeNode(4,
      TreeNode(5, null, null),
      null),
    null))

isBalanced(tree)