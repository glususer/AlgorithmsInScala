//Given an integer A, find and return the Ath magic number.
//
//A magic number is defined as a number that can be expressed as a power of 5 or a sum of unique powers of 5.
//
//First few magic numbers are 5, 25, 30(5 + 25), 125, 130(125 + 5), …

def solve(A: Int): Int  = {
  A.toBinaryString.foldRight(0,0){case (ele, (sum, i)) =>
    if(ele=='1') (sum+math.pow(5,i+1).toInt, i+1)
    else (sum, i+1)
  }._1
}
solve(10)
