//https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/
def positionByBS(key:Int, arr:Array[Int], left:Int, right:Int):Int={
  if(left < right){
    val mid = left + (right-left)/2
    if(key <= arr(mid))positionByBS(key,arr,left,mid)
    else positionByBS(key,arr,mid+1,right)
  } else left
}
def condition(key:Int, arr:Array[Int]):Int={
  val indexOfKey = positionByBS(key,arr,0,arr.length-1)
  val sumBySubstitutingKey = arr.zipWithIndex.scanLeft(0){case(acc, ele) => if(ele._2 < indexOfKey) acc+ele._1
  else acc+key}
  //println(s"sumBySubstitutingKey is ${sumBySubstitutingKey.toList} and key is $key")
  sumBySubstitutingKey.last//
}
def findBestValueSearch(left:Int,right:Int, arr:Array[Int], target:Int):Int={
  if(left<right){
    val mid = left+(right-left)/2
  //  println(s"left is $left, right is $right and sum is ${condition(mid,arr)}")
    if(condition(mid,arr) > target) findBestValueSearch(left,mid, arr, target)
    else findBestValueSearch(mid+1, right,arr,target)
  }else {
  //  println(s"ans is $left")
    if(math.abs(condition(left-1, arr)-target) <= math.abs(condition(left,arr)-target))
      left-1
    else
    left
  }
}

def findBestValue(arr: Array[Int], target: Int): Int = {
  if(target >= arr.sum) arr.max
  else{
    findBestValueSearch(0,arr.max, arr.sorted, target)
  }
}

//positionByBS(8,Array(1,3,6),0,3)

//condition(3,Array(3,4,9),10)
//positionByBS(11361,Array(60864,25176,27249,21296,20204).sorted, 0,4 )
//condition(11361,Array(60864,25176,27249,21296,20204).sorted )-56803
//condition(9150,Array(60864,25176,27249,21296,20204).sorted) -56803
findBestValue(Array(60864,25176,27249,21296,20204),56803)

Array(1,-2,-3,5).foldLeft(1){case (acc, ele) => if(ele < 0) acc * -1
else if(ele > 0) acc * 1
else 0}
