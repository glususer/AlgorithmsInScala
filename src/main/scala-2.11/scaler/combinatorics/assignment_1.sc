//Given three integers A, B, and C, where A represents n, B represents r, and C represents m, find and return the value of nCr % m where nCr % m = (n!/((n-r)!*r!))% m.
//
//x! means factorial of x i.e. x! = 1 * 2 * 3... * x.


def solve(A: Int, B: Int, C: Int): Int  = {
  val dpArr = Array.fill(A+1)(Array.fill(B+1)(0))
  for(i<- 0 to A){
    dpArr(i)(0) = 1
  }
  for(i<- 1 to A){
    for (j<- 1 to B){
      dpArr(i)(j)=(dpArr(i-1)(j)%C + dpArr(i-1)(j-1)%C)%C
    }
  }
 // dpArr.foreach(arr => println{arr.toList.mkString(", ")})
  dpArr(A)(B)%C
}

solve(5,2,13)