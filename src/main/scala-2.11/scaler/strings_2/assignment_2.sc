//Given two binary strings A and B, count how many cyclic permutations of B when taken XOR with A give 0.
//
//NOTE: If there is a string, S0, S1, ... Sn-1 , then it is a cyclic permutation is of the form Sk, Sk+1, ... Sn-1, S0, S1, ... Sk-1 where k can be any integer from 0 to N-1.

def solve(A: String, B: String): Int  = {
  val aLen = A.length
 val concatenatedStr =  B.concat(B)
  concatenatedStr.indices.foldLeft(0){case (count,i) =>
    if(i+aLen < concatenatedStr.length) {
      if (concatenatedStr.substring(i, i + aLen) == A) (count + 1)
      else count
    }else count
   }
}

solve("111","111")