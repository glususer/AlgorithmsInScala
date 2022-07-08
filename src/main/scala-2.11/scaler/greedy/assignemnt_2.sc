//There are N jobs to be done, but you can do only one job at a time.
//
//Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.
//
//Your aim is to select jobs in such a way so that you can finish the maximum number of jobs.
//
//Return the maximum number of jobs you can finish.

def solve(A: Array[Int], B: Array[Int]): Int  = {
  val zipped = A.zip(B).sortBy(_._2)
    zipped.tail.foldLeft(1,zipped.head._2){case ((jobs, prevEndTime), (startTime, endTime)) =>
    if(prevEndTime <= startTime) (jobs+1, endTime)
    else (jobs, prevEndTime)
    }._1
}

solve(Array(1, 5, 7, 1), Array(7, 8, 8, 8))