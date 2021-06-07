import Leetcode.TreeNode


def helper(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode = {
  root match {
    case node if (root.value > p.value && root.value > q.value) => helper(root.left, p, q)
    case node if (root.value < p.value && root.value < q.value) => helper(root.right, p, q)
    case node if (root.value > p.value && root.value <= q.value) => root
    case _ => root

  }
}

def lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode = {
  if(p.value < q.value) helper(root, p,q)
  else  helper(root,q,p)
}
def levelOrderTraversal(nodes:List[TreeNode], acc:List[List[Int]]):List[List[Int]]={
  if(nodes.isEmpty) acc
  else{
    val next = nodes.flatMap(node => List(node.left,node.right).filter(_ != null))
    levelOrderTraversal(next, acc:+nodes.map(_.value))
  }
}
//https://leetcode.com/problems/cousins-in-binary-tree/
def isSibling(node:TreeNode, x:Int,y:Int):Boolean={
  if(node == null) false
  else if(node.left!= null && node.right != null && (node.left.value == x && node.right.value == y || node.left.value == y && node.right.value == x))true
  else{
    isSibling(node.left,x,y) || isSibling(node.right,x,y)
  }
}
def isCousins(root: TreeNode, x: Int, y: Int): Boolean = {
  val levels = levelOrderTraversal(List(root), List.empty)
  if(levels.exists(level => level.contains(x) && level.contains(y))){
    !isSibling(root,x,y)
  }else false
}
val tree = TreeNode(1,TreeNode(2,TreeNode(4,null,null),TreeNode(5,TreeNode(7,null,null),TreeNode(8,null,null))),
  TreeNode(3,TreeNode(6,null,null),null))
isCousins(tree,3,4)
