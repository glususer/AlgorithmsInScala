import Leetcode.TreeNode
//Two elements of a binary search tree (BST), represented by root A are swapped by mistake. Tell us the 2 values swapping which the tree will be restored.
//
//A solution using O(n) space is pretty straightforward. Could you devise a constant space solution?
def inOrder(A:TreeNode, acc:List[Int] = List[Int]()):List[Int]={
  if(A == null) acc
  else{
    inOrder(A.left, acc):::List(A.value):::inOrder(A.right, acc)
  }
}
def recoverTree(A: TreeNode): Array[Int]  = {
  val inorderArr = ((Integer.MIN_VALUE)::inOrder(A)):+Integer.MAX_VALUE
  val lst = (inorderArr.zip(inorderArr.sorted)).filter{case(a,b) => a!=b}.head
  Array(lst._1, lst._2)
}