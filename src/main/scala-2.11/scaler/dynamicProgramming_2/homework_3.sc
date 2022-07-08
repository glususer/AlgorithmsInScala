//int [] dp;
//public solve (int A){
// dp = new int [A+1];
// return compute(A);
//}
//
//int compute(int n){
//  if (A==1) return 1;
//  //init  values
//  dp[1]=1;
//  dp[2]= 2;
//  for (int i =3;i<=n;i++){
//    //first is (n-1) choices and remaining people  is (n-2) --> pair
//    // second is alone party (n-1) choices
//    dp[i]= (i-1)* dp[i-2] + 1* dp[i-1]
//  }
// return dp[n];
//}
//In Danceland, one person can party either alone or can pair up with another person.
//
//Can you find in how many ways they can party if there are A people in Danceland?
//
//Note: Return your answer modulo 10003, as the answer can be large.
def solve(A: Int): Int  = {
  if(A == 0 || A==1) 1
  else if(A==2)2
  else {
    val dp = Array.fill(A + 1)(0)

    dp(0) = 1
    dp(1) = 1
    dp(2) = 2
    val mod = 10003

    for {i <- 3 to A} {
      dp(i) = (((i - 1) * dp(i - 2)) % mod + dp(i - 1) % mod) % mod
    }
    dp(A)
  }
}

solve(10)