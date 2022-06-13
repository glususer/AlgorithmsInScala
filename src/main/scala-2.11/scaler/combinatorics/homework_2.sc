def noOfWays(N:Int, acc:Int, l:Int):Int={
  if(l*(l+1)>=2*N) acc
  else{
    val doesAExist = (1.0*N-((l*(l+1))/2))/(l+1)
    noOfWays(N, acc+ {if((doesAExist-doesAExist.toInt)==0.0) 1 else 0}, l+1)
  }
}

def solve(A: Int): Int  = {
  noOfWays(A, 1, 1)
}
solve(600213035)