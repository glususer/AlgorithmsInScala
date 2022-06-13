import Leetcode.TreeNode
//Given a binary search tree of integers. You are given a range B and C.
//
//Return the count of the number of nodes that lie in the given range.
def helper(root:TreeNode, B:Int, C:Int, acc:Int):Int={
  if(root == null) acc
  else{
    val left = helper(root.left,B,C,acc)
    val right = helper(root.right, B,C,acc)

    if(B<= root.value && root.value <= C) {
    //  println(s"root ${root.value}")
      left+right+1
    }
    else left+right

  }
}

def solve(A: TreeNode, B: Int, C: Int): Int  = {
  helper(A,B,C,0)
}

val tree = TreeNode(1,
  TreeNode(2,
    TreeNode(4,null,null),
    TreeNode(5,
      TreeNode(7,null,null),
      null)),
  TreeNode(3,
    null,
    TreeNode(6,null,null)))
solve(tree,2,5)