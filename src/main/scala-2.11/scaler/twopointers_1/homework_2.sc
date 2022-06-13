//Given two sorted arrays of distinct integers, A and B, and an integer C, find and return the pair whose sum is closest to C and the pair
// has one element from each array.
//
//More formally, find A[i] and B[j] such that abs((A[i] + B[j]) - C) has minimum value.
//
//If there are multiple solutions find the one with minimum i and even if there are multiple values of j for the same i then return the one with minimum j.
//
//Return an array with two elements {A[i], B[j]}.
def loopTailRecursive(A: Array[Int], B: Array[Int], C: Int, i: Int, j: Int, k: Int, res: Array[Int], globalMin: Int): Array[Int] = {

  if (i < A.length && j >= 0) {
    val abs = math.abs(A(i) + B(j) - C)
    val sum = A(i)+B(j)
    // println(s"i $i j $j k $k res ${res.toList} globalMin $globalMin abs $abs sum $sum")

    val (updatedI,updatedJ) = (if(sum<C)i+1 else i,if(sum >=C)j-1 else j )

    if (abs < globalMin) {
      res(1) = B(j)
      loopTailRecursive(A, B, C, updatedI, updatedJ, A(i), res, abs)
    }
    else if (abs == globalMin && A(i) == k) {
      res(1) = B(j)
      loopTailRecursive(A, B, C, updatedI, updatedJ, k, res, abs)
    }
    else loopTailRecursive(A, B, C,updatedI, updatedJ, k, res, globalMin)

  } else {
    res(0) = k
    res
  }
}

def makeThiswithWhileBecauseYouAintGotAnyScalDeveloper(A: Array[Int], B: Array[Int], C: Int):Array[Int]={
  var i = 0
  var j =B.length-1
  var k = A(0)
  var globalMin = Integer.MAX_VALUE
  val res = Array.fill(2)(0)
  while(i<A.length && j>=0){
    val abs = math.abs(A(i) + B(j) - C)
    val sum = A(i)+B(j)

    if(abs < globalMin) {
      res(1) = B(j)
      globalMin = abs
      k=A(i)
    }
    if(abs == globalMin && A(i) ==k) {
      res(1) = B(j)
    }

    if(sum < C) i = i+1
    else j=j-1
  }
  res(0)=k
  res
}


def solve(A: Array[Int], B: Array[Int], C: Int): Array[Int] = {
  // loopTailRecursive(A, B, C, 0, B.length-1, A(0), Array.fill(2)(0), Integer.MAX_VALUE)
  makeThiswithWhileBecauseYouAintGotAnyScalDeveloper(A,B,C)
}

solve(Array(1, 3, 4, 4), Array(2, 4, 5,8,10),10)