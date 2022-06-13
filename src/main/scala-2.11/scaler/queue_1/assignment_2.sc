//A CPU has N tasks to be performed. It is to be noted that the tasks have to be completed in a specific order to avoid deadlock.
// In every clock cycle, the CPU can either perform a task or move it to the back of the queue.
// You are given the current state of the scheduler queue in array A and the required order of the tasks in array B.
//
//Determine the minimum number of clock cycles to complete all the tasks.

//A = [2, 3, 1, 5, 4]
//B = [1, 3, 5, 4, 2]
def solve(A: Array[Int], B: Array[Int]): Int  = {
  import scala.collection.mutable
  val queue =  mutable.Queue[Int]()

  A.indices.foldLeft(queue){case(q,i) =>
  q.enqueue(A(i))
    q
  }

  B.indices.foldLeft(queue,0){case ((q,cpuCycles),i) =>
    var tempCycle = 0
    while (q.nonEmpty && q.head != B(i))
      {
        val current = q.dequeue()
        q.enqueue(current)
        tempCycle = tempCycle+1
      }
    q.dequeue()
    (q,cpuCycles+tempCycle+1)
  }._2

}

solve(Array(1), Array(1))