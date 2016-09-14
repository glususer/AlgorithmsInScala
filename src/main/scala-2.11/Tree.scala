

  sealed abstract class Tree[+T] {
    def addValue[U >: T <% Ordered[U]](x: U): Tree[U]

    def toStringFromTree: String

    def toDotString:String
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

  }

  case object End extends Tree[Nothing] {
    def addValue[U <% Ordered[U]](x: U) = Node(x)

    override def toString = "."

    override def toStringFromTree: String = ""

    override def toDotString: String = ""
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
          if (isMirrorOf(left.right, right.left) && (isMirrorOf(left.left, right.right))) true
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

    def leafList[T](tree: Tree[T]): List[T] = {
      def helper(tree: Tree[T], l: List[T]): List[T] = {
        tree match {
          case (End) => l
          case (tree: Node[T]) => {
            if (tree.left == End && tree.right == End)
              tree.value :: l
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
            var (leftIn, rightIn) = in.span(_ != y)
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
            if(k==depth) l
            else helper(tree,k+1,l:::atLevel(tree,k))
          }
        }
      }
      helper(tree, 1, List())
    }
  }