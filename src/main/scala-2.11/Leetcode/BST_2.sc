import Leetcode.TreeNode

def inorder(root:TreeNode, acc:List[Int]) :List[Int]={
  root match{
    case null => acc
    case _ => inorder(root.left,acc):::root.value::acc:::inorder(root.right, acc)
  }
}

def mergeSortedList(l1:List[Int],l2:List[Int]):List[Int]={
  (l1,l2) match{
    case (Nil,l2) => l2
    case (l1,Nil) => l1
    case (x::xs, y::ys) if(x <= y)  => x::mergeSortedList(xs, y::ys)
    case (x::xs, y::ys)  if(x>y) => y::mergeSortedList(x::xs, ys)
  }
}
def getAllElements(root1: TreeNode, root2: TreeNode): List[Int] = {
mergeSortedList(inorder(root1, List.empty), inorder(root1, List.empty))
}
def preOrderTraversal(tree:TreeNode, acc:List[Int]):List[Int] ={
  if(tree == null) acc
  else{
    tree.value::acc:::preOrderTraversal(tree.left, acc):::preOrderTraversal(tree.right, acc)
  }
}
def inorderNode(root:TreeNode, acc:List[TreeNode]) :List[TreeNode]={
  root match{
    case null => acc
    case _ => inorderNode(root.left,acc):::root::acc:::inorderNode(root.right, acc)
  }
}

def createNode(l:List[TreeNode],left:Int, right:Int):TreeNode={
  if(left<right){
    val mid = left+(right-left)/2
    val node = l(mid)
    node.left = createNode(l,left,mid)
    node.right=  createNode(l,mid+1,right)
    node
  }
  else null
}
def balanceBST(root: TreeNode): TreeNode = {
  val nodeList = inorderNode(root, List.empty)
  createNode(nodeList, 0,nodeList.length)
}

val balancedTree = balanceBST(TreeNode(5,null,TreeNode(6,null,TreeNode(7,null,TreeNode(8,null,null)))))

preOrderTraversal(balancedTree, List.empty)
inorder(balancedTree, List.empty)

def findMode(root: TreeNode): Array[Int] = {
 ???
}

//https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
def helperBstToGst(node:TreeNode, rightSum:Int):Int={
  node match{
    case null => rightSum
    case _ => node.value = node.value + helperBstToGst(node.right,rightSum)
      helperBstToGst(node.left, node.value)
  }
}
def bstToGst(root: TreeNode): TreeNode = {
  helperBstToGst(root,0)
  root
}

val greaterTree = bstToGst(TreeNode(5,null,TreeNode(6,null,TreeNode(7,null,TreeNode(8,null,null)))))

inorder(greaterTree,List.empty)

//https://leetcode.com/problems/diameter-of-binary-tree/
def depth(root:TreeNode):Int={
  if(root == null) 0
  else 1+math.max(depth(root.left),depth(root.right))
}
def diameterOfBinaryTreeHelper(root: TreeNode, maxlength:Int): Int = {
 if(root == null) maxlength
  else{
   val leftLength = depth(root.left)
   val rightLength = depth(root.right)
   val currentMax = leftLength+rightLength+1 max maxlength
   math.max(diameterOfBinaryTreeHelper(root.left, currentMax),diameterOfBinaryTreeHelper(root.right, currentMax))
 }
}

def diameterOfBinaryTree(root: TreeNode): Int = {
  diameterOfBinaryTreeHelper(root,1)
}

diameterOfBinaryTree(TreeNode(1,TreeNode(2,TreeNode(4,null,null),TreeNode(5,TreeNode(7,null,null),TreeNode(8,null,null))),
  TreeNode(3,TreeNode(6,null,null),null)))

//https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
def minAndMaxOfSubtree(node:TreeNode, minValue:Int, maxValue:Int, maxDiff:Int):(Int,Int,Int)={
  if(node == null) (minValue, maxValue, maxDiff)
  else{
    val fromLeft = minAndMaxOfSubtree(node.left,minValue, maxValue, maxDiff)
    val fromRight = minAndMaxOfSubtree(node.right, minValue, maxValue, maxDiff)
    val combined = List(fromLeft._1, fromLeft._2,fromRight._1, fromRight._2)
      .filterNot(num => num == Integer.MAX_VALUE || num == Integer.MIN_VALUE)
  //  println(s"fromLeft $fromLeft, fromRight $fromRight node is ${node.value} and maxDiff is $maxDiff")
    if(combined.isEmpty)(node.value,node.value,0)
    else{
      val currentDiff = combined.map(num => math.abs(num-node.value)).max
      (combined.min min node.value, combined.max max node.value, currentDiff max fromLeft._3 max fromRight._3)
    }
  }
}
def maxAncestorDiff(root: TreeNode): Int = {
val (max,min, diff) = minAndMaxOfSubtree(root, Integer.MAX_VALUE, Integer.MIN_VALUE,0)
  println(s"max $max, min $min, diff $diff")
  diff
}


val ancestorTree = TreeNode(8,
  TreeNode(3,TreeNode(1,null,null),TreeNode(6,TreeNode(4,null,null),TreeNode(7,null,null))),
  TreeNode(10,null, TreeNode(14,TreeNode(13,null,null),null)))
val ancestorTree2 = TreeNode(1,null,TreeNode(2,null,TreeNode(0,TreeNode(3,null,null),null)))
maxAncestorDiff(ancestorTree2)

