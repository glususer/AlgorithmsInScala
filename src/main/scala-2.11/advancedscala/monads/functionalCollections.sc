
import scala.annotation.tailrec

trait MySet[A] extends Function[A,Boolean] {
  def contains(elem:A):Boolean
  def +(elem:A):MySet[A]
  def ++(anotherSet:MySet[A]):MySet[A]
  def map[B](fn : A =>B):MySet[B]
  def flatMap[B](f: A => MySet[B]):MySet[B]
  def filter(predicate:A=>Boolean):MySet[A]
  def foreach(f:A=>Unit):Unit
  def remove (elem:A):MySet[A]
  def intersect (otherSet:MySet[A]):MySet[A]
  def subtract (otherSet: MySet[A]): MySet[A]

  def apply(elem:A):Boolean=contains(elem)
  def unary_! : MySet[A]
}

class NonEmptySet[A](head:A, tail: MySet[A]) extends MySet[A] {
  override def contains(elem: A): Boolean = head == elem || tail.contains(elem)

  override def +(elem: A) = if (this.contains(elem)) this else new NonEmptySet(elem, this)

  override def ++(anotherSet: MySet[A]): MySet[A] = tail ++ anotherSet + head

  override def map[B](fn: A => B): MySet[B] = (tail map fn) + fn(head)

  override def flatMap[B](f: A => MySet[B]) = (tail flatMap f) ++ f(head)

  override def filter(predicate: A => Boolean) = {
    val filteredTail = tail filter predicate
    if (predicate(head)) filteredTail + head
    else filteredTail
  }

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail foreach f
  }

  override def remove(elem: A) = if(head == elem) tail else (tail remove elem) + head

  override def intersect(otherSet: MySet[A]) ={
    filter(x => otherSet.contains(x))
  }

  override def unary_! : MySet[A] = {
    new PropertyBasedSet[A](x => this.contains(x))
  }

  override def subtract(otherSet: MySet[A]) =
    filter(! otherSet.contains)
}


class EmptySet[A] extends MySet[A] {
  override def contains(elem: A) = false

  override def +(elem: A) = new NonEmptySet(elem, new EmptySet)

  override def ++(anotherSet: MySet[A]) = anotherSet

  override def map[B](fn: A => B) = new EmptySet

  override def flatMap[B](f: A => MySet[B]) = new EmptySet

  override def filter(predicate: A => Boolean) = this

  override def foreach(f: A => Unit): Unit = ()

  override def remove(elem: A) = this

  override def intersect(otherSet: MySet[A]) = new EmptySet

  override def subtract(otherSet: MySet[A]) = this

  override def unary_! : MySet[A] = new AllInclusiveSet[A]


}

class AllInclusiveSet[A] extends MySet[A]{
  override def contains(elem: A) = true

  override def +(elem: A) = this

  override def ++(anotherSet: MySet[A]) = this

  override def map[B](fn: A => B) = ???

  override def flatMap[B](f: A => MySet[B]) = ???

  override def filter(predicate: A => Boolean) =  ???

  override def foreach(f: A => Unit): Unit = ???

  override def remove(elem: A) = ???

  override def intersect(otherSet: MySet[A]) = filter(!otherSet)

  override def subtract(otherSet: MySet[A]) = filter(otherSet)

  override def unary_! = new PropertyBasedSet[A]
}

class PropertyBasedSet[A](property: A => Boolean) extends MySet[A]{
  override def contains(elem: A) = property(elem)

  override def +(elem: A) = new PropertyBasedSet[A](x => property(x) || x == elem)

  override def ++(anotherSet: MySet[A]) = new PropertyBasedSet[A]( x  => property(x) || anotherSet(x))

  override def map[B](fn: A => B) = politelyDecline

  override def flatMap[B](f: A => MySet[B]) = politelyDecline

  override def filter(predicate: A => Boolean) = new PropertyBasedSet[A](x => property(x) &&predicate(x))

  override def foreach(f: A => Unit): Unit = ???

  override def remove(elem: A) = filter(x => x != elem)

  override def intersect(otherSet: MySet[A]) = filter(contains)

  override def subtract(otherSet: MySet[A]) = filter(! otherSet)

  override def unary_! = new PropertyBasedSet[A](x  => !property(x))

  def politelyDecline  = throw new IllegalArgumentException("Cannot be implemented")
}


object MySet{
   def apply[A](values: A*):MySet[A] = {
     @tailrec
    def buildSet(valSeq:Seq[A], acc:MySet[A]):MySet[A]= {
      if (valSeq.isEmpty) acc
      else {
        acc match {
          case x: EmptySet[A] => buildSet(valSeq.tail, new NonEmptySet[A](valSeq.head, new EmptySet))
          case x: NonEmptySet[A] => buildSet(valSeq.tail, acc + valSeq.head)
        }
      }
    }
    buildSet(values, new EmptySet[A])
  }

}

val s = MySet(1,2,3)
s ++ MySet(5,6,7,3) flatMap  (x => MySet(x*10)) foreach println

MySet(1,2,3,4) remove (1)foreach println