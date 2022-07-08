//Given two arrays, A and B of size N. A[i] represents the time by which you can buy the ith car without paying any money.
//
//B[i] represents the profit you can earn by buying the ith car. It takes 1 minute to buy a car, so you can only buy the ith car when the current time <= A[i] - 1.
//
//Your task is to find the maximum profit one can earn by buying cars considering that you can only buy one car at a time.
def solve(A: Array[Int], B: Array[Int]): Int  = {
  import scala.collection.mutable
  case class Car(time:Int, profit:Int)

  val pq = mutable.PriorityQueue.empty(Ordering.Int).reverse
  val cars = A.zip(B).map(x => Car(x._1, x._2)).sortBy(_.time)
  pq.enqueue(cars.head.profit)
  val mod = 1000000007

  cars.tail.foldLeft(cars.head.profit,1){case ((totalProfit, currentTime), car) =>

    if(currentTime < car.time){
      pq.enqueue(car.profit)
      ((totalProfit%mod+car.profit%mod)%mod, currentTime+1)
    }
    else{
      if(car.profit > pq.head) {
        val profitToBeLetGo = pq.dequeue()
        pq.enqueue(car.profit)
        (totalProfit%mod-profitToBeLetGo%mod+car.profit%mod, currentTime)
      }
      else (totalProfit, currentTime)
    }
  }._1
}

solve(Array(1, 7, 6, 2, 8, 4, 4, 6, 8, 2),Array(8, 11, 7, 7, 10, 8, 7, 5, 4, 9))
