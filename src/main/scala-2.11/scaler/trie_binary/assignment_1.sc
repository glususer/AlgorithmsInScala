
//Problem Description
//Given an array of integers A, find and return the maximum result of A[i] XOR A[j], where i, j are the indexes of the array.
class TreeNode(_value: Char = '0', _left: TreeNode = null, _right: TreeNode = null) {
  var value: Char = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object TreeNode {
  def apply(_value: Char, _left: TreeNode, _right: TreeNode): TreeNode = new TreeNode(_value, _left, _right)

  def isLeaf(tree: TreeNode): Boolean = tree.right == null && tree.left == null

  def insert(tree: TreeNode, item: String): TreeNode = {
    if (!item.isEmpty) {
      if (item.length > 1) {
        val nextChar = item.charAt(1)
        if (nextChar == '0') {
          if (tree.left == null) tree.left = TreeNode('0', null, null)
          insert(tree.left, item.tail)
        } else {
          if (tree.right == null) tree.right = TreeNode('1', null, null)
          insert(tree.right, item.tail)
        }
      } else insert(null, item.tail)
    } else tree
  }

  def search(tree: TreeNode, item: String, acc: String = ""): String = {
    if (tree == null) acc
    else if (!item.isEmpty) {
      if (item.length > 1) {
        val nextChar = item.charAt(1)
        if (nextChar == '0') search(if (tree.left != null) tree.left else tree.right, item.tail, acc + tree.value)
        else search(if (tree.right != null) tree.right else tree.left, item.tail, acc + tree.value)
      } else search(null, item.tail, acc + tree.value)
    } else acc
  }


}
def prependZeroes(n: String, numOfZeroes: Int): String = (0 until numOfZeroes).map(_ => 0).mkString("") + n

def solve(A: Array[Int]): Int = {
  val maxBinaryRepr = '0' + A.max.toBinaryString
  val binarySeqs = A.map { x =>
    val binaryRepr = x.toBinaryString
    prependZeroes(binaryRepr, maxBinaryRepr.length - binaryRepr.length)
  }

  val root = TreeNode('0', null, null)
  TreeNode.insert(root, binarySeqs.head)

  val maxXor = binarySeqs.tail.foldLeft(0) { case (xor, str) =>
    val invertedString = '0' + str.tail.map { ch => if (ch == '0') '1' else '0' }.mkString
    val searchedInvertedSrting = TreeNode.search(root, invertedString)
    TreeNode.insert(root, str)

    val currentXor = Integer.parseInt(searchedInvertedSrting, 2) ^ Integer.parseInt(str, 2)
    xor max currentXor
  }
  maxXor
}

solve(Array(1, 2, 3, 4, 5))