//Given an integer, A. Find and Return first positive A integers in ascending order containing only digits 1, 2, and 3.
//
//NOTE: All the A integers will fit in 32-bit integers.

def solve(A: Int): Array[Int]  = {
  if(A== 1) Array(1)
  else if(A==2)Array(1,2)
  else if(A==3)Array(1,2,3)
  else {
    val result = Array.fill(A)(0)
    var start = 0
    var end = 3
    var count = 3
    result(0) = 1
    result(1) = 2
    result(2) = 3

    while (count < A) {
      val generatedElements: Array[Int] = result.slice(start,end).flatMap{ele => Array(ele  * 10 + 1, ele * 10 + 2, ele* 10 + 3)}
      var k = 0
      for (i <- count until count + generatedElements.length) {
        if (i < result.length) {
          result(i) = generatedElements(k)
          k = k + 1
        }
      }

      count = count + generatedElements.length
      start = end
      end = end+generatedElements.length
    }
    result
  }
}

solve(20)

