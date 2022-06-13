//int countr = 0;
//    for (int i = 1;
//             i <= n; i *= 10)
//    {
//        int divider = i * 10;
//        countr += (n / divider) * i +
//                Math.min(Math.max(n %
//                         divider - i +
//                            1, 0), i);
//    }
//    return countr;

def helper(A:Int, i:Int, sum:Int):Int={
  if(i<=A){
    val divider = i*10
    val maxTerm = (A%divider)-i+1
   // println(s"i $i, divider $divider maxTerm $maxTerm sum $sum")
    val updatedSum  = sum+(A/divider)*i+ ((maxTerm max 0) min i)
    helper(A,i*10, updatedSum)
  }else sum
}

def solve(A: Int): Int  = {
  helper(A, 1, 0)
}


solve(10)