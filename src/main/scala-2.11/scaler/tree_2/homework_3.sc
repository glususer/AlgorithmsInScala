//Given a binary tree of integers. Find the difference between the sum of nodes at odd level and sum of nodes at even level.
//
//NOTE: Consider the level of root node as 1.


import Leetcode.TreeNode

def levelOrder(level:List[TreeNode], even:Int, odd:Int, currentLevel:Int):Int={
  if(level.isEmpty) odd - even
  else{
    val children = level.flatMap(node => List(node.left, node.right)).filter(_ != null)
    val(updatedEven, updatedOdd) = {
      if(currentLevel%2==0)(even+level.map(_.value).sum,odd)
      else (even, odd+level.map(_.value).sum)
    }
    levelOrder(children, updatedEven, updatedOdd,currentLevel+1)
  }
}
def solve(A: TreeNode):Int = {
  levelOrder(List(A), 0,0,1)
}