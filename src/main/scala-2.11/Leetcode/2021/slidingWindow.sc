//https://leetcode.com/problems/max-consecutive-ones-iii/

def longestOnesHelper(left: Int, right: Int, A: Array[Int], limit: Int, count: Int):Int ={
  if(count < limit) left
  else if(A(left) == 0)longestOnesHelper(left+1, right, A, limit, count-1)
  else longestOnesHelper(left+1, right, A, limit, count)
}

def longestOnes1(A: Array[Int], K: Int): Int = {
val x =  A.scanLeft(0,0,0,0){case((zeroCount, maxLength, left,right), ele) =>
  if(left <= right) {
    if (zeroCount <= K) {
      if (A(right) == 1) (zeroCount, maxLength + 1, left, right + 1)
      else (zeroCount + 1, maxLength + 1, left, right + 1)
    } else {
      val newLeft = longestOnesHelper(left, right, A: Array[Int], K, zeroCount)
      val newLength = math.max(right - newLeft + 1, maxLength)
      println(s"newLeft  $newLeft, newLength ${right - newLeft + 1} maxLength $maxLength zeroCount $zeroCount")
      (right - newLeft + 1, newLength, newLeft, right + 1)
    }
  } else (zeroCount, maxLength, left,right)
}

  x.foreach(println)
  x.last._2
}

val array1 = Array(1,1,1,0,0,0,1,1,1,1,0)
//longestOnesHelper(4,10,Array(1,1,1,0,0,0,1,1,1,1,0),2,3)
longestOnes1(array1, 2)

//public int longestOnes(int[] A, int K) {
//        int zeroCount=0,start=0,res=0;
//        for(int end=0;end<A.length;end++){
//            if(A[end] == 0) zeroCount++;
//            while(zeroCount > K){
//                if(A[start] == 0) zeroCount--;
//                start++;
//            }
//            res=Math.max(res,end-start+1);
//        }
//        return res;
//    }

def longestOnes(A: Array[Int], K: Int): Int={
  A.scanLeft(0,0,0,0){case ((zeroCount,start, end, result),_) =>
  if(A(end) == 0) (zeroCount+1,start, end, result)
  else{
    ???
  }}
  ???
}

//https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/
def minSwaps(nums: Array[Int]): Int = {
  val ones = nums.count(_ ==1)
  if(ones == 0) 0
  else {
    val circularNums = nums ++ nums
    val onesInFirstWindow = circularNums.take(ones).count(_ == 1)
    val holes = circularNums.tail.foldLeft(onesInFirstWindow, onesInFirstWindow, nums.head, 1, ones){
      case ((maxOnes, prevOnes, prevNum, startIdx, endIdx), _) =>
        if(endIdx < circularNums.length) {
          val left = circularNums(startIdx)
          val right = circularNums(endIdx)
          val currentOnes = (prevNum, left, right) match {
            case (0, 1, 1) | (0,0,1) => prevOnes + 1
            case (1, 0, 0) | (1,1,0) => prevOnes - 1
            case _ => prevOnes
          }
          (currentOnes max maxOnes, currentOnes, left, startIdx + 1, endIdx + 1)
        }else (maxOnes, prevOnes, prevNum, startIdx, endIdx)

    }
    ones - holes._1
  }
}

minSwaps(Array(0,1,0,1,1,0,0))
minSwaps(Array(0,1,1,1,0,0,1,1,0))
minSwaps(Array(1,1,0,0,1))
minSwaps(Array(0,1,0,1,1,0,0))
minSwaps(Array(0,1,1,1,0,0,1,1,0))

//Array(0,1,1,1,0,0,1,1,0,0,1,1,1,0,0,1,1,0).sliding(5).toList
