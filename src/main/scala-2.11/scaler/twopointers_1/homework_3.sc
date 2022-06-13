import scala.collection.mutable
//Misha likes finding all Subarrays of an Array. Now she gives you an array A of N elements and told you to find the number of subarrays of A,
// that have unique elements.
//
//Since the number of subarrays could be large, return value % 109 +7.


def loopTailRecursive(A: Array[Int], i: Int, j: Int, currentSet: mutable.HashMap[Int, Int], count: Long, numBetweenDuplicateNos: Int): Long = {
  if (i <= j && j < A.length) {
    if (currentSet.contains(A(j))) {
      println(s"len ${currentSet.size} count $count numBetweenDuplicateNos $numBetweenDuplicateNos")
      val currentCount = (((currentSet.size * 1l * (currentSet.size + 1) * 1l) / 2) + (numBetweenDuplicateNos * currentSet.size)) % 1000000007
      loopTailRecursive(A, j, j + 1, mutable.HashMap(A(j) -> j), count + currentCount, j - currentSet(A(j)) - 1)
    }
    else loopTailRecursive(A, i, j + 1, currentSet += (A(j) -> j), count, numBetweenDuplicateNos)
  } else {
    println(s"len ${currentSet.size} count $count  numBetweenDuplicateNos $numBetweenDuplicateNos" +
      s"")
    val currentCount = ((currentSet.size * 1l * (currentSet.size + 1) * 1l) / 2) + (numBetweenDuplicateNos * currentSet.size) % 1000000007
    count + currentCount
  }
}

def someThingIsMessedUp(A: Array[Int]): Int = {
  var currentSet = mutable.Set.empty[Int]
  var i = 0
  var j = 0
  var count = 0l
  var prev = Integer.MAX_VALUE
  var currentCount = 0l
  var mod = 1000000007

  while (i <= j && j < A.length) {
    if (currentSet.contains(A(j))) {
      currentCount = (currentSet.size * 1l * (currentSet.size + 1) * 1l) % mod / 2 - (if (prev != A(j)) 1 else 0)
      count = count + currentCount
      currentSet = mutable.Set(A(j), prev)
      i = i + 1
      j = j + 1
      // println(s"i $i j $j")

    }
    else {
      currentSet = currentSet + A(j)
      prev = A(j)
      j = j + 1
    }
  }
  currentCount = ((currentSet.size * 1l * (currentSet.size + 1) * 1l) % mod / 2)
  (count + currentCount).toInt
}

def solve(A: Array[Int]): Int = {
  //someThingIsMessedUp(A)
  loopTailRecursive(A, 0, 0, mutable.HashMap.empty, 0l, 0).toInt
}
val arr1 = Array(19,30,70, 19, 20, 78, 30)

arr1.length
arr1.mkString(" ")
solve(arr1)