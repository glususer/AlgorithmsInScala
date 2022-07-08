//Given a sorted matrix of integers A of size N x M and an integer B.
//
//Each of the rows and columns of matrix A is sorted in ascending order, find the Bth smallest element in the matrix.
//
//NOTE: Return The Bth smallest element in the sorted order, not the Bth distinct element.
//def solve(self, A, B):
//        n = len(A)
//        m = len(A[0])
//        pq = []
//        for i in range(n):
//            heapq.heappush(pq, (A[i][0], i, 0))
//        res = None
//        while B > 0:
//            B -= 1
//            temp = heapq.heappop(pq)
//            res = temp[0]
//            r = temp[1]
//            c = temp[2]
//            if c + 1 < m:
//                c += 1
//                heapq.heappush(pq, (A[r][c], r, c))
//        return res
def solve(A: Array[Array[Int]], B: Int): Int  = {
  import scala.collection.mutable
  val pq  = mutable.PriorityQueue[Int]()(Ordering.Int).reverse
  A.flatten.foreach(ele => pq.enqueue(ele))
  (for{
    x<- 0 until  B
    ele = pq.dequeue()
  }yield ele).last
}