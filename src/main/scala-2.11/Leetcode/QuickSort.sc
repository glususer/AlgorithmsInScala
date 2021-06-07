import scala.util.Random

def quickSelectLargest(seq:Array[Int],k:Int):Int={

  val pivot = Random.nextInt(seq.length);
  val (left, right) = seq.partition(_ < seq(pivot))
  println(s"left ${left.toList} right ${right.toList}, k $k pivot $pivot")
  if (right.length == k) {
    seq(pivot)
  } else if ( k < right.length) {
    quickSelectLargest(right, k)
  } else {
    quickSelectLargest(left, k-right.length)
  }
}
//quickSelectLargest(Array(5,8,9,2,1,10,3,4),1)
quickSelectLargest(Array(5,8,9,2,1,10,3,4),4)
/*quickSelectLargest(Array(5,8,9,2,1,10,3,4),3)
quickSelectLargest(Array(5,8,9,2,1,10,3,4),4)
quickSelectLargest(Array(5,8,9,2,1,10,3,4),5)
quickSelectLargest(Array(5,8,9,2,1,10,3,4),6)
quickSelectLargest(Array(5,8,9,2,1,10,3,4),7)
quickSelectLargest(Array(5,8,9,2,1,10,3,4),8)*/

