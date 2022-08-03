
def helper(catalanArr:Array[Int], n:Int,A:Int):Unit={
  //println(s"catalan ${catalanArr.toList}")
  if(n <= A){
    catalanArr(n) = (0 until n).map(i => catalanArr(i)*catalanArr(n-1-i)).sum
    helper(catalanArr, n+1,A)
  }
}
def numTrees(A: Int): Int  = {
  if(A==0 || A==1) 1
  else {
    val catalanArr = Array.fill(A + 1)(0)
    catalanArr(0) = 1
    catalanArr(1) = 1
    helper(catalanArr, 2,A)
    catalanArr(A)
  }
}

numTrees(3)