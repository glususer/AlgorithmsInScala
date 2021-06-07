//https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
def minPairSum(nums: Array[Int]): Int = {
  val length = nums.length
  scala.util.Sorting.quickSort(nums)
    nums.zipWithIndex.foldLeft(Integer.MIN_VALUE) { case (globalMin, (ele, idx)) =>
    if (idx < length / 2) {
      val currentSum = ele + nums(length - 1 - idx)
      if (currentSum > globalMin) currentSum else globalMin
    }
    else globalMin
  }
}

  minPairSum(Array(3,5,2,3))

//https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/
//(a,b) => if(a._2 == b._2) a._1 <= b._1 else a._2 <= b._2
//Runtime: 2248 ms, faster than 50.00% of Scala online submissions for Minimum Initial Energy to Finish Tasks.
//Memory Usage: 265.2 MB, less than 50.00% of Scala online submissions for Minimum Initial Energy to Finish Tasks.

def canFinishInThisEnergy(tasks:Array[(Int,Int)], energy:Int):Boolean={
 val canFinish =  tasks.foldLeft(energy){case (acc, (actualEnergy, minEnergy)) =>
 //  println(s"minEnergy $minEnergy, actualEnergy $actualEnergy acc $acc")
    if(acc >= minEnergy) acc-actualEnergy else acc - minEnergy
  }
  println(s"energy is $energy and canFinish is $canFinish")
  canFinish>=0
}

def minimumEffortBS(tasks:Array[(Int,Int)], left:Int, right:Int):Int={
  if(left < right){
    val mid = left + (right-left)/2
    if(canFinishInThisEnergy(tasks,mid)) minimumEffortBS(tasks, left,mid)
    else(minimumEffortBS(tasks, mid+1, right))
  }else {
    if(canFinishInThisEnergy(tasks, left-1))left-1 else left
  }
}

def minimumEffort(tasks: Array[Array[Int]]): Int = {
  val tasksTuple = tasks.map(task => (task(0), task(task.length-1))).sortWith((a,b) => a._2 -a._1 > b._2-b._1)
  val (initialLeft, initialRight) = tasks.map(task => (task(0),task(task.length-1)))
    .foldLeft(0,0){case ((left,right),(ele1, ele2)) => (left+ele1, right+ele2)}
  minimumEffortBS(tasksTuple,initialLeft, initialRight )

}

//canFinishInThisEnergy(Array((4,8),(2,4),(1,2)),9)
//Array(Array(1,3),Array(2,4),Array(10,11),Array(10,12),Array(8,9))
//[1,7],[2,8],[3,9],[4,10],[5,11],[6,12]
//[[1,2],[1,7],[2,3],[5,9],[2,2]]
minimumEffort(Array(Array(1,2),Array(1,7),Array(2,3),Array(5,9),Array(2,2)))