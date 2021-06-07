import Leetcode.TreeNode

//https://leetcode.com/problems/house-robber/
//2,7,9,3,1,2,5,10
def rob(nums: Array[Int]): Int = {

  if(nums.length <= 2) nums.max
  else{
    val dpArr = Array.fill(nums.length)(0)
    dpArr(0) = nums(0)
    dpArr(1) = nums(1)
    nums.zipWithIndex.drop(2).foldLeft(nums(0),nums(1)){case (acc, ele) =>
      val currentAmount = acc._1+ele._1
      dpArr(ele._2) =  currentAmount
      (dpArr.take(ele._2).max,ele._1)
    }
    println(s"dpArr is ${dpArr.toList}")
    dpArr.max
  }
}

rob(Array(2,7,9,3,1))

//https://leetcode.com/problems/house-robber-iii/

def helperRob(root:TreeNode, acc:(Int,Int)):(Int,Int)={
  root match{
    case null => acc
    case n if(n.left == null && n.right == null) => (n.value,0)
    case _ => val leftAmount = helperRob(root.left, acc)
    val rightAmount = helperRob(root.right, acc)
     val x  =  (root.value+leftAmount._2+rightAmount._2,(leftAmount._1 max leftAmount._2)+(rightAmount._1 max rightAmount._2))
      println(s"amount for ${root.value} is ${x}, left is $leftAmount and right is $rightAmount")
      x
  }
}
def rob(root: TreeNode): Int = {
  val rootAmount = helperRob(root, (0,0))
  println(rootAmount)
  rootAmount._1 max rootAmount._2
}

val tree1 = TreeNode(3,TreeNode(2,null,TreeNode(3, null,null)),TreeNode(3,null,TreeNode(1, null,null)))
val tree2 = TreeNode(4, TreeNode(1, TreeNode(2,TreeNode(3, null,null),null),null),null)
val tree3 = TreeNode(3, TreeNode(4, TreeNode(1, null,null), TreeNode(3, null,null)), TreeNode(5,TreeNode(1, null,null),null))
rob(tree3)