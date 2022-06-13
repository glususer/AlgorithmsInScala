//Given a binary array A, find the maximum sequence of continuous 1's that can be formed by replacing at-most B zeroes.
//
//For this problem, return the indices of maximum continuous series of 1s in order.
//
//If there are multiple possible solutions, return the sequence which has the minimum start index.
def maxone(A: Array[Int], B: Int): Array[Int]  = {
  var i= 0
  var j=0
  var count = 0
  var maxI = 0
  var maxJ = 0
  var maxLength = 0

  while(j<A.length){
   // println(s" i $i j $j maxLen $maxLength maxI $maxI maxJ $maxJ count $count")
  if(A(j) == 0)
    count = count+1
  while(count > B){
    if(A(i) ==0) count = count-1
    i=i+1
  }
    val currentLength = j-i+1
    //println(s"i $i j $j currentLength $currentLength maxLength $maxLength")

    if(currentLength > maxLength){
      maxI = i
      maxJ = j
      maxLength = currentLength
    }
    j=j+1
  }

  (maxI to maxJ).toArray
}
val arr = Array(0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0)
arr.length
maxone(arr,12)
arr.zipWithIndex

