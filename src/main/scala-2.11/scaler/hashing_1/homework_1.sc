//Given two arrays of integers A and B, Sort A in such a way that the relative order among the elements will be the same as those are in B.
//For the elements not present in B, append them at last in sorted order.
//
//Return the array A after sorting from the above method.
//
//NOTE: Elements of B are unique.

def solve(A: Array[Int], B: Array[Int]): Array[Int]  = {
  import scala.collection.mutable.ListBuffer
  var result = ListBuffer.empty[Int]
  val intersection = A.intersect(B).toSet
  val toBeAppended = A.filterNot(num => intersection.contains(num)).sorted
  val numVsFreq = A.groupBy(identity).mapValues(_.length)
 // println(s"numVsFreq $numVsFreq intersection $intersection")

  for(i<-B.indices){
    if(numVsFreq.contains(B(i))) {
      val freq = numVsFreq.getOrElse(B(i),0)
      val temp = ListBuffer.fill(freq)(B(i))
     // println(s"temp $temp")
      result = result++ListBuffer.fill(freq)(B(i))
    }
  }
  (result++toBeAppended).toArray

}

solve(Array(18, 2, 10, 16, 16, 16 ), Array(3, 13,16,2, 4, 19))