import scala.annotation.tailrec
// return elements from i to j position in tail recursive method


@tailrec
def slice[T](i: Int, j: Int, acc: List[T], l: List[T]): List[T] = {
  l match {
    case Nil => acc
    case _ if j == 0 => acc
    case head :: tail =>
      if (i == 0) slice(i, j - 1, acc :+ head, tail)
      else slice(i - 1, j, acc, tail)
  }
}

@tailrec
def slice2[T](i:Int, j:Int,acc:List[T], l:List[T]):List[T]={
  i match {
    case i == j => acc
    case _ => slice2(i + 1, j, acc:::List(l(i)), l)
  }
}