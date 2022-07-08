def trap(A: Array[Int]): Int  = {
  val maxRight = A.scanRight(Integer.MIN_VALUE){case (ele,acc) => if(ele > acc) ele else acc}.dropRight(1)
  val maxleft = A.scanLeft(Integer.MIN_VALUE){case (acc, ele) => if(ele  > acc) ele else acc}.drop(1)
  /*println(s"maxLeft ${maxleft.toList}")
  println(s"maxRight ${maxRight.toList}")*/
  A.zip(maxleft zip(maxRight)).map{case (height, (maxL, maxR)) => (maxL min maxR) - height}.sum
}
trap(Array(1,2))