import Leetcode.TreeNode

def rightSideViewHelper(root:TreeNode, acc:List[Int] = List.empty):List[Int]={
  if (root == null) acc
  else {
    val fromRight = rightSideViewHelper(root.right, acc:+root.value)
    val fromLeft = rightSideViewHelper(root.left, acc:+root.value)
//    println(s"fromRight $fromRight, fromLeft $fromLeft root ${root.value}")
    if (fromRight.size >= fromLeft.size) fromRight else fromRight++(fromLeft.drop(fromRight.length))
  }
}

def rightSideView(root: TreeNode): List[Int] = {
  rightSideViewHelper(root)
}

val root1 = TreeNode(4,
  TreeNode(4,
    null,
    TreeNode(2,
      TreeNode(10, null, null),
      null
    )
  ),
  TreeNode(5, null,null))

val root2 = TreeNode(1, TreeNode(2, TreeNode(3,TreeNode(4, null, null),null), null),null)
//rightSideView(root1)


def pathSumHelper(root: TreeNode, targetSum:Int, currentSum:Int, acc:List[List[Int]]= List.empty, currentSumList:List[Int]= List.empty):List[List[Int]]={
  if(root == null) acc
  else if(root.value+currentSum == targetSum && root.left == null && root.right == null) {
    acc:+(currentSumList:+root.value)
  }
  else{
    val sumFromLeft= pathSumHelper(root.left, targetSum, currentSum+root.value, acc, currentSumList:+root.value)
    val sumFromRight = pathSumHelper(root.right, targetSum, currentSum+root.value, acc, currentSumList:+root.value)

    sumFromLeft++sumFromRight

  }
}

def pathSum(root: TreeNode, targetSum: Int): List[List[Int]] = {
pathSumHelper(root, targetSum,0)
}

val sumRoot = TreeNode(5,
  TreeNode(4,
    TreeNode(11,
      TreeNode(7, null,null),
      TreeNode(2, null,null)),null),
  TreeNode(8,
    TreeNode(13, null,null),
    TreeNode(4, TreeNode(5, null,null),
      TreeNode(1, null,null))))

val testCase1 = TreeNode(1,TreeNode(2, null,null),null )

//pathSum(sumRoot, 22)

def zigzagLevelOrderHelper(currentNodes:List[TreeNode], acc:List[List[Int]] =  List.empty, level:Int=1):List[List[Int]]={
  if (!currentNodes.exists(_ != null))  acc
  else{
    val neighbours = currentNodes.flatMap(node => List( node.left , node.right)).filter( _ != null)
    zigzagLevelOrderHelper(neighbours, if(level %2 ==0) acc:+(currentNodes.map(_.value).reverse) else acc:+currentNodes.map(_.value), level+1)
  }
}

def zigzagLevelOrder(root: TreeNode): List[List[Int]] = {
zigzagLevelOrderHelper(List(root))
}
//zigzagLevelOrder(sumRoot)
//zigzagLevelOrder(null)

def getNodes(root:TreeNode, count:Int = 0):Int={
  if(root== null) count
  else{
    val fromLeft = getNodes(root.left, count)
    val fromRight = getNodes(root.right, count)
    fromLeft+fromRight+1
  }
}

def kthSmallestHelper(root: TreeNode, k:Int, lessThan: Int):Int={
  print(s" root ${root.value}, k $k, lessThan $lessThan")
  if(k-1 == 0  &&  lessThan == 0) root.value
  else{
    val valuesLessThen = getNodes(root.left.left)
    println(s" valuesLessThen $valuesLessThen")
    if(valuesLessThen == 0) root.left.value
    else  if(valuesLessThen >= k ) kthSmallestHelper(root.left, k, valuesLessThen)
      else kthSmallestHelper(root.right, k-1-valuesLessThen, valuesLessThen)
  }
}

def kthSmallest(root: TreeNode, k: Int): Int = {
  val lessthan = getNodes(root.left)
  if(lessthan < k-1) kthSmallestHelper(root.right, k, lessthan+1)
  else if(lessthan == k-1)root.value
  else kthSmallestHelper(root, k, getNodes(root.left))
}

val tree = TreeNode(10,
  TreeNode(8,
    TreeNode(5,
      TreeNode(3,
        TreeNode(2, null, null),
        TreeNode(4, null, null)),
      TreeNode(6,
        null,
        TreeNode(7, null, null))),
    TreeNode(9,null,null)),
  TreeNode(20,
    TreeNode(15, null, null),
    TreeNode(25,
      TreeNode(22,null,null),
      TreeNode(30, null, null))))

//kthSmallest(tree, 1)
//kthSmallest(tree, 2)
/*kthSmallest(tree, 3)
kthSmallest(tree, 4)
kthSmallest(tree, 5)
kthSmallest(tree, 6)
kthSmallest(tree, 7)
kthSmallest(tree, 8)
kthSmallest(tree, 9)
kthSmallest(tree, 10)
kthSmallest(tree, 11)
kthSmallest(tree, 12)
kthSmallest(tree, 13)
kthSmallest(tree, 14)*/

def lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode = {
  if(root == null || root == p || root == q) root
  else{
    val fromLeft = lowestCommonAncestor(root.left, p, q)
    lazy val fromRight = lowestCommonAncestor(root.right, p, q)
    if(fromLeft != null && fromRight != null) root
    else if (fromLeft != null) fromLeft
    else fromRight
  }
}

def minAndMaxOfSubtree(node:TreeNode, minValue:Int, maxValue:Int, maxDiff:Int):(Int,Int,Int)={
  if(node == null) (minValue, maxValue, maxDiff)
  else{
    val (lMin, lMax, lDiff) = minAndMaxOfSubtree(node.left,minValue, maxValue, maxDiff)
    val (rMin, rMax, rDiff) = minAndMaxOfSubtree(node.right, minValue, maxValue, maxDiff)
    val combined = List(lMin, lMax,rMin, rMax)
      .filterNot(num => num == Integer.MAX_VALUE || num == Integer.MIN_VALUE)

    if(combined.isEmpty)(node.value,node.value,0)
    else{
      val currentDiff = combined.map(num => math.abs(num-node.value)).max
      (combined.min min node.value, combined.max max node.value, currentDiff max lDiff max rDiff)
    }
  }
}

def maxAncestorDiff(root: TreeNode): Int = {
  val (l,r, maxDiff) = minAndMaxOfSubtree(root, Int.MaxValue,Int.MinValue, 0)
  maxDiff
}

val maxDiffRoot = TreeNode(8,
  TreeNode(3,
    TreeNode(1,null,null),
    TreeNode(6,
      TreeNode(4, null, null),
      TreeNode(7, null, null))),
  TreeNode(10,null,TreeNode(14,TreeNode(13,null,null),null)))

maxAncestorDiff(maxDiffRoot)

def widthOfBinaryTreeHelper(nodes: List[TreeNode], maxWidth:Long = 1):Long={
  if (nodes.forall(_ == null)) maxWidth
  else {
    val nodesWithIndex  = nodes.zipWithIndex.filter(node => node._1 != null)
    val (_, firstIdx) = nodesWithIndex.head
    val (_, lastIdx) = nodesWithIndex.last
    val children = nodes.slice(firstIdx, lastIdx+1).flatMap(node => if(node == null) List(null,null) else List(node.left, node.right))
    println(s"firstIdx $firstIdx, lastIdx ${lastIdx}, width ${nodes.slice(firstIdx, lastIdx+1).length}")
    widthOfBinaryTreeHelper(children, maxWidth max nodes.slice(firstIdx, lastIdx+1).length)
  }
}

def widthOfBinaryTree(root: TreeNode): Int = {
  widthOfBinaryTreeHelper(List(root)).toInt
}

val widthTree = TreeNode(1,
  TreeNode(3, TreeNode(5, null, null), null),
  TreeNode(2,null,null))

//widthOfBinaryTree(widthTree)

val widthTree1 = TreeNode(1,
  TreeNode(3,
    TreeNode(5, null, null),
    TreeNode(3, null, null)),
  TreeNode(2, null, TreeNode(9, null, null)))

//widthOfBinaryTree(widthTree1 )

List(1,2,3,4).slice(0,0)

def getTreeSum(root:TreeNode, acc:Long=0):Long={
  if(root == null) acc
  else {
    val leftTreeSum = getTreeSum(root.left, acc)
    val rightTreeSum = getTreeSum(root.right, acc)
    leftTreeSum+rightTreeSum+root.value
  }
}

def maxProductHelper(root: TreeNode, maxPro: Long, treeSum:Long):(Long,Long)={
  if (root == null) (0,maxPro)
  else{
    val (lSum,lMaxPro) = maxProductHelper(root.left, maxPro, treeSum)
    val (rSum, rMaxPro) = maxProductHelper(root.right, maxPro, treeSum)
    val currentTreeSum = lSum+rSum+root.value
    val currentProduct = (treeSum-currentTreeSum) * currentTreeSum
    (currentTreeSum, lMaxPro max rMaxPro max currentProduct)
  }
}

def maxProduct(root: TreeNode): Int = {
  (maxProductHelper(root, root.value, getTreeSum(root))._2%1000000007).toInt
}

/*def split(ndOpt: Option[TreeNode], totalSum: Int): List[Long] = {
  ndOpt.map { nd =>
    val left  = split(Option(nd.left), totalSum)
    val right = split(Option(nd.right), totalSum)
    val sum   = nd.value + left(0) + right(0)
    val prod  = 1L * sum * (totalSum - sum)
    val maxProd = List(prod, left(2), right(2)).max
    List(sum, prod, maxProd)
  }.getOrElse(List(0L, 0L, 0L))
}

def totalSum(ndOpt: Option[TreeNode]): Int = {
  ndOpt.map(nd => nd.value + totalSum(Option(nd.left)) + totalSum(Option(nd.right)))
    .getOrElse(0)
}*/

//maxProduct(widthTree1)



