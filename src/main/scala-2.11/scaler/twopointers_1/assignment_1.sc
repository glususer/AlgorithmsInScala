//Given an array of positive integers A and an integer B, find and return first continuous subarray which adds to B.
//
//If the answer does not exist return an array with a single element "-1".
//
//First sub-array means the sub-array for which starting index in minimum.
def loop(k: Int, A: Array[Int], i: Int, j: Int, sum: Int): (Int, Int) = {
 //println(s"i $i j $j sum $sum")
  if (A.slice(i,j+1).sum == k) (i, j)
  else {
    if (j < A.length) {
      val currentSum = if(sum < k) sum + A(j) else sum-A(i)
      if (currentSum < k) loop(k, A, i, j + 1, currentSum)
      else loop(k, A, i + 1, j,currentSum)
    } else (-1, -1)
  }
}

def loop2(k: Int, A: Array[Int]):(Int,Int)={
var i = 0
var j=1
var sum = 0

while(j<A.length){
  if(A.slice(i,j+1).sum == k) return (i, j)
  else {
    sum = if(sum < k) sum + A(j) else sum-A(i)
    if (sum < k) j=j + 1
    else i=i + 1
  }
}
  (-1, -1)
}

def solve(A: Array[Int], B: Int): Array[Int] = {
 // val (a, b) = loop(B, A, 0, 1, 0)
  val (a,b) = loop2(B,A)
  if(a == -1)Array(-1) else (a to b).map(idx => A(idx)).toArray
}

solve(Array(1, 2, 3, 4, 5), 5)