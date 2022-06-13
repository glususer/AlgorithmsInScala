//Given an array of integers A, sort the array into a wave-like array and return it.
//In other words, arrange the elements into a sequence such that
//
//a1 >= a2 <= a3 >= a4 <= a5.....
//NOTE: If multiple answers are possible, return the lexicographically smallest one

def wave(A: Array[Int]): Array[Int]  = {
  scala.util.Sorting.quickSort(A)
  val (odd, even) = A.zipWithIndex.partition(_._2%2==1)

  (1 to A.length by 2).foldLeft(odd.map(_._1), even.map(_._1),List[Int]())
  {case ((first, second,acc), ele) =>
   // println(s"first ${first.toList}, second ${second.toList}, acc $acc i $ele")
    if(first.isEmpty)(first, Array.empty,acc++second)
    else if (second.isEmpty) (Array.empty, second,acc++first)
    else {
      (first.tail, second.tail, acc:+first.head:+second.head)
    }
  }._3.toArray
}

wave(Array(5, 1, 3, 2, 4))