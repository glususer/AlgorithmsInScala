import Leetcode.TreeNode
//Given a binary tree, return the Postorder traversal of its nodes' values.
//
//NOTE: Using recursion is not allowed.
// left, right, node
def postorderTraversal(A: TreeNode): Array[Int]  = {
  import scala.collection.mutable

  val lb = mutable.ListBuffer[Int]()
  val stack = mutable.Stack[TreeNode]()

  stack.push(A)
  while(stack.nonEmpty){
    val current = stack.pop()
    lb+=current.value
    if(current.left !=null) stack.push(current.left)
    if(current.right!=null) stack.push(current.right)
  }
  lb.reverse.toArray
}

val tree = TreeNode(1,
  TreeNode(6,
    TreeNode(4,
      TreeNode(5,null,null),
      TreeNode(10,null,null)),
    null),
  TreeNode(2,TreeNode(3,null,null),TreeNode(8,null,null)))
//5 4 6 3 8 2 1
postorderTraversal(tree)