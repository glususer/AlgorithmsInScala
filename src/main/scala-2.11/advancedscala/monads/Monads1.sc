

/*object Monad extends App{
  // our own try Monad
  trait Attempt[+A]{
    def flatMap[B](f: A => Attempt[B]): Attempt[B]
  }

  object Attempt{
    def apply[A](a: => A): Attempt[A] =
      try{
        Success(a)
      }catch{
        case e : Throwable  => Fail(e)
      }
  }

  case class Success[+A](value:A)extends Attempt[A]{
    def flatMap[B](f: A => Attempt[B]): Attempt[B] =
      try {
        f(value)
      }catch{
        case e: Throwable => Fail(e)
      }
  }

  case class Fail(e: Throwable) extends Attempt[Nothing]{
    def flatMap[B](f: Nothing => Attempt[B]): Attempt[B] = this

  }

  class Lazy[+A](value: => A){
    // call by need
    private lazy val internalValue = value
    def use:A =internalValue
    def flatMap[B](f : (=> A)=>Lazy[B]):Lazy[B]=f(internalValue)
  }

  object Lazy{
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
  }

  val lazyInstance = Lazy{
    println("today I will conquer thr world")
    42
  }
  val flatMapped = lazyInstance.flatMap(x => Lazy{
    x*10
  })

  val flatMapped2 = lazyInstance.flatMap(x => Lazy{
    x*10
  })

  trait Monad[T]{
    def flatMap[B](f  : T => Monad[B]):Monad[B] = {
      ???
    }

    def map[B](f: T=>B):B={
    //  flatMap(x => unit(f(x)))
      ???
    }

    def flatten[B](monad : Monad[Monad[B]]):Monad[B]={
      monad.flatMap((m:Monad[B]) => m)
    }
  }

}*/

val x = List(List(1, 2), List(3, 4)).flatten

println(x)