//Given an integer A. Return minimum count of numbers, sum of whose squares is equal to A.

def countMinSquares(A: Int): Int  = {
  val cnt = Array.fill(A+1)(0)
  for(i<- 1 to A){
    cnt(i) = i
    var x = 1
    while(x*x <= i){
      //println(s"${1+cnt(i-x*x)}  cnt(i) ${ cnt(i)}")
      cnt(i) = cnt(i) min (1+cnt(i-x*x))
      x = x+1
    }
  }
  println(s"arr ${cnt.toList}")
  cnt(A)
}

countMinSquares(13)
countMinSquares(1)
countMinSquares(2)
countMinSquares(3)
countMinSquares(4)
countMinSquares(5)
countMinSquares(6)