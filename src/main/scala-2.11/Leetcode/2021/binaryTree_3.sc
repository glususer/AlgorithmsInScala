import Leetcode.TreeNode
import Leetcode.TreeNode.atLevel

def levelOrderTraversal(root:TreeNode):List[List[Int]]={
  val depth = TreeNode.depth(root)
  (for{
    level<- Range(1,depth+1)
  }yield atLevel(root,level,List())).toList
}


//https://leetcode.com/problems/add-one-row-to-tree/
def addRowHelper(nodes:List[TreeNode], depth:Int, `val` :Int): List[TreeNode]={
  if(depth-1 ==1){
    val children = nodes.flatMap{node =>
      val leftChild = node.left
      val rightChild = node.right
     val newLeftChild =  {
         node.left = TreeNode(`val`,if(leftChild != null ) leftChild else null ,null)
         TreeNode(`val`,if(leftChild != null ) leftChild else null,null)
       }
     val newRightChild = {
         node.right = TreeNode(`val`, null,if(rightChild != null) rightChild else null)
       TreeNode(`val`, null, if(rightChild != null) rightChild else null)
     }
    // println(s"newLeftChild ${if(newLeftChild != null) newLeftChild.value else null}, newRightChild ${if(newRightChild != null) newRightChild.value else null}, leftChild ${if(leftChild!= null) leftChild.value else null} rightChild ${if(rightChild!= null) rightChild.value else null}")
      List(newLeftChild, newRightChild)
    }
    children
  }else{
    val children = nodes.flatMap(node => List(node.left, node.right).filter(_ != null))
    addRowHelper(children, depth-1, `val`)
  }
}

def addOneRow(root: TreeNode, `val`: Int, depth: Int): TreeNode = {
  if(depth == 1) {
    val newRoot = TreeNode(`val`, null, null)
    newRoot.left = root
    newRoot
  }
  else {
    addRowHelper(List(root),depth, `val`)
    root
  }
}

val rowTree =  TreeNode(2,TreeNode(4,TreeNode(5, null, null),TreeNode(6, null, null)),
  TreeNode(7, TreeNode(8, null, null), TreeNode(9, null, null)))

levelOrderTraversal(rowTree)
println(s"-----------")
levelOrderTraversal(addOneRow(rowTree,1,1)).foreach(level => println(level))