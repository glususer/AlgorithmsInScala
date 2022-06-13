//https://www.geeksforgeeks.org/lexicographic-rank-string-duplicate-characters/
//Given a string A, find the rank of the string amongst its permutations sorted lexicographically.
// Note that the characters might be repeated.
// If the characters are repeated, we need to look at the rank in unique permutations. Look at the example for more details.
//The answer might not fit in an integer, so return your answer % 1000003 where 1000003 is a prime number.
//String A can consist of both lowercase and uppercase letters. Characters with lesser ascii value are considered smaller i.e. 'a' > 'Z'.

def calcFac(n:Int, acc:Int=1, mod:Int):Int={
  if(n==0) acc
  else calcFac(n-1, (acc*n)%mod,mod)
}

def findRank(A: String): Int  = {
  println(s"len ${A.length}")
  val charToSmallerCharMap = A.zipWithIndex.map { case(x,idx) =>
    //println(s"ele $x smaller ${A.drop(idx).partition(_ < x)._1.toList} ")
    A.drop(idx).partition(_ < x)._1.toList}

  val charsToRepeatedMap = A.zipWithIndex.map { case (x, idx) =>
    (x, A.drop(idx).groupBy(identity).mapValues(_.length).filter(_._2  >1))}

  println(s"charToSmallerCharMap $charToSmallerCharMap")
  A.zipWithIndex.foldLeft(0){case (acc,(ele, idx)) =>
    val modPrime = 1000003

    val noOfSmallerCharsOnRight = charToSmallerCharMap(idx).length
  val noOfRepeatedCharsOnRight =  charsToRepeatedMap(idx)._2.values

    val numerator = (noOfSmallerCharsOnRight*calcFac(A.length-1-idx,1,modPrime))

    val denominator = noOfRepeatedCharsOnRight.map(n => calcFac(n,1,modPrime)).foldLeft(1)
    {case (product, ele) => product*ele}

    println(s"ele $ele noOfSmallerCharsOnRight $noOfSmallerCharsOnRight, numOfRepeatedChars $noOfRepeatedCharsOnRight acc $acc num $numerator den $denominator num%den ${numerator%denominator}")

    acc+(numerator/denominator)
  }+1
}

/*val s = "settLe"
"settLe".map { x => (x, s.partition(_ < x)._1.toList) }.toMap
s.zipWithIndex.map { case (x, idx) => println(s"${s.drop(idx)}")
  (x, s.drop(idx).groupBy(identity).mapValues(_.length).filter(_._2  >1))}*/
findRank("sadasdsasassasas")


val x = calcFac(15,1,1000003)*8
val y  = (List(8,2,6).map(n => calcFac(n,1,1000003)).product)%1000003

x%y
x/y
14*13*11*10*9

