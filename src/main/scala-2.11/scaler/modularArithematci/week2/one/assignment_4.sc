//Given two Integers A, B. You have to calculate (A ^ (B!)) % (1e9 + 7).
//
//"^" means power,
//
//"%" means "mod", and
//
//"!" means factorial.
////Calcluating r=B%(P-1)
//long mod = (long)(1e9 + 7);
//long power = calculatePower(B,(mod-1));
//long ans = raisedPower(A,power,mod);
//return new Integer ((int)ans);
//}
//private long calculatePower(int B,long mod){
//long ans = 1;
//for(int i =2;i<=B;i++){
//ans = ((ans%mod)*(i%mod))%mod;
//}
//return ans;
//}
//private long raisedPower(long A,long r,long mod){
//// System.out.println("A "+A+" :R "+r);
//if(r==0){
//return 1;
//}
//if(r==1){
//return A%mod;
//}
//if(r%2==0){
//long x = raisedPower((A*A)%mod,r/2,mod);
//return x%mod;
//}else{
//long y = (A%mod*(raisedPower((A*A)%mod,(r-1)/2,mod)))%mod;
//return y%mod;
//}

def calculatePower(B:Int, mod:Long):Long={
  var ans = 1l
  for(i<-2 to B){
    ans = (ans%mod*(i%mod))%mod
  }
  ans
}

def raisedPower(A:Long, r:Long, mod:Long):Long={
  if(r==0)1
  else if(r==1) A%mod
  else if(r%2==0){
    val x = raisedPower((A*A)%mod,r/2,mod)
    x%mod
  }else{
    val y = (A%mod*(raisedPower((A*A)%mod,(r-1)/2,mod)))%mod
    y%mod
  }
}
def solve(A: Int, B: Int): Int  = {
  val mod = 1000000000+7
  val power = calculatePower(B,mod-1)
  val ans = raisedPower(A, power,mod)
  ans.toInt
}