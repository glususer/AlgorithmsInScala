
//Given a number A, we need to find the sum of its digits using recursion.
def helper(num:Int, acc:Int=0):Int={
  if (num == 0) acc
  else helper(num/10, acc+num%10)
}

def solve(A: Int): Int  = {
  helper(A)
}

solve(100)

