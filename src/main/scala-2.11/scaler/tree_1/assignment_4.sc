import Leetcode.TreeNode

import scala.collection.mutable
//You are given an integer array A denoting the Level Order Traversal of the Binary Tree.
//
//You have to Deserialize the given Traversal in the Binary Tree and return the root of the Binary Tree.
//
//NOTE:
//
//In the array, the NULL/None child is denoted by -1.
//For more clarification check the Example Input.
def inOrderTraversal(tree: TreeNode, acc: List[Int] = List.empty): List[Int] = {
  if (tree == null) acc
  else {
    inOrderTraversal(tree.left, acc) ::: (tree.value :: acc) ::: inOrderTraversal(tree.right, acc)
  }
}

//static void insertValue(int value)
//{
//  Node node = newNode(value);
//  if (root == null)
//    root = node;
//
//  else if (q.peek().left == null)
//    q.peek().left = node;
//  else
//  {
//    q.peek().right = node;
//    q.remove();
//  }
//  q.add(node);
//
//}
def preOrderTreeTraversal(tree: TreeNode, acc: List[Int]=List.empty): List[Int] = {
  if (tree == null) acc
  else {
    (tree.value :: acc) ::: preOrderTreeTraversal(tree.left, acc) ::: preOrderTreeTraversal(tree.right, acc)
  }
}

def insertValue(root:TreeNode,q:mutable.Queue[TreeNode],i:Int, arr:Array[Int]):TreeNode={
  println(s"q ${q.map(_.value)}, root ${preOrderTreeTraversal(root)}  i ${i}")
  if(i>=arr.length) root
  else {
      val node = if(arr(i) != -1)TreeNode(arr(i), null, null) else null
      if (root == null) {
        q.enqueue(node)
        insertValue(node, q, i + 1, arr)
      }
      else if (q.head.left == null) {
        q.head.left = node
        if(node != null) q.enqueue(node)
        insertValue(root, q, i + 1, arr)
      } else {
        q.head.right = node
        q.dequeue()
        if(node != null) q.enqueue(node)
        insertValue(root, q, i + 1, arr)
      }
  }
}
//TreeNode head = new TreeNode(A.get(0));
//        ArrayDeque<TreeNode> q = new ArrayDeque<TreeNode>();
//        q.addLast(head);
//        int i = 1;
//        while(!q.isEmpty() && i < A.size() - 1) {
//            TreeNode root = q.removeFirst();
//            TreeNode left = null;
//            TreeNode right = null;
//            if(A.get(i) != -1) {
//                left = new TreeNode(A.get(i));
//                q.addLast(left);
//            }
//            if(A.get(i+ 1) != -1) {
//                right = new TreeNode(A.get(i + 1));
//                q.addLast(right);
//            }
//
//            i = i + 2;
//            root.left = left;
//            root.right = right;
//        }
//        return head;
def solve(A: Array[Int]): TreeNode  = {
  import scala.collection.mutable
  val root = TreeNode(A(0),null,null)
  val queue =  mutable.Queue[TreeNode]()
  queue.enqueue(root)
  var index = 1
  while(index < A.length) {
    var n = queue.size
    while (n > 0) {
      val t = queue.dequeue()
      if (A(index) == -1) {
        t.left = null
        index = index + 1
      }
      else {
        t.left = TreeNode(A(index), null, null)
        queue.enqueue(t.left)
        index = index + 1
      }
      if(A(index) == -1){
        t.right=null
        index=index+1
      }
      else
      {
        t.right = TreeNode(A(index), null, null)
        queue.enqueue(t.right)
        index = index + 1
      }
      n=n-1
    }
  }
  root
}
// 1 2 4 5 6 3
val arr = Array(1, 2, 3, 4, 5, -1, -1, -1, -1, -1, 6, -1, -1)
arr.zipWithIndex
val tree = solve(Array(1, 2, 3, 4, 5, -1, -1, -1, -1, -1, 6, -1, -1))
preOrderTreeTraversal(tree)
