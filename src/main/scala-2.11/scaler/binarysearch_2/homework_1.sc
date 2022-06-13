//Given an integer A representing the number of square blocks. The height of each square block is 1. The task is to create a staircase of max-height using these blocks.
//
//The first stair would require only one block, and the second stair would require two blocks, and so on.
//
//Find and return the maximum height of the staircase.

def binarySearchForN(left:Long, right:Long,key:Int):Long={
  if(left<right){
    val mid =((right-left)/2)+left
    val sumOfNaturalNosTillMid = (mid*(mid+1))/2
    println(s"left $left right $right sumOfNaturalNosTillMid $sumOfNaturalNosTillMid mid $mid")
    if(sumOfNaturalNosTillMid > key)binarySearchForN(left, mid, key)
    else binarySearchForN(mid+1, right, key)
  }else left-1
}
def solve(A: Int): Int  = {
  A match{
    case 0 => 0
    case 1 => 1
    case _ => binarySearchForN(0,A,A).toInt
  }
}

solve(92761)
