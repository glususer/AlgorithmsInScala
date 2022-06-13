//Given a matrix of integers A of size N x M in which each row is sorted.
//
//Find and return the overall median of matrix A.
//
//NOTE: No extra memory is allowed.
//
//NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.
//A = [   [1, 3, 5],
//        [2, 6, 9],
//        [3, 6, 9]   ]

def binarySearch(left: Int, right: Int, key: Int, array: Array[Int]): Int = {
  if (left < right) {
    val mid = (right - left) / 2 + left
    if (key < array(mid)) binarySearch(left, mid, key, array)
    else binarySearch(mid + 1, right, key, array)
  }
  else left
}
val arr1  =  Array(1,2,2,3,3,4,4,5)
binarySearch(0, arr1.length-1,2,arr1)
def getNoOfElementsLessThanAndEqualTo(arr: Array[Array[Int]], key: Int, row: Int, col: Int): Int = {
  val currentEle = key
  var currentRow  = 0
  var acc = 0
  while(currentRow< row){
    if (arr(currentRow)(0) <= currentEle && arr(currentRow)(col - 1) <= currentEle) {
      currentRow = currentRow+1
      acc = acc+col
    } else if (arr(currentRow)(0) <= currentEle) {
      val bs = binarySearch(0, col - 1, currentEle, arr(currentRow))
      currentRow = currentRow+1
      acc = acc+bs
    }
    else {
      currentRow = currentRow+1
    }
  }
  acc
}
def binarySearchMedian(arr: Array[Array[Int]], left: Int, right: Int, row: Int, col: Int, desiredCount: Int): Int = {
  if (left >= right) left
  else {
    val mid = ((right - left) / 2) + left
    val counter = getNoOfElementsLessThanAndEqualTo(arr, mid, row, col)

    if (counter < desiredCount) binarySearchMedian(arr, mid + 1, right, row, col, desiredCount)
    else binarySearchMedian(arr, left, mid, row, col, desiredCount)
  }
}
def findMedian(A: Array[Array[Int]]): Int = {
  val row = A.length
  val col = A(0).length
  val min = A.map(arr => arr.min).min
  val max = A.map(arr => arr.max).max
  val desiredCount = (1 + (row * col) / 2)
  val noOfElements = row * col
  val median1 = binarySearchMedian(A, min, max, row, col, desiredCount)
  lazy val median2 = binarySearchMedian(A, min, max, row, col, desiredCount - 1)

  if (noOfElements % 2 == 0) (median1 + median2) / 2
  else median1
}
val arr = Array(Array(1, 3, 5), Array(2, 6, 9), Array(3, 6, 9), Array(5, 6, 10), Array(6, 8, 11))
binarySearch(0,1,19,Array(-26))
//binarySearch(0,3,6,Array(2,6,9))
/*getNoOfElementsLessThanAndEqualTo(Array(Array(1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3)),2,1,11)

getNoOfElementsLessThanAndEqualTo(arr,2,5,3)
getNoOfElementsLessThanAndEqualTo(arr,3,5,3)
getNoOfElementsLessThanAndEqualTo(arr,4,5,3)
getNoOfElementsLessThanAndEqualTo(arr,5,5,3)
getNoOfElementsLessThanAndEqualTo(arr,6,5,3)
getNoOfElementsLessThanAndEqualTo(arr,7,5,3)
getNoOfElementsLessThanAndEqualTo(arr,8,5,3)
getNoOfElementsLessThanAndEqualTo(arr,9,5,3)
getNoOfElementsLessThanAndEqualTo(arr,10,5,3)
getNoOfElementsLessThanAndEqualTo(arr,11,5,3)*/
//println(x)
//findMedian(Array(Array(1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3)))
//println(getNoOfElementsLessThanAndEqualTo(arr,0,1,4,3,0,0,0 ))// == 2
//println(getNoOfElementsLessThanAndEqualTo(arr,0,2,4,3,0,0,0 ))// == 4
//getNoOfElementsLessThanAndEqualTo(arr,1,0,4,3,0,0,0 ) == 1
//getNoOfElementsLessThanAndEqualTo(arr,1,1,4,3,0,0,0 ) == 6
//getNoOfElementsLessThanAndEqualTo(arr,1,2,4,3,0,0,0 ) == 9
/*getNoOfElementsLessThanAndEqualTo(arr,2,0,4,3,0,0,0 ) == 2
getNoOfElementsLessThanAndEqualTo(arr,2,1,4,3,0,0,0 ) == 6
getNoOfElementsLessThanAndEqualTo(arr,2,2,4,3,0,0,0 ) == 9
getNoOfElementsLessThanAndEqualTo(arr,3,0,4,3,0,0,0 ) == 4
getNoOfElementsLessThanAndEqualTo(arr,3,1,4,3,0,0,0 ) == 6
getNoOfElementsLessThanAndEqualTo(arr,3,2,4,3,0,0,0 ) == 11*/
