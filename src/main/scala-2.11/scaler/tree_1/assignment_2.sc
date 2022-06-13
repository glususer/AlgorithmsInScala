
//Given preorder and inorder traversal of a tree, construct the binary tree.
//
//NOTE: You may assume that duplicates do not exist in the tree.

class TreeNode(val xc: Int) {
  var value: Int = xc
  var left: TreeNode = null
  var right: TreeNode = null
}

object TreeNode {
  def apply(_x: Int, _left: TreeNode, _right: TreeNode): TreeNode = new TreeNode(_x)
}

def inOrderTraversal(tree: TreeNode, acc: List[Int] = List.empty): List[Int] = {
  if (tree == null) acc
  else {
    inOrderTraversal(tree.left, acc) ::: (tree.value :: acc) ::: inOrderTraversal(tree.right, acc)
  }
}
// A-> inorder
//B-> postOrder
def buildTree(A: Array[Int], B: Array[Int]): TreeNode = {
  var postIdx = B.length - 1
  val nodeVsIndexMap = A.zipWithIndex.toMap

  def buildTreeRec(low: Int, high: Int): TreeNode = {
    if (low > high) return null

    val root = TreeNode(B(postIdx), null, null)
    val idx = nodeVsIndexMap.getOrElse(B(postIdx), -1)
   // println(s"low $low high $high idx $idx postIdx $postIdx node ${B(postIdx)}")
    postIdx -= 1
    root.right = buildTreeRec(idx + 1, high)
    root.left = buildTreeRec(low, idx - 1)
    root
  }

  buildTreeRec(0, A.length - 1)
}

val tree = buildTree(Array(6, 1, 3, 2), Array(6, 3, 2, 1))
//tree.left.value
println(inOrderTraversal(tree))