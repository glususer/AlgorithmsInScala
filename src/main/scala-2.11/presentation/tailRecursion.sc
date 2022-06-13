import scala.annotation.tailrec

// reverse a list
@tailrec
def reverse[T](l: List[T], acc:List[T]): List[T] = {
  l match {
    case Nil => List()
    case x :: Nil => x+:acc
    case x :: xs => {
      reverse(xs, x+:acc)
  }
}

reverse(List(1,2,3,4), List.empty)
reverse(List.empty, List.empty)

