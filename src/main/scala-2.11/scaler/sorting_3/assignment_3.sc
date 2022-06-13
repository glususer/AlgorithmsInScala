import scala.collection.mutable

//Given an array A of non-negative integers, arrange them such that they form the largest number.
//
//Note: The result may be very large, so you need to return a string instead of an integer.
// HINT : Use count sort
def largestNumber(A: Array[Int]): String  = {
  /*val max = A.max
  val min = A.min
  val numberVsFrequency = A.foldLeft(mutable.HashMap[Int,Int]()){case(hashMap, ele) =>
    val existingKey = hashMap.getOrElse(ele,0)
    hashMap.update(ele, existingKey+1)
    hashMap
  }

  (min to max).foldLeft(""){case (acc, num) =>
  if(numberVsFrequency.contains(num)) acc+{(0 until numberVsFrequency.getOrElse(num,0)).map(i => num).mkString("")}
  else acc
  }*/
  if(A.forall(x => x==0)) "0"
else {
  A.map(_.toString).sortWith { case (s1, s2) => println(s"s1 $s1 s2 $s2 s2+s1 ${((s2+s1).toLong)} s1+s2 ${(s1+s2).toLong} ${(s2+s1) < (s1+s2)} ")
    (s2 + s1) <= (s1 + s2)
  }.mkString("")
}
}

largestNumber(Array(3, 30, 34, 5, 9))

Array(3,1,2,9).sortWith{case (s1,s2) => s1>s2}

