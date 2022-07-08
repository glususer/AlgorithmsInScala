//You are climbing a stair case and it takes A steps to reach to the top.
//
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

def helper(current:Int,n:Int,prev1:Int, prev2:Int):Int={
  if(n==current) prev2+prev1
  else helper(current+1,n,prev1+prev2, prev1)
}
def climbStairs(A: Int): Int  = {
  if(A==0 | A==1) 1
  else helper(2,A,1,1)
}

climbStairs(0)
climbStairs(1)
climbStairs(2)
climbStairs(3)
climbStairs(4)
climbStairs(5)
climbStairs(6)