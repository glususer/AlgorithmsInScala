

  sealed abstract class Tree[+T] {
    def addValue[U >: T <% Ordered[U]](x: U): Tree[U]

    def toStringFromTree: String

    def toDotString:String

    def isGreaterThan[U >: T <% Ordered[U]](x: U,y:U): Boolean
  }

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    def addValue[U >: T <% Ordered[U]](x: U) = {
      if (x < value) Node(value, left.addValue(x), right)
      else Node(value, left, right.addValue(x))
    }

    override def toStringFromTree: String = {
      if(left == End && right == End) value.toString
      else value+"("+left.toStringFromTree+","+right.toStringFromTree+")"
    }

    override def toString = "T(" + value.toString + " " + left.toString + " " + right.toString + ")"

    override def toDotString: String = {
      if(left == End && right == End) value.toString+"."+"."
      else value+left.toStringFromTree+right.toStringFromTree
    }

    override def isGreaterThan[U >: T <% Ordered[U]](x: U,y:U): Boolean = if (x < y) false else true
  }

  case class PositionedNode[+T](value: T,left: Tree[T], right: Tree[T], x: Int, y: Int) extends Tree[T] {
    def addValue[U >: T <% Ordered[U]](x: U) = {
      if (x < value) Node(value, left.addValue(x), right)
      else Node(value, left, right.addValue(x))
    }

    override def toString = "T[" + x.toString + "," + y.toString + "](" + value.toString + " " + left.toString + " " + right.toString + ")"

    override def toStringFromTree: String = {
      if(left == End && right == End) value.toString
      else value+"("+left.toStringFromTree+","+right.toStringFromTree+")"
    }
    override def toDotString: String = {
      if(left == End && right == End) value.toString+"."+"."
      else value+left.toStringFromTree+right.toStringFromTree
    }

    override def isGreaterThan[U >: T <% Ordered[U]](x: U,y:U): Boolean = if (x < y) false else true
  }

  case object End extends Tree[Nothing] {
    def addValue[U <% Ordered[U]](x: U) = Node(x)

    override def toString = "."

    override def toStringFromTree: String = ""

    override def toDotString: String = ""

    override def isGreaterThan[U <% Ordered[U]](x: U,y:U): Boolean = false
  }

  object Node {
    def apply[T](value: T): Node[T] = Node(value, End, End)
  }

  object Tree {


    def symmetricBalancedTrees[T](noOfNodes: Int, s: T): List[Tree[T]] = {
      cBalanced(noOfNodes, s).filter(isSymmetric(_))
    }

    def inorderTraversal[T](tree: Tree[T]): List[T] = {

      def helper(tree: Tree[T], l: List[T]): List[T] = {
        tree match {
          case (End) => Nil
          case (tree: Node[T]) => helper(tree.left, l) ::: tree.value :: l ::: helper(tree.right, l)
        }
      }
      helper(tree, List())
    }

    def preorderTraversal[T](tree: Tree[T]): List[T] = {

      def helper(tree: Tree[T], l: List[T]): List[T] = {
        tree match {
          case (End) => Nil
          case (tree: Node[T]) => tree.value :: l ::: helper(tree.left, l) ::: helper(tree.right, l)
        }
      }
      helper(tree, List())
    }


    def inorderSeqAndDepth[T](tree: Tree[T]): List[(T, Int)] = {
      def helper(tree: Tree[T], l: List[(T, Int)], n: Int): List[(T, Int)] = {
        tree match {
          case (End) => l
          case (tree: Node[T]) => {
            helper(tree.left, l, n + 1) ::: (tree.value, n) :: l ::: helper(tree.right, l, n + 1)
          }
        }
      }
      helper(tree, List(), 1)
    }

    def positionCoordinateOfNode[T](l: List[(T, Int)], node: Node[T]): (Int, Int) = {
      def helper(l: List[(T, Int)], node: Node[T], n: Int): (Int, Int) = {
        l match {
          case (x :: xs) => {
            if (x._1 == node.value) (n, x._2)
            else helper(xs, node, n + 1)
          }
        }
      }
      helper(l, node, 1)
    }


    private def createTree[T](noOfNodes: Int, c: T, l: List[Tree[T]]): List[Tree[T]] = {
      noOfNodes match {
        case 0 => l
        case 1 => new Node(c, End, End) :: Nil
        case 2 => new Node(c, new Node(c, End, End), End) :: new Node(c, End, new Node(c, End, End)) :: Nil
        case _ => {
          for {
            nl <- 0 to noOfNodes - 1
            nr = noOfNodes - 1 - nl
            leftList = createTree(nl, c, l)
            rightList = createTree(nr, c, l)
            left <- leftList
            right <- rightList
          } yield new Node(c, left, right)
        }.toList
      }
    }

    def nodesInSubtree[T](tree: Tree[T]): Int = {
      tree match {
        case (End) => 0
        case (tree: Node[T]) => {
          if (tree.left == End && tree.right == End) 1
          else nodesInSubtree(tree.left) + nodesInSubtree(tree.right) + 1
        }
      }
    }

    def isBalanced[T](tree: Tree[T]): Boolean = {
      tree match {
        case (tree: Node[T]) => {
          if (math.abs(nodesInSubtree(tree.left) - nodesInSubtree(tree.right)) <= 1) {
            isBalanced(tree.left) && isBalanced(tree.right)
          }
          else false
        }
        case (End) => true
      }
    }

    def cBalanced[T](noOfNodes: Int, c: T): List[Tree[T]] = {
      val x = createTree(noOfNodes, c, List(End))
      /*for (tree <- x)
        println(nodesInSubtree(tree), tree)*/
      //x.filter(isBalanced(_))
      x.filter(isSymmetric(_))
    }

    def isMirrorOf[T](left: Tree[T], right: Tree[T]): Boolean = {
      (left, right) match {
        case (End, right: Node[T]) => false
        case (left: Node[T], End) => false
        case (End, End) => true
        case (left: Node[T], right: Node[T]) => {
          if (left.value == right.value && isMirrorOf(left.right, right.left) && (isMirrorOf(left.left, right.right))) true
          else false
        }
      }
    }

    def isSymmetric[T](tree: Tree[T]): Boolean = {
      tree match {
        case (End) => true
        case (tree: Node[T]) => isMirrorOf(tree.left, tree.right)
      }

    }

    def fromList[T <% Ordered[T]](l: List[T]): Tree[T] = {
      l.foldLeft(End: Tree[T])((r, a) => r.addValue(a))
    }

    private def heightOfTree[T](tree: Tree[T]): Int = {
      tree match {
        case (End) => 0
        case (tree: Node[T]) => {
          if (tree.left == End && tree.right == End) 1
          else (math.max(heightOfTree(tree.left), heightOfTree(tree.right)) + 1)
        }
      }
    }

    def isHBalanced[T](tree: Tree[T]): Boolean = {
      tree match {
        case (tree: Node[T]) => {
          if (math.abs(heightOfTree(tree.left) - heightOfTree(tree.right)) <= 1) {

            //           println("height of left and right subtree", heightOfTree(tree.left), heightOfTree(tree.right))
            isHBalanced(tree.left) && isBalanced(tree.right)
          }
          else false
        }
        case (End) => true
      }
    }

    def hbalTrees[T](height: Int, value: T): List[Tree[T]] = {
      val lower = math.pow(2, height).asInstanceOf[Int]
      val upper = (math.pow(2, height + 1) - 1).asInstanceOf[Int]
      println(lower, upper)
      val m = {
        for {
          x <- lower to upper
        } yield createTree(x, value, List(End))
      }.toList

      val z = m.flatMap(x => x)
      z.filter(isHBalanced(_))
    }

    def hbalTreesWithNodes[T](noOfNodes: Int, value: T): List[Tree[T]] = {
      val x = createTree(noOfNodes, value, List(End))
      x.filter(isHBalanced(_))
    }

    def leafCount[T](tree: Tree[T]): Int = {
      tree match {
        case (End) => 0
        case (tree: Node[T]) => leafCount(tree.left) + leafCount(tree.right) + 1
      }
    }

    def leafList[T](tree: Tree[T]): List[Node[T]] = {
      def helper(tree: Tree[T], l: List[Node[T]]): List[Node[T]] = {
        tree match {
          case (End) => l
          case (tree: Node[T]) => {
            if (tree.left == End && tree.right == End)
              tree :: l
            else {
              helper(tree.left, l) ::: helper(tree.right, l)
            }
          }
        }
      }
      helper(tree, List())
    }

    def internalList[T](tree: Tree[T]): List[T] = {
      def helperInternal(tree: Tree[T], l: List[T]): List[T] = {
        (tree) match {
          case (End) => l
          case (tree: Node[T]) => {
            if (tree.left == End && tree.right == End) l
            else
              tree.value :: helperInternal(tree.left, l) ::: helperInternal(tree.right, l)
          }
        }
      }
      helperInternal(tree, List())
    }

    def atLevel[T](tree: Tree[T], n: Int): List[T] = {
      def helper(tree: Tree[T], n: Int, l: List[T]): List[T] = {
        (tree, n) match {
          case (tree: Node[T], 1) => tree.value :: l
          case (End, n) => l
          case (tree: Node[T], n) => helper(tree.left, n - 1, l) ::: helper(tree.right, n - 1, l)
        }

      }
      helper(tree, n, List())
    }

    def completeBinaryTree[T](noOfNodes: Int, value: T): Tree[T] = {
      def generateTree(address: Int): Tree[T] = {
        if (address > noOfNodes) End
        else Node(value, generateTree(2 * address), generateTree(2 * address + 1))
      }
      generateTree(1)
    }

    def layoutBinaryTree[T](tree: Tree[T]): Tree[T] = {
      val inorderList = inorderSeqAndDepth(tree)
      tree match {
        case (tree: Node[T]) => {
          val (seq, depth) = positionCoordinateOfNode(inorderList, tree)
          if (tree.left == End && tree.right == End) new PositionedNode(tree.value, End, End, seq, depth)
          else new PositionedNode(tree.value, layoutBinaryTree(tree.left), layoutBinaryTree(tree.right), seq, depth)
        }
        case (End) => End
      }
    }

    def fromInOrderPreorder[T](tree: Tree[T]): Tree[T] = {
      val inorderList = inorderTraversal(tree)
      val preorderList = preorderTraversal(tree)
      println(tree)
      def helper(in: List[T], pre: List[T], tree: Tree[T]): Tree[T] = {
        (in, pre) match {
          case (List(), _) => End
          case (_, List()) => End
          case (x :: Nil, y :: Nil) => new Node(x, End, End)
          case (x :: xs, y :: ys) => {
            val (leftIn, rightIn) = in.span(_ != y)
            new Node(y, helper(leftIn, ys.take(leftIn.size), tree), helper(rightIn.tail, ys.drop(leftIn.size), tree))
          }
        }
      }
      helper(inorderList, preorderList, End)

    }

    //helper(tree.left, l) ::: tree.value :: l ::: helper(tree.right, l)
    def levelOrder[T](tree: Tree[T]): List[T] = {

      val depth = heightOfTree(tree)

      def helper[T](tree: Tree[T], k: Int, l: List[T]): List[T] = {
        (tree, k) match {
          case (tree, k) => {
            if(k > depth) l
            else helper(tree,k+1,l:::atLevel(tree,k))
          }
        }
      }
      helper(tree, 1, List())
    }

    def binaryTreePaths[T](tree:Tree[T]):List[List[T]]={
      def helper[T](tree:Tree[T],curPath:List[T],paths:List[List[T]]):List[List[T]]={
        tree match {
          case (End)=>paths
          case (tree: Node[T])=>{
            if(tree.left == End && tree.right==End)
              (tree.value::curPath).reverse::paths
            else
              helper(tree.left,tree.value::curPath,paths) ::: helper(tree.right,tree.value::curPath,paths)
          }
        }
      }
      helper(tree,List(),List())
    }

    def minDepth[T](tree:Tree[T]):Int={
      tree match {
        case (End) => 0
        case (tree: Node[T]) => {
          if (tree.left == End && tree.right == End) 1
          else (math.min(heightOfTree(tree.left), heightOfTree(tree.right)) + 1)
        }
      }
    }

    def arrayToHeightBalTree[T](arr:Array[T]):Tree[T]={
      def helper[T](array:Array[T]):Tree[T]={
        (array.length) match{
          case (1)=>new Node(array(0),End,End)
          case (0)=> End
          case(k)=>{
            val mid = array.length/2
            (new Node(array(mid), helper(array.take(mid)), helper(array.drop(mid+1))))
          }
        }
      }
      helper(arr)
    }

    def rightViewOfBinaryTree[T](tree:Tree[T]):List[T]={
      val depth = heightOfTree(tree)
      def helper[T](tree:Tree[T],l:List[T],n:Int):List[T]={
        (tree,n) match{
          case (tree,n)=>{
            if(n > depth) l
            else helper(tree,atLevel(tree,n).last::l,n+1)
          }
        }
      }
      helper(tree,List(),1).reverse
    }

    def LCA[T](tree:Tree[T],node1:Tree[T],node2:Tree[T]):String={
     /* val inorder = inorderTraversal(tree)
      val preorder = preorderTraversal(tree)
      val leftIdx = if (inorder.indexOf(node1) < inorder.indexOf(node2)) inorder.indexOf(node1) else inorder.indexOf(node2)
      val rightIdx = if (inorder.indexOf(node1) > inorder.indexOf(node2)) inorder.indexOf(node1)  else inorder.indexOf(node2)
      val inorderNodes = inorder.take(rightIdx+1).drop(leftIdx)
      val preorderNodes = preorder.take(leftIdx)
      val lca = inorderNodes.intersect(preorderNodes).mkString("")
      if (lca.isEmpty) inorder(rightIdx).toString() else lca.mkString("")*/

      val path1 = rootToLeafPath(tree,node1)
      val path2 = rootToLeafPath(tree,node2)
      val intersect = path1.intersect(path2)
???
    }

    def invertBTree[T](tree:Tree[T]):Tree[T]={
      def helper[T](left:Tree[T],right:Tree[T],tree:Tree[T]):Tree[T]={
        (left,right) match{
          case (End,End)=>tree
          case (left:Node[T],right:Node[T])=>{
            ???
          }
        }
      }
        ???
    }

    def rootToLeafPath[T](tree: Tree[T], node:Tree[T]):List[T] = {
      def helper(tree:Tree[T],node:Tree[T],l:List[T]):List[T]={
        (tree,node) match{
          case (tempNode:Node[T],node:Node[T])=>{
            if(tempNode.value==node.value) tempNode.value::l
            else {
              helper(tempNode.left,node,tempNode.value::l)
              helper(tempNode.right,node,tempNode.value::l)
            }
          }
          case (End,node:Node[T])=>l
        }
      }
      helper(tree,node,List())
    }


    def allLeafToLeafPaths[T](tree:Tree[T]):List[List[T]]={
      val leafNodes:Array[Node[T]] = leafList(tree).toArray
      val paths:List[List[T]] = {for{
        x<-0 until (leafNodes.length-1)
        y<-x+1 until (leafNodes.length-1)
        path1:List[T] = rootToLeafPath(tree,leafNodes(x))
        path2:List[T] = rootToLeafPath(tree,leafNodes(y))
        intersect = path1.intersect(path2)
        path = ((LCA(tree,leafNodes(x),leafNodes(y)),End,End)::path1.filter(x=>intersect.contains(x))).reverse:::path2.filter(x=>intersect.contains(x))
      }yield path2}.toList
      paths
    }
  }
