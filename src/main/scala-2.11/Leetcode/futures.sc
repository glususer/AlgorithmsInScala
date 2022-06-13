import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, FiniteDuration}
import scala.concurrent.{Await, Future}

def generateMagicNumber(): Int = {
  Thread.sleep(3000L)
  23
}
val generatedMagicNumberF: Future[Int] = Future {
  generateMagicNumber()
}

def multiply(multiplier: Int): Future[Int] =
  if (multiplier == 0) {
    Future.successful(0)
  } else {
    Future(multiplier * generateMagicNumber())
  }

val l: List[Future[Int]] = List()
val z = Future.sequence(l).map(_.max).recover{
  case _ => 0
}
val maxWaitTime: FiniteDuration = Duration(5, TimeUnit.SECONDS)
Await.result(z, maxWaitTime)

def add(x:Int)(y:Int):Int={
  x+y
}

val convertLowNumToString: PartialFunction[Int, String] = new PartialFunction[Int, String] {
  val nums = Array("one", "two", "three", "four", "five")
  def apply(i: Int) = nums(i-1)
  def isDefinedAt(i: Int) = i > 0 && i < 6
}