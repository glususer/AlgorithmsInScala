import Leetcode.TreeNode
//Given a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).


def helper(left:TreeNode, right:TreeNode):Boolean={
  (left,right) match{
    case(null,null) => true
    case (null, _) => false
    case (_,null) => false
    case (x,y) => if(x.value == y.value && helper(x.right, y.left) && helper(x.left, y.right)) true else false

  }
}
def isSymmetric(A: TreeNode): Int  = {
  if(A==null || A.left == null && A.right == null) 1
  else{
    if(helper(A.left, A.right)) 1
    else 0
  }
}