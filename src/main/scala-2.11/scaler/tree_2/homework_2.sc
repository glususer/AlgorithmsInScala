import Leetcode.TreeNode
//Given a binary tree, return the zigzag level order traversal of its nodes values.
// (ie, from left to right, then right to left for the next level and alternate between).

def levelOrder(level:List[TreeNode], acc:List[List[Int]], currentLevel:Int):List[List[Int]]={
  if(level.isEmpty) acc
  else{
    val children = level.flatMap(node => List(node.left, node.right)).filter(_ != null)
    levelOrder(children, acc:+(if(currentLevel%2==0)level.map(_.value) else level.map(_.value).reverse),currentLevel+1)
  }
}
def zigzagLevelOrder(A: TreeNode): Array[Array[Int]]  = {
  levelOrder(List(A), List.empty,0).map(_.toArray).toArray
}

