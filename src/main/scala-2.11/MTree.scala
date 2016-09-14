/**
  * Created by shivangi on 13/08/16.
  */


class MTree[+T](value: T, children: List[MTree[T]]) {

  def this(value: T) = this(value, List())

  //override def toString = "M(" + value.toString + " {" + children.map(_.toString).mkString(",") + "})"
  def stringValue: String = value.toString + children.map(_.stringValue).mkString("")

  def nodeCount[T]: Int = {
    children.foldLeft(1)(_ + _.nodeCount)
    children.foldLeft(1)(_ + _.nodeCount)
  }

  def internalPathLength[T]: Int = {
    children.foldLeft(0)((r, c) => r + c.nodeCount + c.internalPathLength)
  }

  override def toString = value.toString + children.map(_.toString + "^").mkString("")

  /*def postOrder[T]: List[T] = {
    //   children.foldLeft(List[T]())((r,c)=>c.postOrder:::r)
    children.flatMap(_.postOrder) ::: List(value)
  }*/

  def lispyTree: String = {
    if (children.isEmpty) value.toString
    else "(" + value.toString + " " + children.map(_.lispyTree).mkString(" ") + ")"
  }
}

object MTree {
  def apply[T](value: T) = new MTree(value, List())

  def apply[T](value: T, children: List[MTree[T]]) = new MTree(value, children)
}
