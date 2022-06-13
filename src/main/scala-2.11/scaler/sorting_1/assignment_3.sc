import scala.annotation.tailrec
//Given an array of integers A. If i < j and A[i] > A[j], then the pair (i, j) is called an inversion of A.
// Find the total number of inversions of A modulo (109 + 7).

@tailrec
def merge(a:List[Int],b:List[Int],merged:List[Int]=List.empty,acc:Int=0):(List[Int],Int)={
  (a,b) match{
    case (x,y) if x.isEmpty => (merged++y,acc)
    case (x,y) if y.isEmpty => (merged++x, acc)
    case (x::xs, y::ys) => if(x<=y) merge(xs,y::ys,merged:+x, acc)
    else  merge(x::xs,ys,merged:+y, (acc+a.length)%(math.pow(10,9)+7).toInt)
  }
}

def sort(l:List[Int]):(List[Int],Int)={
  if(l.length >1){
    val (left,right) = l.splitAt(l.length/2)
    val (mergedList, count) = merge(sort(left)._1, sort(right)._1, List.empty,sort(left)._2+sort(right)._2)
    (mergedList, count)
  }else (l,0)
}

def solve(A: Array[Int]): Int  = {
  sort(A.toList)._2
}

//solve(Array(3,2,1))

