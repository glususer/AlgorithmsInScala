import scala.collection.mutable
//Given two arrays of integers A and B describing a pair of (A[i], B[i]) coordinates in a 2D plane.
// A[i] describe x coordinates of the ith point in the 2D plane, whereas B[i] describes the y-coordinate of the ith point in the 2D plane.
//
//Find and return the maximum number of points that lie on the same line.
//https://leetcode.com/problems/max-points-on-a-line/discuss/47124/C%2B%2B-slope-counter
def calcGCD(a:Int, b:Int):Int={
  if(b == 0) a
  else calcGCD(b,a%b)
}

def solve(A: Array[Int], B: Array[Int]): Int  = {
  case class Point(x:Int, y:Int)
  val points = A.zip(B).map{case(x,y) => Point(x,y)}
  var ans = 0
  for(i<- A.indices){
    val counter = mutable.HashMap[(Int,Int), Int]()
    var duplicates = 1
    for(j<- i+1 until A.length){
        if(points(j)== points(i)) duplicates = duplicates+1
        else{
          val dx = points(i).x - points(j).x
          val dy = points(i).y - points(j).y
          val gcd = calcGCD(dy, dx)
          val key = (dx/gcd,dy/gcd)
          counter.update(key,counter.getOrElse(key,0)+1)
        }
    }
    ans = ans max duplicates
  //  println(s"counter $counter i $i duplicates $duplicates ans $ans")
    ans = if(counter.nonEmpty) counter.maxBy(_._2)._2+duplicates max ans else ans
  }
  ans
}
//{1_-1=3, 2_3=1, -8_1=1, -2_3=1}12
solve(Array(6, -5, 3, -6, 3, -9, -8, -7), Array(5, 0, -8, 1, -1, 6, 3, -3))