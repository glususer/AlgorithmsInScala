import Leetcode.TreeNode
//Given a binary search tree represented by root A, write a function to find the Bth smallest element in the tree.

def inorder(A:TreeNode, acc:List[Int]):List[Int]={
  if(A==null) acc
  else{
    inorder(A.left,acc):::List(+A.value):::inorder(A.right,acc)
  }
}
def kthsmallest(A: TreeNode, B: Int): Int  = {
  val soreted = inorder(A,List.empty).toArray
  soreted(B-1)
}