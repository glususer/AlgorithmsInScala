import Monad.Lazy

object Monad extends App{
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
    private lazy val internalValue = value
    def flatMap[B](f : (=>A)=>Lazy[B]):Lazy[B]=f(internalValue)

    def use:A = internalValue
  }

  object Lazy{
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
  }

  trait Monad[T]{
    def flatMap[B](f:T=>Monad[B]):Monad[B]
   // def map[B](f:T=>B):Monad[B] = flatMap(v => unit(f(v)))
    def flatten(m:Monad[Monad[T]]):Monad[T]= m.flatMap(v => v)
  }

}
val lazyInstance = Lazy{
  println("today I will conquer thr world")
  42
}

val flatMappedLazyInstance = lazyInstance.flatMap(x  => Lazy{
  10*x
})

val flatMappedLazyInstance2 = lazyInstance.flatMap(x  => Lazy{
  100*x
})

flatMappedLazyInstance.use
flatMappedLazyInstance2.use
//println(lazyInstance.use)
//println(flatMappedLazyInstance.use)


