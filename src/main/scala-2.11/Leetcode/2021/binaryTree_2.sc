import Leetcode.TreeNode

def pathSumHelper2(root: TreeNode, targetSum: Int, count: Int=0, parentPathSum:Int=0):Int={
  if(root == null) count
  else{
   val lCount = pathSumHelper2(root.left, targetSum, count, parentPathSum+root.value)
    val rCount = pathSumHelper2(root.right, targetSum, count, parentPathSum+root.value)
    lCount+rCount+(if(parentPathSum+root.value == targetSum) 1 else 0)
  }
}

def traversal(root:TreeNode, targetSum:Int, count:Int=0):Int={
  if(root == null) count
  else{
    pathSumHelper2(root, targetSum)+traversal(root.left, targetSum, count)+traversal(root.right, targetSum, count)
  }
}

def pathSum(root: TreeNode, targetSum: Int): Int = {
//  pathSumHelper2(root, targetSum)+pathSumHelper2(root.left, targetSum)
  traversal(root, targetSum)
}

val pathSumTree = TreeNode(10,
  TreeNode(5,
    TreeNode(3,
      TreeNode(3,null,null),
      TreeNode(-2,null,null)),
    TreeNode(2,null,TreeNode(1,null,null))),
  TreeNode(-3,null,TreeNode(11,null,null)))

pathSum(pathSumTree, 8)

//https://leetcode.com/problems/longest-univalue-path/




def longestUnivaluePath(root: TreeNode): Int = {
  var topLevelmax = 0

  def longestUnivaluePathHelper(root:TreeNode, globalMaxFreq:Int=0):Int={
    if(root == null) 0
    else{
      val fromLeft = longestUnivaluePathHelper(root.left, globalMaxFreq)
      val fromRight = longestUnivaluePathHelper(root.right, globalMaxFreq)
      val (freqAtCurrentNode, toBePropagated) = {
        if(root.left!= null && root.right!= null && root.left.value == root.value&& root.right.value == root.value) (fromLeft+fromRight+1, (fromLeft max fromRight) +1)
        else if(root.left!= null && root.right!= null && root.left.value != root.value && root.right.value != root.value) (fromLeft max fromRight, 1)
        else if(root.left != null && fromLeft > fromRight && root.left.value == root.value) (fromLeft+1, fromLeft+1)
        else if(root.right != null && fromRight > fromLeft && root.right.value == root.value) (fromRight+1, fromRight+1)
        else if(fromLeft == 0 && fromRight == 0) (1,1)
        else (fromLeft max fromRight, if(root.value == root.left.value) fromLeft+1 else if(root.value == root.right.value)fromRight+1 else fromLeft max fromRight)
      }
      topLevelmax = topLevelmax max freqAtCurrentNode
      println(s"freqAtCurrentNode $freqAtCurrentNode topLevelmax $topLevelmax fromLeft $fromLeft fromRight $fromRight")
      toBePropagated
    }
  }
  longestUnivaluePathHelper(root)
  topLevelmax-1
}

val univalueTree = TreeNode(5,
  TreeNode(5,TreeNode(5,TreeNode(5, null, null),TreeNode(5,null,TreeNode(5,null,TreeNode(5, null,null)))),null),
  null)

val univalTree2 = TreeNode(1, TreeNode(4, TreeNode(4, null, null), TreeNode(4,null,null)), TreeNode(5, TreeNode(5,null,null),null))

longestUnivaluePath(univalTree2)