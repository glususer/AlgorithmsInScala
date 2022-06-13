import scala.collection.mutable
//Given an array of integers A and an integer B, find and return the number of pairs in A whose sum is divisible by B.
//
//Since the answer may be large, return the answer modulo (10^9 + 7).
def foldMap(pairs : mutable.Map[Int,Int], acc:Int=0, B:Int):Int={
  (0 until pairs.size).foldLeft(0){case(acc, _) =>
  if(pairs.isEmpty)acc
    else{
    val (num1, remainder1) = pairs.head
    val num2 = B-num1
    val remainder2 = pairs.getOrElse(B-num1,0)

    if(num1 != num2 && num1!=0) {
      pairs.remove(num1)
      pairs.remove(num2)
      (acc+remainder1*remainder2)%(math.pow(10,9).toInt+7)
    } else {
      pairs.remove(num1)
      (acc+(remainder1*(remainder1-1))/2)%(math.pow(10,9).toInt+7)
    }

  }
  }
}

def iterateMap(pairs : mutable.Map[Int,Int], acc:Int, B:Int):Int={
  println(s"acc $acc")
  if(pairs.isEmpty) acc
  else{
    val (num1, remainder1) = pairs.head
    val num2 = B-num1
    val remainder2 = pairs.getOrElse(B-num1,0)

    if(num1 != num2 && num1!=0) {
      pairs.remove(num1)
      pairs.remove(num2)
      iterateMap(pairs, (acc+remainder1*remainder2)%(math.pow(10,9).toInt+7),B)
    } else {
      pairs.remove(num1)
      iterateMap(pairs, (acc+(remainder1*(remainder1-1))/2)%(math.pow(10,9).toInt+7), B)
    }
  }
}

def solve(A: Array[Int], B: Int): Int  = {
    val pairs = A.foldLeft(collection.mutable.Map[Int, Int]()) { case (modMap, ele) =>
      val remainder = ele % B
      modMap.update(remainder, modMap.getOrElse(remainder, 0) + 1)
      modMap
    }
    foldMap(pairs, 0, B)
}

solve((1 to 100000 ).toArray, 888)