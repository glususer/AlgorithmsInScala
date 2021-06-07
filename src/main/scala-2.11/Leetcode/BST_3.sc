import Leetcode.TreeNode

def inorder(root:TreeNode, acc:List[Int]) :List[Int]={
  root match{
    case null => acc
    case _ => inorder(root.left,acc):::root.value::acc:::inorder(root.right, acc)
  }
}
//https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
case class NodeWithDepth(value:Int, depth:Int)

def helperRecoverFromPreorder(tree:List[NodeWithDepth]):TreeNode ={
  tree match{
    case Nil => null
    case x::Nil => TreeNode(x.value, null,null)
    case x::xs => val childDepth = xs.head.depth
      val splitIdx = xs.map(_.depth).lastIndexOf(childDepth)
      val (left,right) ={ if(splitIdx == 0) (xs,List.empty) else xs.splitAt(splitIdx)}
      TreeNode(x.value, helperRecoverFromPreorder(left),helperRecoverFromPreorder(right))
  }
}
def recoverFromPreorder(S: String): TreeNode = {
  // this is to convert String to List of Nodes with Depth and value
  val tree = (S+"-").foldLeft(List[NodeWithDepth](),0,"",' '){
    case (acc,ele) =>
      if(ele.isDigit) (acc._1,acc._2,acc._3+ele,ele)
      else{
        if(acc._4 == '-') (acc._1,acc._2+1,acc._3,ele)
        else(acc._1:+NodeWithDepth(acc._3.toInt,acc._2),1,"",ele)
      }
    }._1
  helperRecoverFromPreorder(tree)
}
val s = "1-401--349---90--88"

val tree = recoverFromPreorder(s)

inorder(tree, List.empty)
def rangeSumHelper(root: TreeNode, low: Int, high: Int, acc: Int):Int={
  root match{
    case null => acc
    case r if r.right == null && r.left == null  =>  if(r.value >= low && r.value <= high) acc+r.value else acc
    case node => val sumFromLeftSubtree = rangeSumHelper(node.left, low, high, acc)
    val sumFromRightSubtree = rangeSumHelper(node.right, low, high, acc)
     if(node.value >= low && node.value <= high) sumFromLeftSubtree+sumFromRightSubtree+node.value else sumFromLeftSubtree+sumFromRightSubtree
  }
}

def rangeSumBST(root: TreeNode, low: Int, high: Int): Int = {
  rangeSumHelper(root, low, high, 0)
}

val ancestorTree = TreeNode(8,
  TreeNode(3,TreeNode(1,null,null),TreeNode(6,TreeNode(4,null,null),TreeNode(7,null,null))),
  TreeNode(10,null, TreeNode(14,TreeNode(13,null,null),null)))
val ancestorTree2 = TreeNode(1,null,TreeNode(2,null,TreeNode(0,TreeNode(3,null,null),null)))

rangeSumBST(ancestorTree, 10,15)
