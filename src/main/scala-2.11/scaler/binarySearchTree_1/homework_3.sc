import Leetcode.TreeNode

import scala.collection.mutable

//Given a binary search tree A, where each node contains a positive integer, and an integer B,
// you have to find whether or not there exist two different nodes X and Y such that X.value + Y.value = B.
//
//Return 1 to denote that two such nodes exist. Return 0, otherwise.
def createMap(root:TreeNode, secondNum: mutable.Map[TreeNode,Int], B:Int): mutable.Map[TreeNode,Int]={
  if(root == null) secondNum
  else{
    val left = createMap(root.left, secondNum,B)
    val right = createMap(root.right, secondNum,B)
    val updated = left++right
    updated+=(root-> (B - root.value))
  }
}

def existsInTree(root:TreeNode, key: Int, firstNode:TreeNode):Boolean = {
  if(root == null) false
  else if(root.value == key){
    root != firstNode
  }
  else{
    if(key > root.value)existsInTree(root.right, key,firstNode)
    else existsInTree(root.left, key,firstNode)
  }
}

def t2Sum(A: TreeNode, B: Int): Int  = {
  val numMap = createMap(A, mutable.Map[TreeNode,Int](), B)
  if(numMap.exists{case(firstNode,secondNum) =>existsInTree(A,secondNum,firstNode) }) 1 else 0
}

