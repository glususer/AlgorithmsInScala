
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.Try


// fulfill a future immediately with a value
def returnImmediately[T](t:T):Future[T]={
  Future{
    t
  }
}

def inSequence[A,B](a:Future[A], b:Future[B]):Future[B]={
  a.flatMap(_=> b)
}

def first[T](t1:Future[T], t2:Future[T]):Future[T]={
  val promise = Promise[T]

  t1.onComplete(promise.tryComplete)
  t2.onComplete(promise.tryComplete)

  promise.future

}

def last[T](t1:Future[T], t2:Future[T]):Future[T]={
  val bothPromise = Promise[T]
  val lastPromise = Promise[T]
  val checkAndComplete = (result:Try[T]) => if(!bothPromise.tryComplete(result)) lastPromise.tryComplete(result)

  t1.onComplete(checkAndComplete)
  t2.onComplete(checkAndComplete)

}