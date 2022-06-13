import Leetcode.TreeNode
def inOrderTraversal(tree: TreeNode, acc: List[Int] = List.empty): List[Int] = {
  if (tree == null) acc
  else {
    inOrderTraversal(tree.left, acc) ::: (tree.value :: acc) ::: inOrderTraversal(tree.right, acc)
  }
}

//https://leetcode.com/problems/all-possible-full-binary-trees/

def allPossibleFBT(n: Int): List[TreeNode] = {
  if(n%2 == 0) List.empty
  else if(n == 1) List(TreeNode(0,null,null))
  else{
    (for{
       leftNodeCount  <- (1 until n by 2)
       leftTree  <- allPossibleFBT(leftNodeCount)
      rightTree <- allPossibleFBT(n-1-leftNodeCount)
    }yield TreeNode(0, leftTree, rightTree)).toList
  }
}

allPossibleFBT(3).length




