import Leetcode.TreeNode
//Given two BST's A and B, return the (sum of all common nodes in both A and B) % (109 +7) .
//
//In case there is no common node, return 0.
//
//NOTE:
//
//Try to do it one pass through the trees.
def inOrder(A:TreeNode, acc:List[Int] = List[Int]()):List[Int]={
  if(A == null) acc
  else{
    inOrder(A.left, acc):::List(A.value):::inOrder(A.right, acc)
  }
}
def solve(A: TreeNode, B: TreeNode): Int  = {
  val inorder1 =inOrder(A)
  val inorder2 = inOrder(B)
  val mod = 1000000007
  inorder1.intersect(inorder2).foldLeft(0){case(acc, ele) =>
    (acc+ele)%mod}
}