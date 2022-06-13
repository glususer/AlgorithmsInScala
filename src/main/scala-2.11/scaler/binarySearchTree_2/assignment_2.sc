import Leetcode.TreeNode

//Find the lowest common ancestor in an unordered binary tree A, given two values, B and C, in the tree.
//
//Lowest common ancestor: the lowest common ancestor (LCA) of two nodes and w in a tree or directed acyclic graph (DAG)
// is the lowest (i.e., deepest) node that has both v and w as descendants.

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

def lca(A: TreeNode, B: Int, C: Int): Int  = {
  if(A == null) -1
    else if(A.left == null && A.right == null){
    if(B == C &&  B == A.value) A.value
    else -1
  }
  else {
    val p = searchForNode(A, B)
    lazy val q = searchForNode(A, C)
    if(p == null || q == null) -1
    else {
      println(s"p ${p.value} q ${q.value}")
      val ancestor = lcaHelper(A, p, q)
      ancestor.value
    }
  }
}
lca(TreeNode(3,TreeNode(1,null,null),null),16,29)

