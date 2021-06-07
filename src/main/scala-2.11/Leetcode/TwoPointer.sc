//https://leetcode.com/problems/binary-subarrays-with-sum/
def numSubarraysWithSum(A: Array[Int], S: Int): Int = {
var left,right, count, currentSum = 0

  while(right < A.length) {
    println(s"left $left right $right count $count")
    currentSum = currentSum+A(right)
    while(left <= right && currentSum >S){
      currentSum = currentSum-A(left)
      left = left+1
    }
    while(A(left) == 0 && left <= right){
      count = count+1
      left = left+1
    }
    if(currentSum == S) count = count+1
    right = right+1
  }

  count
}

//numSubarraysWithSum(Array(0,0),0)

/*smaller = [[0,2],[5,10],[13,23],[24,25]],
longer = [[1,5],[8,12],[15,24],[25,26]]
output = [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]*/
case class Interval(start: Int, end: Int)
def sortIntervals(a:Interval, b:Interval):(Interval, Interval)={
  if((a.end <= b.start) || (a.start < b.end && b.end < a.end))(a,b) else (b,a)
}
def isDisjoint(a:Interval, b:Interval):Boolean={
  val (first, second) = sortIntervals(a,b)
  first.end < second.start
}
def merge(a:Interval, b:Interval):Option[Interval]={
  val(smaller, greater) = sortIntervals(a,b)
  if(isDisjoint(smaller, greater)) None
  else {
    val sorted = Array(smaller.start, smaller.end, greater.start, greater.end).sorted
    Some(Interval(sorted(1), sorted(2)))
  }
}
def helperIntervalIntersection(first:List[Interval], second:List[Interval], acc:List[Option[Interval]]):List[Option[Interval]]={
  (first, second) match{
    case (Nil,Nil) => acc
    case(x::xs, Nil)   => acc
    case (Nil, y::ys) => acc
    case (x::Nil, y::Nil) =>   acc:+merge(x,y)
    case (x::Nil, y::ys::yss) =>  val merged = merge(x,y)
      if(isDisjoint(x, ys)) acc:+merged
      else helperIntervalIntersection(x::Nil,ys::yss,  acc:+merged )
    case (x::xs::xss, y::Nil) =>  val merged = merge(x,y)
      if(isDisjoint(xs, y)) acc:+merged
      else helperIntervalIntersection(xs::xss,y::Nil,  acc:+merged )

    case (x::xs::xss, y::ys::yss) => val merged = merge(x,y)
    if(isDisjoint(xs,y) && isDisjoint(x,ys))(helperIntervalIntersection(xs::xss, ys::yss, acc:+merged))
    else if(isDisjoint(xs,y) && !isDisjoint(x,ys))   (helperIntervalIntersection(x::xs::xss, ys::yss, acc:+merged))
    else  (helperIntervalIntersection(xs::xss, y::ys::yss, acc:+merged))
  }
}
def intervalIntersection(firstList: Array[Array[Int]], secondList: Array[Array[Int]]): Array[Array[Int]] = {
  if(firstList.isEmpty || secondList.isEmpty) Array.empty
  else {
    helperIntervalIntersection(firstList.map(arr => Interval(arr.head, arr.last)).toList,
      secondList.map(arr => Interval(arr.head, arr.last)).toList,
      List.empty).flatten.map(interval => Array(interval.start, interval.end)).toArray
  }
}

def helper(smaller: Array[Array[Int]], longer: Array[Array[Int]], acc:Array[Array[Int]],i:Int, j:Int):Array[Array[Int]]={
  if(i<smaller.length && j < longer.length){
    if(smaller(i).last < longer(j).head) helper(smaller, longer, acc, i+1,j)
    else if(longer(j).last < smaller(i).head) helper(smaller, longer, acc, i, j+1)
    else{
      val sorted = Array(longer(j).head, longer(j).last, smaller(i).head, smaller(i).last).sorted
      if(smaller(i).last < longer(j).last) helper(smaller, longer, acc:+Array(sorted(1), sorted(2)) ,i+1,j)
      else helper(smaller, longer, acc:+Array(sorted(1), sorted(2)) ,i,j+1)
    }
  }else acc
}

def intervalIntersection1(firstList: Array[Array[Int]], secondList: Array[Array[Int]]): Array[Array[Int]] = {
  val (smaller, longer) = if(firstList.length < secondList.length)(firstList, secondList)
  else if (secondList.length > firstList.length)(secondList, firstList)
  else (firstList, secondList)

  helper(smaller, longer, Array.empty, 0,0)
}
val smaller = Array(Array(0,2),Array(5,10),Array(13,23),Array(24,25))
val longer =   Array(Array(1,5),Array(8,12),Array(15,24),Array(25,26))

/*val first = Array(Array(17, 20))
val second = Array(Array(2,3),Array(19,20))*/
intervalIntersection1(smaller, longer).map(arr => arr.toList)
intervalIntersection(smaller, longer).map(arr => arr.toList)


