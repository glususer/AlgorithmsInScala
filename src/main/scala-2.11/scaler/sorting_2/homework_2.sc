def hasSum(min:Int, max:Int, array:Array[Int]):Boolean={
  (min to max).sum == array.sum
}
 def hasElements(min:Int, max:Int, array: Array[Int]):Boolean={
   (min to max).length == array.length
 }
def solve(A: Array[Int]): Int  = {
  val min = A.min
  val max = A.max
  if(hasSum(min, max,A) && hasElements(min, max, A)) 1 else 0
}

solve(Array(1, 1, 1, 5, 5))

Array(1, 1, 1, 5, 5).sum
(1 to 5).sum