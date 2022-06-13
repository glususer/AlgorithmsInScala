class Person(val name:String, val age:Int){

}

abstract class MyList[+A] {
  def head: A = ???

  def tail: MyList[A] = ???
}

case object Empty extends MyList[Nothing]
case class Cons[+A](override val head:A, override val tail:MyList[A]) extends MyList[A]

object MyList{
  def unapplySeq[A](list: MyList[A]): Option[Seq[A]] = {
  if(list == Empty) Option(Seq.empty)
  else unapplySeq(list.tail).map(ele => list.head +: ele)
  }
}

val myList = Cons(1, Cons(2, Cons(3,Empty)))

myList match{
  case MyList(1,2, _*) =>"starting from 1 and 2"
  case _ => "blah"
}

// Advanced functional programming

