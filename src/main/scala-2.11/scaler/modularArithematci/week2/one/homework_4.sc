//Given a positive integer A. Return an array of minimum length whose elements represent the powers of 3, and the sum of all the elements is equal to A.


def loopHelper(A:Int, powers:List[Int]=List.empty, i:Int=0):List[Int]={
  if(A==0) powers
  else{
    loopHelper(A/3, A%3+:powers, i+1)
  }
}
def solve(A: Int): Array[Int]  = {
  loopHelper(A).toArray.reverse.zipWithIndex.flatMap{case  (ele, idx) => if(ele == 2) List(math.pow(3,idx).toInt,math.pow(3,idx).toInt) else  if(ele  == 1) List(math.pow(3,idx).toInt) else List.empty}
}

//3 3 81 81 243
solve(411)