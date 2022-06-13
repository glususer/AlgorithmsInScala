//Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in a 2-D Cartesian plane.
//
//Find and return the number of unordered quadruplet (i, j, k, l) such that (A[i], B[i]), (A[j], B[j]), (A[k], B[k]) and (A[l], B[l]) form a rectangle with the
// rectangle having all the sides parallel to either x-axis or y-axis.
case class Point(x:Int, y:Int)

def loopWithVars(points:Array[Point], pointSet:Set[Point]):Int={

  var count = 0
  for(i<- points.indices){
    for(j<- points.indices){
      val b = points(i)
      val d = points(j)
      val a = Point(d.x,b.y)
      val c = Point(b.x, d.y)
      if(b.x > d.x && b.y > d.y && pointSet.contains(a) && pointSet.contains(c)) count = count+1
    }
  }
  count
}

def solve(A: Array[Int], B: Array[Int]): Int  = {
  val points = A.zip(B).map{case (x,y)=> Point(x,y)}

  val pointSet :Set[Point]= points.toSet
  loopWithVars(points, pointSet)
}

solve(Array(1, 1, 2, 2, 3, 3), Array(1, 2, 1, 2, 1, 2))
