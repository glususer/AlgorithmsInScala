//Given a matrix of integers A of size N x M and an integer B. Write an efficient algorithm that searches for integer B in matrix A.
//
//This matrix A has the following properties:
//
//Integers in each row are sorted from left to right.
//The first integer of each row is greater than or equal to the last integer of the previous row.
//Return 1 if B is present in A, else return 0.
//
//NOTE: Rows are numbered from top to bottom, and columns are from left to right.

def rowColToInt(row:Int, col:Int, i:Int, j:Int):Int=
  (0 until i).map(_ => col).sum+j

def intToRowCol(row:Int, col:Int, int:Int):(Int,Int)=
  (int/col,int%col)

def binarySearchinMatrix(left:Int, right:Int,key:Int, arr:Array[Array[Int]], row:Int, col:Int):Int={
  if(left < right){
    val mid = (right-left)/2+left
    val (i,j) = intToRowCol(row, col,mid)
   // println(s"left $left, right $right mid $mid, i $i j$j")
    if(arr(i)(j) < key) binarySearchinMatrix(mid+1, right,key,arr,row,col)
    else binarySearchinMatrix(left,mid,key,arr,row,col)
  }else{
    val (i,j) = intToRowCol(row,col,left)
    if(arr(i)(j)== key) 1
    else 0
  }
}

def searchMatrix(A: Array[Array[Int]], B: Int): Int  = {
  val row = A.length
  val col = A(0).length
  binarySearchinMatrix(0,row*col-1,B ,A,row,col)
}

val arr = Array(Array(1,   3,  5,  7),Array(10, 11, 16, 20),Array(23, 30, 34, 50))

searchMatrix(arr,0)
/*

val row = 3
val col = 4
val x = 2
val y = 2
val rowColIn1D = rowColToInt(row,col,x,y)

intToRowCol(3,4,rowColIn1D) == (x,y)*/
