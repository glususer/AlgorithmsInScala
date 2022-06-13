import Leetcode.TreeNode
//Given a Binary Tree A consisting of N integer nodes, you need to find the diameter of the tree.
//
//The diameter of a tree is the number of edges on the longest path between two nodes in the tree.
//            1
//          /   \
//         2     3
//        / \     \
//       4   5     6

def height(current:TreeNode):Int={
  if(current == null) 0
  else{
    val left = height(current.left)
    val right = height(current.right)
    (left max right)+1
  }
}
def helper(node: TreeNode, dia:Int):Int={
  if(node == null) dia
  else{
    val left = height(node.left)
    val right = height(node.right)
    val currentDia = left+right max dia
    println(s"curr ${node.value} left $left, right $right dia $dia")
    helper(node.left, currentDia) max helper(node.right,currentDia) max currentDia
  }
}

def solve(A: TreeNode): Int  = {
  helper(A,0)
}
//21 1 2 3 4 5 -1 -1 6 -1 8 -1 7 -1 9 -1 10 -1 -1 -1 -1 -1
val tree = TreeNode(1,
  TreeNode(2,TreeNode(4,null,null),TreeNode(5,null,null)),
  TreeNode(3,null,null))
solve(tree)
height( TreeNode(1,
  TreeNode(2,null,null),
  TreeNode(3,null,null)))