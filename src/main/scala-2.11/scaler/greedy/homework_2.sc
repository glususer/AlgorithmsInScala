//The local ship renting service has a special rate plan:
//
//It is up to a passenger to choose a ship.
//If the chosen ship has X (X > 0) vacant places at the given moment, then the ticket for such a ship costs X.
//The passengers buy tickets in turn, the first person in the queue goes first, then the second one, and so on up to A-th person.
//
//You need to tell the maximum and the minimum money that the ship company can earn if all A passengers buy tickets.

def solve(A: Int, B: Int, C: Array[Int]): Array[Int]  = {
  import scala.collection.mutable
  val minPQ = mutable.PriorityQueue.empty(Ordering.Int).reverse
  val maxPQ = mutable.PriorityQueue.empty(Ordering.Int)

  var minMoney = 0
  var maxMoney = 0
  var persons = A

  C.foldLeft(minPQ){case(p, ele) => p.enqueue(ele)
  p}

  C.foldLeft(maxPQ){case(q, ele) => q.enqueue(ele)
  q}
  while(persons >0){
    val currentMax = maxPQ.dequeue()
    val currentMin = minPQ.dequeue()

    maxMoney = maxMoney+currentMax
    minMoney = minMoney+currentMin

   if(currentMax-1 > 0) maxPQ.enqueue(currentMax-1)
   if(currentMin-1 >0) minPQ.enqueue(currentMin-1)
    persons = persons-1

   // println(s"minMoney $minMoney minPQ ${minPQ} maxMoney $maxMoney maxPQ ${maxPQ}")
  }

  Array(maxMoney, minMoney)
}

solve(4,3,Array(2,2,2))