import Leetcode.TreeNode
def inOrderTraversal(tree: TreeNode, acc: List[Int]): List[Int] = {
  if (tree == null) acc
  else {
    inOrderTraversal(tree.left, acc) ::: (tree.value :: acc) ::: inOrderTraversal(tree.right, acc)
  }
}

def preOrderTraversal(tree:TreeNode, acc:List[Int]):List[Int] ={
  if(tree == null) acc
  else{
    tree.value::acc:::preOrderTraversal(tree.left, acc):::preOrderTraversal(tree.right, acc)
  }
}

def bstFromPreorder(preorder: Array[Int]): TreeNode = {
  preorder.length match{
    case 0=> null
    case 1 => TreeNode(preorder.head, null,null)
    case _ =>  val node = binarypreorder.head
      val (left, right) = preorder.tail.partition(_ < node)
      TreeNode(node, bstFromPreorder(left), bstFromPreorder(right))
  }
}

//println(TreeNode.inOrderTraversal(bstFromPreorder(Array(1,2,3)), List()))

sealed trait Tree
case object End extends Tree
case class Node(value: Int, left:Tree, right:Tree) extends Tree

def bstFromPreorder2(preorder:Array[Int]):Tree={

  preorder.length match{
    case 0 => End
    case 1 => Node(preorder.head,End,End)
    case _ => val node = preorder.head
    val (left,right) = preorder.tail.partition(_ < node)
    Node(node,bstFromPreorder2(left),bstFromPreorder2(right))
  }
}

def inOrderTraversal2(tree:Tree, acc:List[Int]):List[Int]={
  tree match{
    case End => acc
    case (tree:Node) => inOrderTraversal2(tree.left,acc):::(tree.value::acc):::inOrderTraversal2(tree.right, acc)
  }
}

//val x = bstFromPreorder2(Array(4,12,3,6,8,0,9))

//println(inOrderTraversal2(x,List()))

def helper(tree:TreeNode, acc:List[Int]):List[Int] = {
  tree match{
    case null => acc
    case _ => val rightResult = if(tree.left == null && tree.right == null) tree.value::acc else helper(tree.right,acc)
      helper(tree.left, tree.value+rightResult.lastOption.getOrElse(0)::rightResult)
  }
}

def bstToGst(tree: TreeNode): TreeNode = {
  def helper(tree:TreeNode, acc:List[Int]):List[Int]={
    tree match{
      case null => acc
      case _ => val righTreeSum = helper(tree.right,acc)
        helper(tree.left, (righTreeSum.headOption.getOrElse(0)+ tree.value)+: righTreeSum)
    }
  }

  def isLeaf(node: TreeNode):Boolean ={
    node.right == null && node.left == null
  }

  def insert(tree:TreeNode, item:Int):TreeNode={
    tree match {
      case null => TreeNode(item, null, null)
      case node if (isLeaf(node)) =>
        if (tree.value > item) tree.left = TreeNode(item, null, null)
        else tree.right = TreeNode(item, null, null)
        tree
      case _ =>
        if (tree.value < item) tree.right = insert(tree.right, item)
        else tree.left = insert(tree.left, item)
        tree
    }
  }

  @scala.annotation.tailrec
  def createTree(nodes: List[Int], newtree:TreeNode):TreeNode ={
    nodes match {
      case Nil => newtree
      case x::xs =>  createTree(xs,insert(newtree,x))
    }
  }
  val treeList = helper(tree, List())
  createTree(treeList, null )
}

//https://leetcode.com/problems/flip-equivalent-binary-trees/
def flipEquiv(root1: TreeNode, root2: TreeNode): Boolean = {
  (root1,root2) match{
    case (null,null) => true
    case (_,null) => false
    case (null,_) => false
    case (t1,t2) if (t1.value == t2.value)=> (flipEquiv(t1.right,t2.right) && flipEquiv(t1.left,t2.left))|| (flipEquiv(t1.right,t2.left) && flipEquiv(t1.left,t2.right))
    case (t1,t2) => false
  }
}

