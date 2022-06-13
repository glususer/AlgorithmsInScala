// A = "abadbc"
// output = aabbdd

def solve(A: String): String = {
  import scala.collection.mutable

  val queue = mutable.Queue[Char]()
  val freqMap = mutable.HashMap[Char, Int]()
  A.foldLeft(queue, freqMap, "") { case ((q, fMap, str), ch) =>

    freqMap.update(ch, freqMap.getOrElse(ch, 0) + 1)
    if (fMap.getOrElse(ch, 0) == 1) q.enqueue(ch)

    while (q.nonEmpty && fMap.getOrElse(q.head, 0) > 1)
      q.dequeue()

    if (q.isEmpty) (q, fMap, str + "#")
    else (q, fMap, str + q.head)
  }._3
}

solve("a")