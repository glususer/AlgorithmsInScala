import scala.collection.mutable
//You are given two strings, A and B, of size N and M, respectively.
//
//You have to find the count of all permutations of A present in B as a substring. You can assume a string will have only lowercase letters.

def containsAllLetters(freqA:Map[Char, Int], freqB:mutable.HashMap[Char, Int]):Boolean={
  freqA == freqB
}
def solve(A: String, B: String): Int  = {
  val freqA = A.groupBy(identity).mapValues(_.length)

  val intialValueMap: mutable.HashMap[Char, Int] = B.take(A.length).foldLeft(mutable.HashMap[Char, Int]()){case (freqMap, ele) =>
  freqMap.update(ele,freqMap.getOrElse(ele,0)+1 )
  freqMap}

  val initialValueCount = if(containsAllLetters(freqA, intialValueMap)) 1 else 0

  B.indices.tail.foldLeft(intialValueMap,initialValueCount){case ((currentFreq, currentCount),idx) =>
    if(idx < B.length+1-A.length) {
      val toBeUpdatedKeyPrev = B(idx - 1)
      val toBeDecrValue = currentFreq.getOrElse(toBeUpdatedKeyPrev, 0) - 1

      if(toBeDecrValue == 0) currentFreq.remove(toBeUpdatedKeyPrev) else currentFreq.update(toBeUpdatedKeyPrev, toBeDecrValue)

      val toBeUpdatedCurrKey = B(idx + A.length - 1)
      val toBeIncrValue = currentFreq.getOrElse(toBeUpdatedCurrKey, 0) + 1
      currentFreq.update(toBeUpdatedCurrKey, toBeIncrValue)
     // println(s"freqA ${freqA} currentFreq ${currentFreq} idx $idx currentCount $currentCount shouldBeIncr ${containsAllLetters(freqA, currentFreq)}")
      if (containsAllLetters(freqA, currentFreq)) (currentFreq, currentCount + 1) else (currentFreq, currentCount)
    }else (currentFreq, currentCount)
  }._2
}

solve("p","pccdpeeooadeocdoacddapacaecb")