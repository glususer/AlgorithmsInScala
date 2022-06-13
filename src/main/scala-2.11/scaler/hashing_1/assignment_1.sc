import scala.collection.mutable

//Shaggy has an array A consisting of N elements. We call a pair of distinct indices in that array a special if elements at those indices in the array are equal.
//
//Shaggy wants you to find a special pair such that the distance between that pair is minimum. Distance between two indices is defined as |i-j|.
// If there is no special pair in the array, then return -1.

case class Interval(start: Int, end: Option[Int])

def loop(A: Array[Int], eleVsIdx: mutable.HashMap[Int, Interval], i: Int, minDis: Int): Int = {
  if (i < A.length) {
    if (eleVsIdx.contains(A(i))) {
      eleVsIdx.get(A(i)) match {
        case Some(interval) => if (interval.end.isEmpty) {
          val currentDist = i - interval.start
          eleVsIdx.update(A(i), Interval(interval.start, Some(i)))
          loop(A, eleVsIdx, i + 1, minDis min currentDist)
        } else {
          val currentDist = i - interval.end.get
          val prevDist = interval.end.get - interval.start
          if (currentDist < prevDist) {
            eleVsIdx.update(A(i), Interval(interval.end.get, Some(i)))
            loop(A, eleVsIdx, i + 1, minDis min currentDist)
          } else loop(A, eleVsIdx, i + 1, minDis)

        }

        case None => loop(A, eleVsIdx += (A(i) -> Interval(i, None)), i + 1, minDis)
      }
    } else loop(A, eleVsIdx += (A(i) -> Interval(i, None)), i + 1, minDis)
  } else minDis
}

def loopWithVars (A: Array[Int]):Int={
  val eleVsIdx =  mutable.HashMap.empty[Int, Interval]
  var i = 0
  var minDis = Integer.MAX_VALUE

  while(i<A.length){
    if (eleVsIdx.contains(A(i))) {
      eleVsIdx.get(A(i)) match {
        case Some(interval) => if (interval.end.isEmpty) {
          val currentDist = i - interval.start
          eleVsIdx.update(A(i), Interval(interval.start, Some(i)))
          minDis = minDis min currentDist
          i=i+1
        } else {
          val currentDist = i - interval.end.get
          val prevDist = interval.end.get - interval.start
          if (currentDist < prevDist) {
            eleVsIdx.update(A(i), Interval(interval.end.get, Some(i)))
            minDis = minDis min currentDist
            i=i+1
          } else i=i+1

        }
        case None => {
          eleVsIdx += (A(i) -> Interval(i, None))
          i=i+1
        }
      }
    } else {
      eleVsIdx += (A(i) -> Interval(i, None))
      i=i+1
    }
  }
  minDis
}

def solve(A: Array[Int]): Int = {
  if(A.length==0 ||A.length==1) -1
  else {
   // val minValue = loop(A, mutable.HashMap.empty, 0, Integer.MAX_VALUE)
    val minValue = loopWithVars(A)
    if(minValue == Integer.MAX_VALUE) -1 else minValue
  }
}
solve(Array(1, 1))