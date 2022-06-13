//int max1 = Integer.MIN_VALUE;
//    int max2 = Integer.MIN_VALUE;
//    int max3 = Integer.MIN_VALUE;
//    int max4 = Integer.MIN_VALUE;
//    int min1 = Integer.MAX_VALUE;
//    int min2 = Integer.MAX_VALUE;
//    int min3 = Integer.MAX_VALUE;
//    int min4 = Integer.MAX_VALUE;
//    int n = arr1.length;
//    for (int i = 0; i < n; i++){
//        // 1st scenario arr1[i] + arr2[i] + i
//        max1 = Integer.max(arr1[i]+arr2[i]+i, max1);
//        min1 = Integer.min(arr1[i]+arr2[i]+i, min1);
//        // 2nd scenario arr1[i] + arr2[i] - i
//        max2 = Integer.max(arr1[i] + arr2[i] - i, max2);
//        min2 = Integer.min(arr1[i] + arr2[i] - i, min2);
//        // 3rd scenario arr1[i] - arr2[i] - i
//        max3 = Integer.max(arr1[i] - arr2[i] - i, max3);
//        min3 = Integer.min(arr1[i] - arr2[i] - i, min3);
//        // 4th scenario arr1[i] - arr2[i] + i
//        max4 = Integer.max(arr1[i] - arr2[i] + i, max4);
//        min4 = Integer.min(arr1[i] - arr2[i] + i, min4);
//    }
//    int diff1 = max1 - min1;
//    int diff2 = max2 - min2;
//    int diff3 = max3 - min3;
//    int diff4 = max4 - min4;
//    return Integer.max(Integer.max(diff1,diff2),Integer.max(diff3,diff4));

def helper(arr:Array[Int], n:Int):Int={
  val (maxSum, minSum)=(0 until n).foldLeft(arr(0), arr(0)){case ((max_sum, min_sum),i) =>
    (max_sum max arr(i), min_sum min arr(i))
  }
 // println(s"arr ${arr.toList} max ${maxSum-minSum}")
  maxSum-minSum
}
def solve(A: Array[Int], B: Array[Int], C: Array[Int], D: Array[Int]): Int  = {
  val n  = A.length
  val (sum1, diff1, sum2, diff2,sum3, diff3, sum4, diff4, sum5, diff5, sum6, diff6, sum7, diff7, sum8, diff8) =
    A.indices.foldLeft(Array.fill(n)(0), Array.fill(n)(0),
                      Array.fill(n)(0),Array.fill(n)(0),
                      Array.fill(n)(0), Array.fill(n)(0),
                      Array.fill(n)(0),Array.fill(n)(0),
      Array.fill(n)(0), Array.fill(n)(0),
      Array.fill(n)(0),Array.fill(n)(0),
      Array.fill(n)(0), Array.fill(n)(0),
      Array.fill(n)(0),Array.fill(n)(0)){
    case ((sum1, diff1,
            sum2, diff2,
            sum3, diff3,
            sum4, diff4,
            sum5, diff5,
            sum6, diff6,
            sum7, diff7,
            sum8, diff8),i) =>
      sum1(i) = A(i)+B(i)+C(i)+D(i)+i
      diff1(i)= A(i)+B(i)+C(i)+D(i)-i
       sum2(i)= A(i)-B(i)-C(i)-D(i)+i
       diff2(i)=A(i)-B(i)-C(i)-D(i)-i
      sum3(i) = A(i)+B(i)+C(i)-D(i)+i
      diff3(i) = A(i)+B(i)+C(i)-D(i)-i
      sum4(i) = A(i)+B(i)-C(i)-D(i)+i
      diff4(i)=  A(i)+B(i)-C(i)-D(i)-i
      sum5(i)= A(i)+B(i)-C(i)+D(i)+i
      diff5(i) = A(i)+B(i)-C(i)+D(i)-i
      sum6(i)  = A(i)-B(i)+C(i)-D(i)+i
      diff6(i)= A(i)-B(i)+C(i)-D(i)-i
      sum7(i) = A(i)-B(i)+C(i)+D(i)+i
      diff7(i) = A(i)-B(i)+C(i)+D(i)-i
      sum8(i) =  A(i)-B(i)-C(i)+D(i)+i
      diff8(i) =  A(i)-B(i)-C(i)+D(i)-i
      (sum1, diff1, sum2, diff2, sum3, diff3, sum4, diff4, sum5, diff5, sum6, diff6, sum7, diff7, sum8, diff8)
  }
  helper(sum1, n) max helper(diff1,n) max helper(sum2, n) max helper(diff2,n) max helper(sum3,n) max helper(diff3,n) max helper(sum4,n) max helper(diff4,n) max helper(sum5,n) max helper(diff5,n) max helper(sum6,n) max helper(diff6,n) max helper(sum7,n) max helper(diff7,n) max helper(sum8,n) max helper(diff8,n)
}

solve(Array(1,2,3,4), Array(-1,4,5,6), Array(-10,5,3,-8), Array(-1,-9,-6,-10))

