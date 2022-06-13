import Leetcode.TreeNode
//Given a binary tree A. Check whether it is possible to partition the tree to two trees which have equal sum of values after removing exactly one edge on the original tree.

def sumOfNodes(A:TreeNode, acc:Int):Int={
  if(A == null)acc
  else if(A.left == null && A.right == null) A.value
  else{
    val left = sumOfNodes(A.left, acc)
    val right = sumOfNodes(A.right, acc)
    left+right+A.value
  }
}

def traverse(A:TreeNode, acc:Int, totalSum: Int, exists:Boolean):(Int,Boolean)={
  if(exists) (0,exists)
  else {
    if (A == null) (acc, exists)
    else if (A.left == null && A.right == null) (A.value, A.value * 2 == totalSum)
    else {
      val (left,leftExists) = traverse(A.left, acc, totalSum,exists)
      val (right,rightExists) = traverse(A.right, acc,totalSum, exists)
      val currentSum = left + right + A.value
    //  println(s"currentSum $currentSum leftExists $leftExists rightExists $rightExists")
      (currentSum, currentSum * 2 == totalSum || leftExists || rightExists)
    }
  }
}

def solve(A: TreeNode): Int  = {
  val totalsum = sumOfNodes(A,0)
 // println(s"totalSum $totalsum")
  val(_,isPossible) = traverse(A,0,totalsum,false)
  if(isPossible) 1 else 0
}

val tree = TreeNode(1,
  TreeNode(2,null,null),
  TreeNode(3,null,null))
solve(tree)