import scala.collection.mutable
//https://leetcode.com/problems/k-closest-points-to-origin/

def getDistanceFromOrigin(x: Int, y: Int):Double = {
  Math.sqrt(Math.pow(x,2)+Math.pow(y,2))
}

//case class numWithFreq(num:Int,freq:Int)
//val pq = mutable.PriorityQueue.empty[numWithFreq](Ordering.by(numWithFreq => numWithFreq.freq))
def kClosest(points: Array[Array[Int]], K: Int): Array[Array[Int]] = {

  case class PointWithDistance(num:(Int,Int),distanceFromOrigin:Double)

  val pq = mutable.PriorityQueue.empty[PointWithDistance](Ordering.by(numWithFreq=> numWithFreq.distanceFromOrigin)).reverse
  val numWithFreqSeq = points.map(point => PointWithDistance((point(0),point(1)),getDistanceFromOrigin(point(0),point(1)))).foreach(ele => pq.enqueue(ele))
  val smallestKPoints = (for{
    x <- 0 until K
    pair = pq.dequeue
  }yield pair
    ).map(pointWithDistance =>Array(pointWithDistance.num._1,pointWithDistance.num._2)).toArray
  smallestKPoints
}

kClosest(Array(Array(3,3),Array(5,-1),Array(-2,4)),2).foreach(point => println(s"${point(0)}, ${point(1)}"))

