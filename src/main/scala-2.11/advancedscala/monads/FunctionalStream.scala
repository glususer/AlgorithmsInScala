
import scala.annotation.tailrec

  abstract class MyStream[+A]{
    def isEmpty: Boolean
    def head:A
    def tail: MyStream[A]

    def #::[B >: A](element:B):MyStream[B]

    def ++[B >: A](anotherStream: => MyStream[B]):MyStream[B]

    def foreach(f: A=>Unit):Unit
    def map[B](f:A=>B):MyStream[B]
    def flatMap[B](f:A=>MyStream[B]):MyStream[B]
    def filter(f:A=>Boolean):MyStream[A]

    def take(n:Int):MyStream[A]
    final def takeAsList(n:Int):List[A] = take(n).toList()

    @tailrec
    final def toList[B  >:A](acc:List[B] = Nil):List[B] ={
      if(isEmpty) acc
      else tail.toList(acc:+head)
    }

  }
  // without call by name , tail will always be eagerly evaluated
  class Cons[+A](hd:A, tl: => MyStream[A]) extends MyStream[A] {
    override def isEmpty = false

    override val head = hd

    override lazy val tail = tl // call by need

    override def #::[B >: A](element: B) = new Cons(element, this)

    override def ++[B >: A](anotherStream: => MyStream[B]) = new Cons(head, tail++anotherStream)

    override def foreach(f: A => Unit): Unit = {
      f(head)
      tail.foreach(f)
    }

    // map still preserves lazy evaluation
    override def map[B](f: A => B) = new Cons(f(head), tail.map(f))

    override def flatMap[B](f: A => MyStream[B]) = f(head)++ tail.flatMap(f)

    override def filter(f: A => Boolean) = if(f(head)) new Cons(head, tail.filter(f)) else tail.filter(f)

    override def take(n: Int) = if(n<=0) new EmptyStream else if(n==1) new Cons(head, new EmptyStream) else new Cons(head, tail.take(n-1))

  }

  class EmptyStream[A] extends MyStream[Nothing]{
    override def isEmpty = true

    override def head = throw new NoSuchElementException

    override def tail = throw new NoSuchElementException

    override def #::[B >: Nothing](element: B) = new Cons(element, this)

    override def ++[B >: Nothing](anotherStream: => MyStream[B]) = anotherStream

    override def foreach(f: Nothing => Unit): Unit = ()

    override def map[B](f: Nothing => B) = this

    override def flatMap[B](f: Nothing => MyStream[B]) = this

    override def filter(f: Nothing => Boolean) = this

    override def take(n: Int) = this

  }

  object MyStream {
    def from[A](start: A)(generator: A => A): MyStream[A] = {
      new Cons(start, MyStream.from(generator(start))(generator))
    }
  }

object MyStreamPlayGround extends App{
  val naturals = MyStream.from(1)(x=> x+1)
  println(naturals.head)
  println(naturals.tail.head)
  val withZero = 0#::naturals
  (withZero.take(100).foreach(println))
  println(withZero.flatMap(x => new Cons(x,new Cons(x+1, new EmptyStream))).take(10).toList())
//1,1,2,3,5,8,13
  val fibonacci = 1#::MyStream.from(new Cons(0,new Cons(1,new EmptyStream)))(str => new Cons(str.tail.head, new Cons(str.head+str.tail.head, new EmptyStream))).map(stream => stream.head+stream.tail.head)
  println(fibonacci.take(10).toList())

  def prime(numbers: MyStream[Int]):MyStream[Int]={
    if(numbers.isEmpty) numbers
    else{
      new Cons(numbers.head, prime(numbers.tail.filter(num => num %numbers.head != 0)))
    }
  }

  println(prime(MyStream.from(2)(x=> x+1)).take(100).toList())

}