val t1 = TreeNode(1,TreeNode(2,TreeNode(4,null,null),TreeNode(5,TreeNode(7,null,null),TreeNode(8,null,null))),
  TreeNode(3,TreeNode(6,null,null),null))

val t2 = TreeNode(1,TreeNode(3,null,TreeNode(6,null,null)),
  TreeNode(2,TreeNode(4,null,null),TreeNode(5,TreeNode(8,null,null),TreeNode(7,null,null))))

flipEquiv(t1,t2)

  def levelOrderTraversal(root:TreeNode):List[List[Int]]={
    def helper(t:List[TreeNode],acc:List[List[Int]]):List[List[Int]]={
      t match{
        case y if(t.isEmpty) => acc
        case x => val next = t.flatMap(node => List(node.left,node.right).filter(_ != null))
        helper(next.filter(_ != null), acc:+t.map(_.value))
      }
    }
    if(root == null) List() else helper(List(root), List())
}

def rob(root: TreeNode): Int = {
  ???
}

//println(levelOrderTraversal(t2))

//flipMatchVoyage(TreeNode(1,null,TreeNode(2,null,null)),Array(1,2))


val validTree = new TreeNode(3, null,new TreeNode(30,new TreeNode(10,null, new TreeNode(15,null, new TreeNode(45, null,null))),null))


//https://leetcode.com/problems/kth-smallest-element-in-a-bst/
def kthSmallest(root: TreeNode, k: Int): Int = {
  val m = inOrderTraversal(root, List.empty)
  println(m)
    m(k-1)
}

kthSmallest(t1,2)

def sumOfNodes(tree:TreeNode, sum:Int):Int={
  tree match{
    case null => sum
    case t => if(t.left != null && t.right != null) t.value+sumOfNodes(t.left,sum)+sumOfNodes(t.right,sum)
    else if(t.left != null) t.value+sumOfNodes(t.left,sum)
    else if(t.right != null) t.value+sumOfNodes(t.right,sum)
    else t.value
  }
}

//https://leetcode.com/problems/validate-binary-search-tree/
def isSubTreeValid(node: TreeNode,rootValue:Int)(implicit cmp : (Int,Int) => Boolean):Boolean ={
  node  match{
    case  null => true
    case n => println(n.value, rootValue)
      if(cmp(n.value,rootValue)) {
        isSubTreeValid(node.left,rootValue) && isSubTreeValid(node.right,rootValue)
      } else false
  }
}

def isValidBST(root: TreeNode): Boolean = {
  root match{
    case null => true
    case leaf if(leaf.left == null && leaf.right == null) => true
    case t =>
      isSubTreeValid(t.left,t.value)((x,y)=> x < y) &&
        isSubTreeValid(t.right,t.value)((x,y)=> x > y) &&
        isValidBST(t.left) && isValidBST(t.right)
  }
}

val t11 = TreeNode(3,TreeNode(1, null,null),TreeNode(2, null,null))
//println(s" isValidBST ${isValidBST(t11)}")


def maxSumBST(root: TreeNode): Int = {
  var maxSum = 0
  def sumOfValidBST(tree:TreeNode, min:Int, max :Int, sum:Int):Array[Int]={
    tree match{
      case null => Array(Integer.MAX_VALUE, Integer.MIN_VALUE, 0)
      case t => val left = sumOfValidBST(t.left, min,max,sum)
        val right = sumOfValidBST(t.right, min,max,sum)
        if(!(left.length == 3 && right.length ==3 && t.value>left(1) && t.value < right(0))) Array.empty
        else{
          val currentSUm = t.value+left(1)+right(1)
          maxSum = math.max(currentSUm, sum)
          Array(math.min(left(0),t.value), math.max(right(1),t.value),currentSUm)
        }

    }
  }
  sumOfValidBST(root,Integer.MAX_VALUE, Integer.MIN_VALUE,0)
  maxSum
}
val maxSumBSTTree = TreeNode(4,t11,null)
maxSumBST(TreeNode(-4, TreeNode(-2, null, null),TreeNode(-5, null,null)))

val l = List((5,10,0),(5,2,2),(8,2,0),(9,0,0),(5,2,1),(5,2,3),(5,2,4))

l.min
