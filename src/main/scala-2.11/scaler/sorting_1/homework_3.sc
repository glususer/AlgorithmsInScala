//Given an array of integers A, we call (i, j) an important reverse pair if i < j and A[i] > 2*A[j].
//Return the number of important reverse pairs in the given array A.
def merge(a:List[Int],b:List[Int],merged:List[Int]=List.empty,acc:Int=0):(List[Int],Int)={
  (a,b) match{
    case (x,y) if x.isEmpty => (merged++y,acc)
    case (x,y) if y.isEmpty => (merged++x, acc)
    case (x::xs, y::ys) =>  // println(s"x $x,  y $y")
      if(x<=y) merge(xs,y::ys,merged:+x, acc)
    else  {// if(x > 2*y) println(s"x $x is greater then y $y a $a b $b merged $merged")
        val (_, greaterByTwice) = a.span(num => num <= 2*y)
        if(greaterByTwice.nonEmpty) println(s"y $y greaterByTwice ${greaterByTwice} acc $acc")
      merge(x::xs,ys,merged:+y, acc+greaterByTwice.length)
    }
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
val (sortedArr, count) = sort(A.toList)
  println(s"sortedArr ${sortedArr.toList}")
  count
}

solve(Array(769, -71, 599, -1438, -530, -512, 1680, 1907, -301, 492, -844, 1765, -188, 685, -1879, -699, -990, 1022, 459, 528, -930, 1051, 1412, -1598, 245, -480, 1979, 1212, 1177, 447, -509, 881, 1968, -1603, -429, 1165, 405, 426, -1610, 931, -835, -1156, 1273, -143, -940, 199, -1886, -1646, 390, -1349, -256, -256, -103, -135, 637, -605, 55, -1805, -17, -229, 1162, 288, -818, -922, 32, -1032, -1823, -1874, -1650, 146, 721, 1586, 1969, 1720, -993, -1137, -1233, -1629, -879, -277, 444, -1191, -1273, 127, 1785, 1407, -1460, 414, -1578, -1348, 72, -794, 632, 877, 338, 1921, -650, -1314, 1187, -40))
//(5,1)(5,2)(9,1)(9,2)(9,3)
/*
merge(List(1,5,9), List(1,2,3))

val l = List(2,4,6,8)

l.span(num =>  num <= 2*3)*/

object Main {

  def main(args: Array[String]): Int = {
    val scanner = new java.util.Scanner(System.in)
    val line = scanner.nextLine().trim.toInt
    val testCase = scanner.nextLine().trim.toInt

    val sum =  (testCase *(testCase-1))/2
    sum
  }
}

