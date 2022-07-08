//Given a sorted array of integers A which contains 1 and some number of primes.
//Then, for every p < q in the list, we consider the fraction p / q.
//
//What is the B-th smallest fraction considered?
//
//Return your answer as an array of integers, where answer[0] = p and answer[1] = q.

def solve(A: Array[Int], B: Int): Array[Int]  = {
  import scala.collection.mutable
  case class Fraction(data:Double, x:Int, y:Int)
  val pq = mutable.PriorityQueue.empty[Fraction](Ordering.by(fraction => fraction.data)).reverse
  A.foldLeft(1) { case (nextIdx, x) =>
    A.drop(nextIdx).foldLeft(pq) { case (q, y) =>
      q.enqueue(Fraction((x * 1.0) / (y * 1.0), x, y))
      q
    }
    nextIdx+1
  }

    var i = 1
    while(i<B){
      pq.dequeue()
      i=i+1
    }
  val result = pq.dequeue()
  Array(result.x, result.y)
}

solve(Array(1, 7),1)
