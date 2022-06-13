import scala.collection.mutable

//Given a binary tree, return the inorder traversal of its nodes' values.
//
//NOTE: Using recursion is not allowed.
class TreeNode(val xc: Int) {
  var value: Int = xc
  var left: TreeNode = null
  var right: TreeNode = null
}

object TreeNode {
  def apply(_x: Int, _left: TreeNode, _right: TreeNode): TreeNode = new TreeNode(_x)
}



def inorderTraversal(A: TreeNode): Array[Int]  = {
 val stack =  mutable.Stack[TreeNode]()
  val lb = mutable.ListBuffer[Int]()
  var current = A

  while(stack.nonEmpty ||current !=null){
  //  println(s"lb $lb current ${if(current !=null) current.value else "NULL"} stack ${stack.map(_.value)}")
    if(current!=null){
      stack.push(current)
      current=current.left
    }else{
      current = stack.pop()
      lb+=current.value
      current = current.right
    }
  }

  lb.toArray
}

val tree = TreeNode(1,
  TreeNode(2,
    TreeNode(4,null,null),
    TreeNode(5,
      TreeNode(7,null,null),
      null)),
  TreeNode(3,
    null,
    TreeNode(6,null,null)))

inorderTraversal(tree)
