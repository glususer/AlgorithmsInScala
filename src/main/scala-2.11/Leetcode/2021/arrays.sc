

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object TreeNode {
  def apply(_value: Int, _left: TreeNode, _right: TreeNode): TreeNode = new TreeNode(_value, _left, _right)

  def isLeaf(tree: TreeNode): Boolean = tree.right == null && tree.left == null

  def insert(tree: TreeNode, item: Int): TreeNode = {
    if (tree == null) TreeNode(item, null, null)

    else if (isLeaf(tree)) {
      if (tree.value > item) tree.left = TreeNode(item, null, null)
      else tree.right = TreeNode(item, null, null)
      tree
    }

    else {
      if (tree.value < item) tree.right = insert(tree.right, item)
      else tree.left = insert(tree.left, item)
      tree
    }
  }

  def preOrderTreeTraversal(tree: TreeNode, acc: List[Int]): List[Int] = {
    if (tree == null) acc
    else {
      (tree.value :: acc) ::: preOrderTreeTraversal(tree.left, acc) ::: preOrderTreeTraversal(tree.right, acc)
    }
  }

  def postOrderTreeTraversal(tree: TreeNode, acc: List[Int]): List[Int] = {
    if (tree == null) acc
    else {
      postOrderTreeTraversal(tree.left, acc) ::: postOrderTreeTraversal(tree.right, acc) ::: (tree.value :: acc)
    }
  }

  def inOrderTraversal(tree: TreeNode, acc: List[Int]): List[Int] = {
    if (tree == null) acc
    else {
      inOrderTraversal(tree.left, acc) ::: (tree.value :: acc) ::: inOrderTraversal(tree.right, acc)
    }
  }
}

def helper(nodesLeft: List[Int]): TreeNode = {
  nodesLeft match {
    case x :: Nil => TreeNode(x, null, null)
    case _ =>
      val maxInArray = nodesLeft.max
      val (left, rightAndRoot) = nodesLeft.span(_ < maxInArray)
      TreeNode(maxInArray, if (left.isEmpty) null else helper(left), if (rightAndRoot.isEmpty || rightAndRoot.tail.isEmpty) null else helper(rightAndRoot.tail))

  }
}

//https://leetcode.com/problems/maximum-binary-tree/submissions/
def constructMaximumBinaryTree(nums: Array[Int]): TreeNode = {
  helper(nums.toList)
}

val tree = constructMaximumBinaryTree(Array(3, 2, 1, 6, 0, 5))

//TreeNode.inOrderTraversal(tree, List.empty)

//https://leetcode.com/problems/merge-intervals/
//[[1,3],[2,6],[8,10],[15,18]]
case class Interval(start:Int, end:Int)

def mergeInterval(intervalA:Interval, intervalB: Interval):Interval={
  Interval(math.min(intervalA.start, intervalB.start), math.max(intervalA.end, intervalB.end))
}
def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {

 val mergedIntervals =  intervals.map(interval => Interval(interval(0), interval(1)))
    .sortWith{case (inta, intb) => inta.start < intb.start}
    .foldLeft(List[Interval]()){case (acc, currentInterval) =>
    acc match{
      case Nil => acc:+currentInterval
      case x::xs => if(currentInterval.start <= x.end)
        Interval(math.min(x.start, currentInterval.start), math.max(x.end, currentInterval.end))+:xs
      else currentInterval+:acc
    }}.reverse

  mergedIntervals.foreach(interval => print(s" $interval"))
  mergedIntervals.map(interval => Array(interval.start, interval.end)).toArray
}
//[[2,3],[2,2],[3,3],[1,3],[5,7],[2,2],[4,6]]
merge(Array(Array(2,3),Array(2,2),Array(3,3),Array(1,3),Array(5,7),Array(2,2),Array(4,6)))

//https://leetcode.com/problems/single-number/
def singleNumber(nums: Array[Int]): Int = {
nums.reduceLeft(_^_)
}

//singleNumber(Array(2,2,6,6,5))

//https://leetcode.com/problems/the-kth-factor-of-n/
def kthFactor(n: Int, k: Int): Int = {
 val factors =  (1 to n).collect{case x if(n%x == 0 ) => x}
   if(factors.length < k) -1
   else factors.take(k).last
}
List(1,2,4).take(4)

kthFactor(7,2)

