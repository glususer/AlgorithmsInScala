import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * Created by shivangi on 08/08/16.
  */
@RunWith(classOf[JUnitRunner])
class TreeSuite extends FunSuite {
 /*val bal =  Tree.cBalanced(7,"x")
  for (tree<-bal)
    println(tree)

  print (Tree.isSymmetric(Tree.fromList(List(5, 3, 18, 1, 4, 12, 21))))*/

  //print (Tree.symmetricBalancedTrees(5, "x"))

  val t1 = new Node("c", new Node("c", End, new Node("c",End,End)), new Node("c",new Node("c",End,End),End))
 println(Tree.levelOrder(Node('a', Node('b',Node('f'),Node('r')), Node('c', Node('d'), Node('e',Node('l',End,End),End)))))
 // println(BTree.nodesInSubtree(t1))
 /* val x = List(List(1,2,3),List(4,5,6),List(7,8,9))
  val y = x.flatMap(c=>c)
  println(x)
  println(y)*/
  /*val x= Tree.hbalTreesWithNodes(5,"o")
  val y = (x.map(_=>1)).foldLeft(0)(_+_)
  println(y)
  for (tree<-x)
    println(tree, Tree.leafCount(tree))*/

 /* println(Tree.internalList(Node('a', Node('b',Node('f'),Node('r')), Node('c', Node('d'), Node('e',Node('l',End,End),End)))))
  println(Tree.leafList(Node('a', Node('b',Node('f'),Node('r')), Node('c', Node('d'), Node('e',Node('l',End,End),End)))))
  println(Tree.atLevel(Node('a', Node('b',Node('f'),Node('r')), Node('c', Node('d'), Node('e',Node('l',End,End),End))),4))

  println(math.log(1)/math.log(2))
  println(Tree.completeBinaryTree(4,'x'))*/
 // println(Tree.inorderSeqAndDepth(Node('a', Node('b',Node('f'),Node('r')), Node('c', Node('d'), Node('e',Node('l',End,End),End)))))
  //println(Tree.layoutBinaryTree(Node('a', Node('b',Node('f'),Node('r')), Node('c', Node('d'), Node('e',Node('l',End,End),End)))))


  //println(Tree.fromInOrderPreorder(Node('a', Node('b', Node('d'), Node('e')), Node('c', End, Node('f', Node('g'), End)))))

/*  val l = List(10,20,30,40,50,60,70,80)
  println(l.take(4))
  println(l.drop(4))
//  println(l.splitAt(0))

  println(l.zipWithIndex.partition(x=>x._2>4))//.map(_._1))*/

 // l.filter(x=>l.indexOf(x) > l.indexOf(_))

}


