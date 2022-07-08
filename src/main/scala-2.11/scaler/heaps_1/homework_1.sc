//We have a list A of points (x, y) on the plane. Find the B closest points to the origin (0, 0).
//Here, the distance between two points on a plane is the Euclidean distance.
//You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.)
//NOTE: Euclidean distance between two points P1(x1, y1) and P2(x2, y2) is sqrt( (x1-x2)2 + (y1-y2)2 ).

def solve(A: Array[Array[Int]], B: Int): Array[Array[Int]]  = {
  import scala.collection.mutable
  case class Point(distance:Double, x:Int, y:Int)
  val pq = mutable.PriorityQueue.empty[Point](Ordering.by(point => point.distance)).reverse
  A.map{arr => val x = arr(0)
  val y = arr(1)
  val distanceFromOrigin = math.sqrt(math.pow(x,2)+math.pow(y,2))
    Point(distanceFromOrigin, x,y)
  }.foldLeft(pq){case (q, ele) => q.enqueue(ele)
  q}

  var i = 0
  val result = Array.fill(B)(Array.fill(2)(0))
  while(i < B){
    val current = pq.dequeue()
    result(i)(0) = current.x
    result(i)(1) = current.y
    i=i+1
  }
  result
}