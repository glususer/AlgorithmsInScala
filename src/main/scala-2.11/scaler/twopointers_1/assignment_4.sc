//Given a sorted array of integers (not necessarily distinct) A and an integer B,
// find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.
//
//Since the number of such pairs can be very large, return number of such pairs modulo (109 + 7).
def loopTailRecursive(A:Array[Int], B:Int, count:Long, i:Int, j:Int, eleFreqMap:Map[Int,Int],x:Int, y:Int):Long={
  //println(s"count-> $count i->$i j->$j x->$x A(i)->${A(i)} y->$y  A(j)->${A(j)}")
  if(i<j && i<A.length && j >=0){
    val currentSum = A(i)+A(j)
    if(currentSum == B){
      val (freq1:Int, freq2:Int) = {
        if (A(i) == A(j)){
          val len = eleFreqMap.getOrElse(A(i), 1)*1l
          val product = (((len*1l*(len-1)*1l)/2)%1000000007).toInt
          (product,1)
        }
        else (eleFreqMap.getOrElse(A(i), 1),eleFreqMap.getOrElse(A(j), 1))
      }
      if (x == A(i) && y == A(j)) loopTailRecursive(A, B, count, i + 1, j - 1, eleFreqMap, x, y)
      else loopTailRecursive(A, B, (count + (freq1 * freq2))%1000000007, i + 1, j - 1, eleFreqMap, A(i), A(j))

    }
    else if(currentSum < B)loopTailRecursive(A, B, count, i+1, j, eleFreqMap,A(i),A(j))
    else loopTailRecursive(A, B, count, i, j-1, eleFreqMap,A(i),A(j))
  }else count
}
// here is the java like code with vars, same logic as above but not tailrecursive.
def likeSeriouslyYourPlatFormSucksForScala(A:Array[Int], B:Int,eleFreqMap:Map[Int,Int]):Int={
  var i =0
  var j = A.length-1
  var count = 0l
  var x = Integer.MAX_VALUE
  var y = Integer.MAX_VALUE

  while(i<j && i<A.length && j >=0){
    val currentSum = A(i)+A(j)
    if(currentSum  == B){
      val (freq1:Int, freq2:Int) = {
        if (A(i) == A(j)) {
          val len = eleFreqMap.getOrElse(A(i), 1)*1l
          val product = (((len*1l*(len-1)*1l)/2)%1000000007).toInt
          (product,1)
        }
        else (eleFreqMap.getOrElse(A(i), 1),eleFreqMap.getOrElse(A(j), 1))
      }
      if (x == A(i) && y == A(j)) {
        i=i+1
        j=j-1
      }
      else {
        count = count+(freq1 * freq2)
        x = A(i)
        y = A(j)
        i=i+1
        j=j-1
      }
    }
    else if(currentSum < B) {
      x = A(i)
      y = A(j)
      i = i+1
    }
    else {
      x = A(i)
      y = A(j)
      j = j-1
    }
  }
  count.toInt
}


def solve(A: Array[Int], B: Int): Int  = {
  val eleFreqMap = A.groupBy(identity).mapValues(_.length)
   likeSeriouslyYourPlatFormSucksForScala(A,B, eleFreqMap)
}

solve(Array(1, 2, 6, 6, 7, 9, 9),13)
((100000l*(100000-1)*1l)/2)%1000000007



