def helper(current: (Int,Int), start: Array[Int],end:Array[Int], acc:Array[Array[Int]] = Array.empty ):Array[Array[Int]]={
  if(start.isEmpty) acc:+Array(current._1, current._2)
  else {
    if (current._2 >= start(0)) helper((current._1, current._2 max end(0)), start.tail, end.tail, acc)
    else helper((start.head, end.head), start.tail, end.tail, acc :+ Array(current._1, current._2))
  }
}

def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
  scala.util.Sorting.quickSort(intervals)(new Ordering[Array[Int]] {
    def compare(x: Array[Int], y: Array[Int]) = {
      x.head compare y.head
    }
  })

  intervals.foreach(row => println(row.mkString(", ")))

  val start = intervals.map(interval => interval(0))
  val end = intervals.map(interval => interval(1))
  helper((start(0), end(0)), start.tail, end.tail)
}

val intervals3 = Array(Array(1,4),Array(0,4))
val intervals = Array(Array(1,4),Array(2,3))
val intervals2 = Array(Array(0,2),Array(1,5),Array(2,3),Array(3,5),Array(4,7),Array(8,10),Array(15,20))
val mergedInterval = merge(intervals3)

mergedInterval.foreach(row => println(row.mkString(", ")))