def transpose(A:Array[Array[Int]]):Unit={
  //  A.foreach(x => println(x.toList))

  A.indices.foreach{x => A.indices.foreach{
    y => if(x<y) {
      val temp = A(x)(y)
      A(x)(y) = A(y)(x)
      A(y)(x) = temp
    }
  }}
   A.foreach(x => println(x.toList))
}

def rotate (A:Array[Array[Int]]):Unit={
  //  A.foreach(x => println(x.toList))
  A.indices.foldLeft(0){ case (_,i) =>
    A(0).indices.foldLeft(A(0).length - 1) { case (end, start)=>
      if(start < end) {
        val temp = A(i)(start)
        A(i)(start) = A(i)(end)
        A(i)(end) = temp
        end-1
      } else end-1
    }

  }
  //  A.foreach(x => println(x.toList))

}

// You are given a n x n 2D matrix A representing an image.
//Rotate the image by 90 degrees (clockwise).
//You need to do this in place.
//Note: If you end up using an additional array, you will only receive partial score.
def solve1(A: Array[Array[Int]]): Unit  = {
A.indices
  A.transpose
  transpose(A)
  rotate(A)
  //A.foreach(x => println(x.toList))
}

// Given a 2D Matrix A of dimensions N*N, we need to return sum of all possible submatrices.
def helper(i:Int, j:Int, ele:Int, n:Int, m:Int):Int=
  ele*((i+1)*(j+1)*(n-i)*(m-j))

def solve3(A: Array[Array[Int]]): Int  = {
  A.indices.foldLeft(0){case (interRowSum,i) =>
   val rowSum =  A.head.indices.foldLeft(0){case (intraRowSum,j) =>
    val sum = helper(i,j,A(i)(j),A.length, A.head.length)
      intraRowSum+sum
    }
    (interRowSum+rowSum)
  }
}
solve3(Array(Array(),Array()))
