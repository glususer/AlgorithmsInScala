//You are given 3 sorted arrays A, B and C.
//
//Find i, j, k such that : max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
//
//Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])).
//int ans = Integer.MAX_VALUE;
//        int i = 0;
//        int j = 0;
//        int k = 0;
//        while(i < A.size() && j < B.size() && k < C.size()) {
//            int val1 = A.get(i) - B.get(j);
//            int val2 = B.get(j) - C.get(k);
//            int val3 = C.get(k) - A.get(i);
//            int max = Math.max(Math.max(Math.abs(val1), Math.abs(val2)), Math.abs(val3));
//            ans = Math.min(ans, max);
//            if(Math.abs(val1) > Math.abs(val2)) {
//                if(Math.abs(val1) > Math.abs(val3)) {
//                    if(val1 > 0) j++;
//                    else i++;
//                } else {
//                    if(val3 > 0) i++;
//                    else k++;
//                }
//            } else {
//                if(Math.abs(val2) > Math.abs(val3)) {
//                    if(val2 > 0) k++;
//                    else j++;
//                } else {
//                    if(val3 > 0) i++;
//                    else k++;
//                }
//            }
//        }
//        return ans;
//    }
def minimize(A: Array[Int], B: Array[Int], C: Array[Int]): Int  = {
  var ans = Integer.MAX_VALUE
  var i= 0
  var j= 0
  var k=0
  while(i<A.length && j < B.length && k<C.length){
    val val1 = A(i)-B(j)
    val val2 = B(j) - C(k)
    val val3 = C(k) - A(i)
    val max = math.abs(val1) max math.abs(val2) max math.abs(val3)
    ans = math.min(ans,max)
    if(val1 > val2){
      if(val1 > val3){
        if(val1 > 0) j=j+1
        else i = i+1
      }else {
        if(val3 > 0) i = i+1
        else k=k+1
       }
    }else{
      if(val2 > val3){
        if(val2>0)k=k+1
        else j=j+1
      }else{
        if(val3>0)i=i+1
        else k=k+1
      }
    }
  }
  ans
}