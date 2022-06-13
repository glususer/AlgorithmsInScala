import Leetcode.TreeNode

//Given a binary tree, return the preorder traversal of its nodes' values.
//
//NOTE: Using recursion is not allowed.

// preorder -> Node, left, right
def preorderTraversal(A: TreeNode): Array[Int]  = {
  import scala.collection.mutable

  val lb = mutable.ListBuffer[Int]()
  val stack = mutable.Stack[TreeNode]()

  stack.push(A)

  while(stack.nonEmpty){
    val current = stack.pop()
    lb+=current.value
    if(current.right!=null) stack.push(current.right)
    if(current.left !=null) stack.push(current.left)
  }
  lb.toArray
}