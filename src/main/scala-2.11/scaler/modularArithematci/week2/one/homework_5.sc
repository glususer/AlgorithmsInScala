//Given an integer A, return the number of trailing zeroes in A! i.e., factorial of A.
//
//Note: Your solution should run in logarithmic time complexity.
def helper(A:Int, acc:Int):Int={
  if(A==0) acc
  else helper(A/5, acc+A/5)
}

def trailingZeroes(A: Int): Int  = {
helper(A,0)
}

trailingZeroes(10)