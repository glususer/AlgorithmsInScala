// intuition : https://leetcode.com/problems/spiral-matrix-ii/discuss/22282/4-9-lines-Python-solutions
def rotate(A:Array[Array[Int]]):Array[Array[Int]]={
 val B = A.transpose
 B.indices.foreach{rowNo => B(0).indices.foldLeft(B(0).length-1){case (j,i) =>
   if(i<j){
    val temp = B(rowNo)(i)
    B(rowNo)(i) = B(rowNo)(j)
    B(rowNo)(j) = temp
    j-1
   }else j
 }
 }
 B
}

def helper(A:Array[Array[Int]], low:Int):Array[Array[Int]]={
 if(low == 1) A
 else{
  val rotated = rotate(A)
  helper((low-rotated(0).length until low).toArray+:rotated,low-rotated(0).length)
 }
}

def generateMatrix(A: Int): Array[Array[Int]]  = {
 helper(Array(Array(A*A)),A*A)
}
val arr = generateMatrix(963)

arr.foreach(row => println(row.mkString(", ")))

//rotate(Array(Array(8),Array(9)))