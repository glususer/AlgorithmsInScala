import Leetcode.TreeNode

//Given a binary search tree.
//Return the distance between two nodes with given two keys B and C. It may be assumed that both keys exist in BST.
//
//NOTE: Distance between two nodes is number of edges between them.
def lcaHelper(root:TreeNode, p:TreeNode, q:TreeNode):TreeNode={
  if(root == null || p == root || q == root) root
  else{
    val fromLeft = lcaHelper(root.left, p,q)
    lazy val fromRight = lcaHelper(root.right, p,q)

    if(fromLeft != null && fromRight != null) root
    else if(fromLeft != null) fromLeft
    else fromRight
  }
}

def searchForNode(node: TreeNode, data:Int):TreeNode = {
  if(node == null) null
  else if(node.value == data) node
  else {
    val fromLeft = searchForNode(node.left, data)
    lazy val fromRight = searchForNode(node.right, data)
    if(fromLeft != null) fromLeft
    else fromRight
  }
}

def distanceFromOneNodeToAnother(first:TreeNode, second:TreeNode, distance:Int=0):Int={
if(second.value < first.value)distanceFromOneNodeToAnother(first.left, second, distance+1)
else if(second.value > first.value)distanceFromOneNodeToAnother(first.right, second, distance+1)
else distance
}

def solve(A: TreeNode, B: Int, C: Int): Int  = {
  val p = searchForNode(A,B)
  val q = searchForNode(A,C)
  val ancestor = lcaHelper(A,p,q)
  distanceFromOneNodeToAnother(ancestor,p)+distanceFromOneNodeToAnother(ancestor,q)
}