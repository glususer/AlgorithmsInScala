// first find the submatrix sum i.e A(i)(j) = P(i-1)(j)+P(i)(j-1)+A(i)(j) -P(i-1)(j-1)
// To do it in O(1), use the given input array to store the sums
// Then for each B*B window calc the sum by subtracting P(start_x-1)(end_y) and P(end_x)(start_y-1) from P(i)(j) and add back P(start_x-1)(start_y-1)
// See Notes for more clarity
def solveQ4(A: Array[Array[Int]], B: Int): Int  = {
  for(i<- A.indices){
    for(j <- A.indices){
      A(i)(j) = A(i)(j)+
        (if(i > 0) A(i-1)(j) else 0 )+
        (if(j > 0) A(i)(j-1) else 0 )-
        (if(i > 0 && j>0) A(i-1)(j-1) else 0)
    }
  }

 val sums =  (for{
    i<- B-1 until A.length
    j <- B-1 until A.length

    currentSum = A(i)(j)- (if(j >=B) A(i)(j-B) else 0)-(if(i >=B) A(i-B)(j) else 0)+(if(i>=B && j>=B)A(i-B)(j-B) else 0)
  }yield (currentSum,i,j))

 // println(s"sums $sums")

  sums.map(_._1).max
}

val arr = Array(Array(1,1,1,1,1),Array(2,2,2,2,2),Array(3,8,6,7,3),Array(4,4,4,4,4),Array(5,5,5,5,5))
//solveQ4(arr,3)

def solveQ2(A: Array[Array[Int]], B: Array[Int], C: Array[Int], D: Array[Int], E: Array[Int]): Array[Int]  = {
  for(i<- A.indices){
    for(j <- A(0).indices){
      A(i)(j) = (A(i)(j)+
        (if(i > 0) A(i-1)(j) else 0 )+
        (if(j > 0) A(i)(j-1) else 0 ))%(10000000+7)-
        (if(i > 0 && j>0) A(i-1)(j-1) else 0)
    }
  }

  B.indices.map{ idx =>
    val start_x = B(idx)-1
  val start_y = C(idx)-1
  val end_x = D(idx)-1
  val end_y = E(idx)-1
  val subMatrixSum = A(end_x)(end_y)-
    (if(start_y>0) A(end_x)(start_y-1) else 0)-
    (if(start_x > 0)A(start_x-1)(end_y) else 0)+
    (if(start_x>0 && start_y>0)A(start_x-1)(start_y-1) else 0)
    subMatrixSum%(10000000+7)
  }.toArray
}

val arr1 = Array(Array(1,2,3),Array(4,5,6),Array(7,8,9))
val b1 = Array(1,2)
val c1 = Array(1,2)
val d1  = Array(2,3)
val e1 = Array(2,3)

solveQ2(arr1, b1,c1,d1,e1)
