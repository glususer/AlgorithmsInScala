//Given an array of integers A.
//
//value of a array = max(array) - min(array).
//
//Calculate and return the sum of values of all possible subarrays of A modulo 109+7.

def nearestOnLeft(A: Array[Int])(fn: (Int, Int) => Boolean): Array[Int] = {
  import scala.collection.mutable
  val smallerArray = Array.fill(A.length)(-1)
  val stack = mutable.Stack[Int]()
  stack.push(0)

  A.indices.tail.foldLeft(stack) { case (stck, idx) =>
    while (stck.nonEmpty && fn(A(stck.top), A(idx)))
      stck.pop()
    if (stck.nonEmpty) smallerArray(idx) = stck.top
    stck.push(idx)
    stck
  }
  smallerArray
}

def nearestOnRight(A: Array[Int])(fn: (Int, Int) => Boolean): Array[Int] = {
  import scala.collection.mutable
  val smallerArray = Array.fill(A.length)(A.length)
  val stack = mutable.Stack[Int]()
  stack.push(A.length - 1)

  A.indices.reverse.tail.foldLeft(stack) { case (stck, idx) =>
    while (stck.nonEmpty && fn(A(stck.top), A(idx)))
      stck.pop()
    if (stck.nonEmpty) smallerArray(idx) = stck.top
    stck.push(idx)
    stck
  }
  smallerArray
}
def solve(A: Array[Int]): Int = {
  val nearestSmallerOnLeft = nearestOnLeft(A)((x: Int, y: Int) => x >= y)
  val nearestGreaterOnLeft = nearestOnLeft(A)((x: Int, y: Int) => x <= y)

  val nearestSmallerOnRight = nearestOnRight(A)((x: Int, y: Int) => x >= y)
  val nearestGreaterOnRight = nearestOnRight(A)((x: Int, y: Int) => x <= y)
  val mod = (1000 * 1000 * 1000) + 7

  /* println(s"nearestSmallerOnLeft ${nearestSmallerOnLeft.toList}")
   println(s"nearestGreaterOnLeft ${nearestGreaterOnLeft.toList}")
   println(s"nearestSmallerOnRight ${nearestSmallerOnRight.toList}")
   println(s"nearestGreaterOnRight ${nearestGreaterOnRight.toList}")*/
  val contribution: Long = A.indices.map { i =>

    val smallerLeft = nearestSmallerOnLeft(i)
    val smallerRight = nearestSmallerOnRight(i)
    val greaterLeft = nearestGreaterOnLeft(i)
    val greaterRight = nearestGreaterOnRight(i)

    val prodMin = (1l * (i - smallerLeft) * (smallerRight - i)) % mod
    val minContribution = (prodMin * A(i)) % mod

    val prodMax = (1l * (i - greaterLeft) * (greaterRight - i)) % mod
    val maxContribution = (prodMax * A(i)) % mod


    // println(s" A(i) ${A(i)} maxContribution ${maxContribution} minContribution ${minContribution}")
    (maxContribution - minContribution) % mod
  }.foldLeft(0L) { case (acc, ele) =>
    (acc + ele) % mod
  }
  // println(s"contribution $contribution")


  if (contribution < 0) ((contribution + mod) % mod).toInt
  else (contribution % mod).toInt
}
//prevSmall[-1, 0, -1, 2]
//nextSmall[2, 2, 4, 4]
//prevGreater[-1, -1, 1, -1]
//nextGreater[1, 3, 3, 4]
//A(i)4 maxContribution4 minContribution 8
//A(i)7 maxContribution28 minContribution 7
//A(i)3 maxContribution3 minContribution 18
//A(i)8 maxContribution32 minContribution 8
//26
solve(Array(4, 7, 3, 8))