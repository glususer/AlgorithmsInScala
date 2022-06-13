// void allPrime(int[] prime, int n)
//    {
//        prime[0] = 1;
//        prime[1] = 1;
//        for(int i=2; i*i<n; i++)
//        {
//            if(prime[i] == 0)
//            {
//                for(int j=i*i; j<n; j+=i)
//                {
//                    if(prime[j] == 0)
//                        prime[j] = 1;
//                }
//            }
//        }
//    }
//
//    public int[] primesum(int A) {
//
//        int[] prime = new int[A+1];
//        allPrime(prime, A+1);
//        for(int i=2; i<=A; i++)
//        {
//            if(prime[i]==0 && prime[A-i]==0)
//            {
//                return new int[]{i, A-i};
//            }
//        }
//        return new int[0];
//    }
//}

def allPrime(prime: Array[Int], n: Int):Unit = {
  prime(0)=1
  prime(1)=1
  var i=2
  while(i*i<n){
    if(prime(i) ==0){
      var j = i*i
      while(j<n){
        if(prime(j) ==0) prime(j)=1
        j=j+i
      }
    }
    i=i+1
  }
}

def primesum(A: Int): Array[Int]  = {
  val prime = Array.fill(A+1)(0)
  allPrime(prime,A+1)
  for(i<-2 to A){
    //println(s"Prime ${prime.toList}")
    if(prime(i) == 0 && prime(A-i) == 0)
      return Array(i,A-i)
  }
  Array.fill(1)(0)
}
primesum(4)