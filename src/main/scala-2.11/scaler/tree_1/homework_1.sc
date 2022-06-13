import scala.collection.mutable
//Given preorder and inorder traversal of a tree, construct the binary tree.
//
//NOTE: You may assume that duplicates do not exist in the tree.

class TreeNode(val xc: Int){
  var value: Int = xc
  var left: TreeNode = null
  var right: TreeNode = null
}

object TreeNode{
  def apply(_x: Int, _left: TreeNode, _right: TreeNode): TreeNode = new TreeNode(_x)
}

def inOrderTraversal(tree: TreeNode, acc: List[Int] = List.empty): List[Int] = {
  if (tree == null) acc
  else {
    inOrderTraversal(tree.left, acc) ::: (tree.value :: acc) ::: inOrderTraversal(tree.right, acc)
  }
}
def buildTree(A: Array[Int], B: Array[Int]): TreeNode  = {
  var preIdx = 0
  val nodeVsIndexMap = B.zipWithIndex.toMap

  def buildTreeRec(low:Int, high:Int):TreeNode ={
    if(low > high)  null
    else {
      val root = TreeNode(A(preIdx), null, null)
      val idx = nodeVsIndexMap.getOrElse(A(preIdx),-1)
      preIdx += 1
      root.left = buildTreeRec(low, idx - 1)
      root.right = buildTreeRec(idx + 1, high)
      root
    }
  }
  buildTreeRec(0, B.length - 1)
}

val tree = buildTree(Array(1, 2, 3, 4, 5), Array(3, 2, 4, 1, 5))
//tree.left.value
println(inOrderTraversal(tree))