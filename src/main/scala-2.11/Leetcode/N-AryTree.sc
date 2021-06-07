class Node(var _value: Int) {
     var value: Int = _value
     var children: List[Node] = List()
   }

//https://leetcode.com/problems/n-ary-tree-preorder-traversal/
def preorder(root: Node): List[Int] = {
 def helper(tree:Node, acc:List[Int]):List[Int]={
   tree match{
     case null => acc
     case t => (acc:+ t.value):::t.children.flatMap(child => helper(child, acc))
   }
 }
  helper(root, List.empty)
}

//https://leetcode.com/problems/n-ary-tree-level-order-traversal/
def levelOrder(root: Node): List[List[Int]] = {
  def helper(treeNodes:List[Node],acc:List[List[Int]]):List[List[Int]]={
    treeNodes match{
      case Nil => acc
      case t => val next = t.flatMap(t => t.children)
        helper(next.filter(_ != null),acc:+t.map(_.value))
    }
  }
  if(root == null) List() else helper(List(root), List())
}

val t1 = new Node(1)
val c1 = new Node(3)
c1.children = List(new Node(5), new Node(6))
t1.children = List(c1, new Node(2), new Node(4))

levelOrder(t1)
//https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
def helper(t:Node, acc:Int):Int={
  t match{
    case null => acc
    case _ => val maxDepthChildren = if(t.children.nonEmpty) t.children.map(child => helper(child,acc)).max else acc
      maxDepthChildren+1
  }
}

def maxDepth(root: Node): Int = {
 helper(root,0)
}






