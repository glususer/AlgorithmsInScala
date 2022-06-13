//Given an array of integers A of size N where N is even.
//
//Divide the array into two subsets such that
//
//1.Length of both subset is equal.
//2.Each element of A occurs in exactly one of these subset.
//Magic number = sum of absolute difference of corresponding elements of subset.
//
//Note: You can reorder the position of elements within the subset to find the value of the magic number.
def solve(A: Array[Int]): Array[Int]  = {
  scala.util.Sorting.quickSort(A)
  val (arr1,arr2) = A.splitAt(A.length/2)
 // println(s"arr1 ${arr1.toList} arr2 ${arr2.toList}")
  val maxDiff = arr1.zip(arr2).map{case (num1, num2) => math.abs(num1-num2)}.foldLeft(0){case (acc, ele) =>
    (acc+ele)%1000000007}

  val(arr3,arr4) = A.zipWithIndex.partition(_._2%2==0)

  //println(s"arr3 ${arr3.toList} arr4 ${arr4.toList}")
  val minDiff = arr3.map(_._1).zip(arr4.map(_._1)).map{case (num1, num2) => math.abs(num1-num2)}.foldLeft(0){case (acc,ele) =>
    (acc+ele)%1000000007}
  Array(maxDiff, minDiff)
}

solve(Array(3, 11, -1, 5))

