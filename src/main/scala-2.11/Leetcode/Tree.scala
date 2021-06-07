package Leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object TreeNode {
  def apply(_value: Int, _left: TreeNode, _right: TreeNode): TreeNode = new TreeNode(_value, _left, _right)

  def isLeaf(tree: TreeNode): Boolean = tree.right == null && tree.left == null

  def insert(tree: TreeNode, item: Int): TreeNode = {
    if (tree == null) TreeNode(item, null, null)

    else if (isLeaf(tree)) {
      if (tree.value > item) tree.left = TreeNode(item, null, null)
      else tree.right = TreeNode(item, null, null)
      tree
    }

    else {
      if (tree.value < item) tree.right = insert(tree.right, item)
      else tree.left = insert(tree.left, item)
      tree
    }
  }

  def preOrderTreeTraversal(tree: TreeNode, acc: List[Int]): List[Int] = {
    if (tree == null) acc
    else {
      (tree.value :: acc) ::: preOrderTreeTraversal(tree.left, acc) ::: preOrderTreeTraversal(tree.right, acc)
    }
  }

  def postOrderTreeTraversal(tree: TreeNode, acc: List[Int]): List[Int] = {
    if (tree == null) acc
    else {
      postOrderTreeTraversal(tree.left, acc) ::: postOrderTreeTraversal(tree.right, acc) ::: (tree.value :: acc)
    }
  }

  def inOrderTraversal(tree: TreeNode, acc: List[Int]): List[Int] = {
    if (tree == null) acc
    else {
      inOrderTraversal(tree.left, acc) ::: (tree.value :: acc) ::: inOrderTraversal(tree.right, acc)
    }
  }

  def depth(treeNode: TreeNode): Int = {
    if (treeNode == null) 0
    else {
      math.max(depth(treeNode.left), depth(treeNode.right)) + 1
    }
  }

  def longestPathThroughNode(tree: TreeNode): Int = {
    depth(tree)
  }

  def diameter(treeNode: TreeNode): Int = {
    if (treeNode == null) 0
    else {
      val leftDia = diameter(treeNode.left)
      val rightDia = diameter(treeNode.right)
      val longestPath = depth(treeNode.left) + depth(treeNode.right) + 1
      math.max(math.max(leftDia, rightDia), longestPath)
    }
  }

  def treeFromInorderPreOrder(inorder: List[Int], preOrder: List[Int]): TreeNode = {
    def helper(in: List[Int], pre: List[Int], tree: TreeNode): TreeNode = {
      (in, pre) match {
        case (List(), _) => null
        case (_, List()) => null
        case (x :: Nil, y :: Nil) => TreeNode(x, null, null)
        case (x :: xs, y :: ys) => {
          val (leftIn, rightIn) = in.span(_ != y)
          TreeNode(y, helper(leftIn, ys.take(leftIn.size), tree), helper(rightIn.tail, ys.drop(leftIn.size), tree))
        }

      }
    }

    helper(inorder, preOrder, null)
  }

  def treeFromInorderPostOrder(inorder: List[Int], postOrder: List[Int]): TreeNode = {
    def helper(in: List[Int], post: List[Int], tree: TreeNode): TreeNode = {
      (in, post) match {
        case (List(), _) => null
        case (_, List()) => null
        case (x :: Nil, y :: Nil) => TreeNode(x, null, null)
        case (x :: xs, ys :+ y) => {
          val (leftIn, rightIn) = in.span(_ != y)
          TreeNode(y, helper(leftIn, ys.take(leftIn.size), tree), helper(rightIn.tail, ys.drop(leftIn.size), tree))
        }

      }
    }
    helper(inorder, postOrder, null)

  }

  def deepestLeavesSum(root: TreeNode):Int={
    val maxDepth = depth(root)
    def helper(tree:TreeNode, sum:Int, currentDepth:Int):Int={
      if(tree == null) sum
      else{
        if(currentDepth == maxDepth) sum+tree.value
        else
          {
           val sumLeft =  helper(tree.left, sum, currentDepth+1)
           val sumRight =  helper(tree.right, sum, currentDepth+1)
            sumLeft+sumRight
          }
      }
    }

    helper(root, 0,1)
  }

  def atLevel(treeNode: TreeNode, level:Int, acc:List[Int]):List[Int]={
    (treeNode, level) match{
      case(null,_) => acc
      case (tree,1) => acc:+tree.value
      case(tree, n) => atLevel(tree.left, level-1, acc):::atLevel(tree.right, level-1, acc)
    }
  }

  def levelOrderTraversal(root:TreeNode):List[List[Int]]={
    val depth = TreeNode.depth(root)
    (for{
      level<- Range(1,depth+1)
    }yield atLevel(root,level,List())).toList
  }

  def rightView(treeNode: TreeNode) : List[Int]={
    levelOrderTraversal(treeNode).map(lst => lst.last)
  }

  def largestValues(root: TreeNode): List[Int] = {
    levelOrderTraversal(root).map(lst => lst.max)
  }
}

/*object Solution {
  def createTree(): TreeNode = {
    val tree = TreeNode.insert(TreeNode.insert(TreeNode.insert(TreeNode.insert(null, 8), 5), 13), 2)
    val tree2 = TreeNode.insert(TreeNode.insert(TreeNode.insert(TreeNode.insert(TreeNode.insert(TreeNode.insert(TreeNode.insert(TreeNode.insert(TreeNode.insert(TreeNode.insert(
      TreeNode.insert(
        TreeNode.insert(
          TreeNode.insert(null, 50),
          60), 40), 45),47),46),49),48),30),20),35),32),33)
    val pre = TreeNode.preOrderTreeTraversal(tree, List())
   /* println(pre)
    println(TreeNode.postOrderTreeTraversal(tree,List()))
    println(TreeNode.inOrderTraversal(tree, List()))
    println(TreeNode.depth(tree))*/

    println(TreeNode.diameter(tree2))
    tree
  }

  def main(args: Array[String]): Unit = {
    createTree()
  }

  Solution.main(Array.empty)
}*/


object Solution {
  def depth(treeNode: TreeNode): Int = {
    if (treeNode == null) 0
    else {
      math.max(depth(treeNode.left), depth(treeNode.right)) + 1
    }
  }

  def diameterOfBinaryTree(root: TreeNode): Int = {
    if (root == null) 0
    else {
      val leftDia = diameterOfBinaryTree(root.left)
      val rightDia = diameterOfBinaryTree(root.right)
      val longestPath = depth(root.left) + depth(root.right) + 1
      math.max(math.max(leftDia, rightDia), longestPath)
    }
  }


  def main(args: Array[String]): Unit = {
    val construtedTree = TreeNode.treeFromInorderPreOrder(List(1,2,5,6,10,12,14,15),List(10,5,2,1,6,14,12,15))

    println(TreeNode.inOrderTraversal(construtedTree, List()))
    println(TreeNode.deepestLeavesSum(construtedTree))
    println(TreeNode.depth(construtedTree))
    println(TreeNode.levelOrderTraversal(construtedTree))
    println(TreeNode.atLevel(construtedTree,4,List()))
    println(TreeNode.rightView(construtedTree))
  }


}
