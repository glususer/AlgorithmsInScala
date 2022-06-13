//Given an one-dimensional integer array A of size N and an integer B.
//
//Count all distinct pairs with difference equal to B.
//
//Here a pair is defined as an integer pair (x, y), where x and y are both numbers in the array and their absolute difference is B.
def loopTailRecursive(A:Array[Int],B:Int, x:Int,y:Int,count:Int, i:Int,j:Int):Int={
  //println(s"x $x, y $y count  $count i $i j $j")
  if(j>=A.length) count
  else if (i<j){
    val diff = math.abs(A(i)-A(j))
    if(diff == B) {
      if (x != A(i) && y != A(j)) loopTailRecursive(A, B, A(i), A(j), count + 1, i + 1, j + 1)
      else loopTailRecursive(A, B, A(i), A(j), count, i + 1, j + 1)
    }
    else if(diff < B) loopTailRecursive(A, B, x,y, count,i,j+1)
    else loopTailRecursive(A, B, x,y, count,i+1,j)
  }else loopTailRecursive(A, B, x, y, count, i, j + 1)
}

def loop(A:Array[Int],B:Int):Int={
  var i=0
  var j=1
  var x=Integer.MAX_VALUE
  var y = Integer.MAX_VALUE
  var count = 0

  while(j< A.length) {
    if (i < j) {
      val diff = math.abs(A(i) - A(j))
      if (diff == B) {
        if (x != A(i) && y != A(j)) {
          count = count + 1
          x = A(i)
          y = A(j)
        }
        i = i + 1
        j = j + 1
      }
      else if (diff < B) j = j + 1
      else i = i + 1
    }else j=j+1
  }
  count
}

def solve(A: Array[Int], B: Int): Int  = {
  scala.util.Sorting.quickSort(A)
 loop(A,B)
 // loopTailRecursive(A,B,Integer.MAX_VALUE, Integer.MAX_VALUE,0,0,1)
}

solve(Array(1,2),0)