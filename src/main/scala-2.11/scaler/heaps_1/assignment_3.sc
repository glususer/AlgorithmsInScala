import scala.collection.mutable



//Given an integer array A of size N.
//
//You have to find the product of the three largest integers in array A from range 1 to i, where i goes from 1 to N.
//
//Return an array B where B[i] is the product of the largest 3 integers in range 1 to i in array A. If i < 3, then the integer at index i in B should be -1.
//4 0 3 2

def getProductOfThree(pq: mutable.PriorityQueue[Long]):Long={
  pq.foldLeft(1l){case (product, ele) =>
    product * ele }
}

def solve(A: Array[Int]): Array[Int]  = {
  val pq = mutable.PriorityQueue.empty(Ordering.Long).reverse
  if(A.length <3) A.map(_ => -1)
  else{
    pq.enqueue(A(0))
    pq.enqueue(A(1))
    pq.enqueue(A(2))

    val initialProduct = A(0)*A(1)*A(2)*1l
    val arr = Array.fill(A.length)(-1l)
    arr(0) = -1
    arr(1) = -1
    arr(2) = initialProduct

    A.drop(3).foldLeft(3){case (idx,ele) =>
      val min = pq.head
      println(s"min $min")

      if(ele > min){
        pq.dequeue()
        pq.enqueue(ele)
      }
      val max = getProductOfThree(pq)
      arr(idx) = max
      idx+1
    }

    arr.map(_.toInt)
  }
}

solve(Array(1, 2, 3,4,5))