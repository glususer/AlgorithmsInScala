def helper(catalanArr:Array[Int], n:Int,A:Int,mod:Int):Unit={
  //println(s"catalan ${catalanArr.toList}")
  if(n <= A){
    catalanArr(n) =(0 until n).map(i => (catalanArr(i)*catalanArr(n-1-i))%mod).sum
    helper(catalanArr, n+1,A,mod)
  }
}
def chordCnt(A: Int): Int  = {
  val mod = 1000000007
  if(A==0 || A==1) 1
  else {
    val catalanArr = Array.fill(A + 1)(0)
    catalanArr(0) = 1
    catalanArr(1) = 1
    /*for(i<- 2 to A) {
      for(j<-0 until  i) {
        catalanArr(i)= ((catalanArr(i)%mod + ((catalanArr(j)%mod*catalanArr(i-j-1)%mod)%mod)%mod)%mod);
      }
    }*/

    helper(catalanArr, 2,A,mod)
    catalanArr(A)
  }
}
//894965608
chordCnt(999)