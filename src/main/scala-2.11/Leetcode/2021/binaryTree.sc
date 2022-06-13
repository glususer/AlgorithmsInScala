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

  //https://leetcode.com/problems/find-bottom-left-tree-value/
  def leftMostLeave(root: TreeNode, depth:Int, currentDepth:Int):Int={
   // println(s"currentDepth $currentDepth and value ${root.value}")
    if(root == null) Int.MaxValue
    else  if(currentDepth == depth) root.value
    else{
      val leafFromLeftSubTree = leftMostLeave(root.left, depth, currentDepth+1)
      val leafFromRightSubTree = leftMostLeave(root.right, depth, currentDepth+1)
      if(leafFromLeftSubTree != Int.MaxValue) leafFromLeftSubTree else leafFromRightSubTree
    }
  }
  def findBottomLeftValue(root: TreeNode): Int = {
    val depthOfTree = depth(root)
   // println(s"depth of tree is $depthOfTree")
    leftMostLeave(root, depthOfTree, 1)
  }
}

def  isSubTreeDfs(root: TreeNode, subRoot: TreeNode):Boolean={
  (root, subRoot) match{
    case (null, null) => true
    case (_,null) | (null,_) => false
    case (p,q) =>  p.value == q.value && isSubTreeDfs(p.left, q.left) || isSubTreeDfs(p.right, q.right)
  }
}
def isSubtree(root: TreeNode, subRoot: TreeNode): Boolean = {
  (root, subRoot) match {
    case (_, null) | (null, _) => false
    case (_, _) => isSubTreeDfs(root, subRoot) || isSubTreeDfs(root.left, subRoot) || isSubTreeDfs(root.right, subRoot)
  }
}

val root1 = TreeNode(4,
  TreeNode(4,
    null,
    TreeNode(2,
      null,
        null
    )
  ),
  TreeNode(5, null, TreeNode(4,null,TreeNode(10,null, null))))
val root2 = TreeNode(3,
  TreeNode(4, TreeNode(1, null, null), null),
  TreeNode(5, TreeNode(2, null, null), null))

val subRoot1 = TreeNode(3,
  TreeNode(1, null, null),
  TreeNode(2, null, null))

TreeNode.findBottomLeftValue(root1)

