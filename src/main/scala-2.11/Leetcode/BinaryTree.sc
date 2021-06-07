import Leetcode.TreeNode

/*def rightSideView(root: TreeNode): List[Int] = {
  if(root == null) List.empty
  else {
    val levels = helper(List(root), List.empty).map(lst => lst.last)
    levels
  }
}

def helper(node:List[TreeNode], acc:List[List[Int]]):List[List[Int]]={
  node match{
    case Nil => acc
    case t => val children = t.flatMap(node => List(node.left,node.right).filterNot(_ == null)).filterNot(_ == null)
    helper(children,acc:+node.map(_.value))
  }
}*/

def depth(treeNode: TreeNode): Int = {
  if (treeNode == null) 0
  else {
    math.max(depth(treeNode.left), depth(treeNode.right)) + 1
  }
}

//https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/

def lcaDeepestLeaves(root: TreeNode): TreeNode = {
  val depthOfTree = depth(root)
  println(s"depth is $depthOfTree")
  def helper(node:TreeNode, acc:List[TreeNode], currDepth :Int):List[TreeNode] ={
    node match{
      case null => acc
      case _ =>
        if(currDepth == depthOfTree) acc:+node
      else if(node.right != null && node.left != null) helper(node.left, acc, currDepth+1):::helper(node.right, acc, currDepth+1)
      else if(node.right!= null)helper(node.right, acc, currDepth+1)
      else   helper(node.left, acc, currDepth+1)
    }
  }
  val deepestLeaves = helper(root, List.empty,1)
  println(deepestLeaves.map(_.value))

  if(deepestLeaves.length == 1) deepestLeaves.head
  else{
    def lca(t:TreeNode, p:TreeNode, q:TreeNode):TreeNode={
      if(t == null || p == t || q == t) t
      else{
        val nodesInLeft = lca(t.left, p,q)
        val nodesInRight = lca(t.right,p,q)
        if(nodesInLeft!=null && nodesInRight!= null)t
        else if(nodesInLeft !=null) nodesInLeft
        else nodesInRight
      }
    }
    lca(root, deepestLeaves.head, deepestLeaves.last)
  }
}

val t1 = TreeNode(1,TreeNode(2,TreeNode(4,null,null),TreeNode(5,null,TreeNode(8,null,null))),
  TreeNode(3,TreeNode(6,null,TreeNode(7,null,null)),null))

lcaDeepestLeaves(t1).value

def isCameraNeeded(root:TreeNode, idx:Int, count:Int):(Int,Int)={
  root match{
    case null => (0,count)
    case n if (n.right == null && n.left == null) => (1,count)
    case _ => val left = isCameraNeeded(root.left, idx, count)
    val right = isCameraNeeded(root.right, idx,count)
     // println(s"left is $left and right is $right for ${root.value}")
      val maxCount = left._2 + right._2
      if(left._1 == 1 || right._1 ==1)(2,maxCount+1)
       // this case is interesting - if camera is on either left or right child
      // then return 0 as we dont want the node monitored, if you send 1 then camera would
        // be planted on node
      else if(left._1 == 2 || right._1 == 2)(0,maxCount)
      else (1,maxCount)
  }
}

def minCameraCover(root: TreeNode): Int = {
  if(root == null) 0
  else if(root.right == null && root.left == null) 1
  else {
    val x = isCameraNeeded(root, 0, 0)
    println(s"root is $x")
    // this if is for case where root needs camera but got returned from the above method
    // eg this test case https://leetcode.com/submissions/detail/464488619/
    if(x._1 == 1)x._2+1 else x._2
  }
}

val tree1 = TreeNode(1,TreeNode(2,TreeNode(3,null,null),TreeNode(4,null,null)),null)
val tree2 = TreeNode(1,TreeNode(2,TreeNode(3,TreeNode(4,null,TreeNode(5,null,null)),null),null),null)
val tree3 = TreeNode(1,TreeNode(2,null,null),TreeNode(3,null,TreeNode(4,null,null)))
val tree4 = TreeNode(1,TreeNode(2,TreeNode(3,TreeNode(4,null,null),null),null),null)
minCameraCover(tree4)

//https://leetcode.com/problems/house-robber-iii/
def robHelper(root:TreeNode,isLeftRobbed:Boolean, isRightRobbed:Boolean):(Int,Int)={
   root match {
     case null => (0,0)
     case n if(n.left == null && n.right == null) => (0,n.value)
     case _ => val leftSubtree = robHelper(root.left, false,false)
       ???
   }
}

def rob(root: TreeNode): Int = {
 ???
}

//https://leetcode.com/problems/invert-binary-tree/
def helper(root:TreeNode):TreeNode={
   root match{
     case null => root
     case n if(n.left == null && n.right == null) => n
     case _ => val left = helper(root.left)
     val right = helper(root.right)
      val temp =  left
       root.left = right
       root.right = temp
       root
  }
}
def invertTree(root: TreeNode): TreeNode = {
  root match{
    case null => root
    case n if(n.left == null && n.right == null) => n
    case _ => val left = invertTree(root.left)
      val right = invertTree(root.right)
      val temp =  left
      root.left = right
      root.right = temp
      root
  }
}

//https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
def helper(children:List[TreeNode], maxSum:Int, maxlevel:Int, currentLevel:Int):(Int, Int)={
  children match {
    case  Nil => (maxSum, maxlevel)
    case _ => val next = children.flatMap(child => List(child.left, child.right)).filterNot(_ == null)
      if(next.isEmpty) (maxSum, maxlevel)
      else {
        val childValueSum = next.map(_.value).sum
        println(s"childValueSum is $childValueSum, maxLevel ${maxlevel} and currentLevel ${currentLevel} maxSum is $maxSum")
        if (childValueSum > maxSum) helper(next, childValueSum, currentLevel+ 1, currentLevel + 1)
        else helper(next, maxSum, maxlevel, currentLevel + 1)
      }
  }
}

def maxLevelSum(root: TreeNode): Int = {
  helper(List(root), root.value, 1,1)._2
}

val tree5 = TreeNode(1, TreeNode(7, TreeNode(7,null,null),TreeNode(-8,null,null)), TreeNode(0,null,null))
val tree6 = TreeNode(1, null,null)
val tree7  = TreeNode(1, TreeNode(1,TreeNode(7, null,null), TreeNode(-8,null,null)),
  TreeNode(0,TreeNode(-7,null,null),TreeNode(9,null,null)))
maxLevelSum(tree7)

//https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/

def helperSumEvenGranParent(node:TreeNode, parent:Int, grandParent:Int):Int={
  node match {
    case null => 0
    case _ => {if(grandParent %2 ==0) node.value else 0}+helperSumEvenGranParent(node.left, node.value,parent) +
      helperSumEvenGranParent(node.right, node.value,parent)
  }
}
def sumEvenGrandparent(root: TreeNode): Int = {
  helperSumEvenGranParent(root, 1,1)
}

val grandParentTree = TreeNode(6,TreeNode(7, TreeNode(2, TreeNode(9,null,null), null), TreeNode(7, TreeNode(1,null,null), TreeNode(4, null,null))),
  TreeNode(8, TreeNode(1, null,null), TreeNode(3,null,TreeNode(5, null,null))))

sumEvenGrandparent(grandParentTree)


