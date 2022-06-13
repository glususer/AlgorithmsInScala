// 1 2 3 4 ---> 1 2 4 3, 1 3 4 2 0----> 1 4 3 2 0
def nextPermutation(A: Array[Int]): Array[Int]  = {
  val (firstNumOutOfOrder,_,firstNumberOutOfOrderIndex ) = A.foldRight(Integer.MAX_VALUE,A.last,A.length-1){case (ele, (acc, prev,idx)) =>
    if (ele < prev && acc == Integer.MAX_VALUE) (ele, ele, idx)
    else if(acc != Integer.MAX_VALUE) (acc, ele, idx)
    else (acc, ele, idx-1)

  }
//  println(s"firstNumOutOfOrder ${firstNumOutOfOrder} firstNumberOutOfOrderIndex $firstNumberOutOfOrderIndex")

  firstNumOutOfOrder  match{
    case x if x == Integer.MAX_VALUE  => A.reverse
    case _ =>  val (nextNumGreaterThanOutOfOrderNum, nextNumGreaterThanOutOfOrderNumIdx) =

     (firstNumberOutOfOrderIndex+1 max 0 until A.length).foldLeft(Integer.MAX_VALUE, A.length-1){
       case ((maxEle, maxIdx),idx) =>
       //  println(s"maxElee $maxEle, maxIdx $maxIdx currentIdx $idx currentEle ${A(idx)}")
       if(A(idx) > firstNumOutOfOrder && A(idx) < maxEle) (A(idx),idx)
       else (maxEle, maxIdx)
     }
    //  println(s"nextNumGreaterThanOutOfOrderNumIdx $nextNumGreaterThanOutOfOrderNumIdx and num is ${A(nextNumGreaterThanOutOfOrderNumIdx)}")

      val temp = A(nextNumGreaterThanOutOfOrderNumIdx)
      A(nextNumGreaterThanOutOfOrderNumIdx) = A(firstNumberOutOfOrderIndex)
      A(firstNumberOutOfOrderIndex) =  temp
    //  println(s"A ${A.toList}")
      A.take(firstNumberOutOfOrderIndex+1)++A.drop(firstNumberOutOfOrderIndex+1).sorted
  }

}

nextPermutation(Array(444, 994, 508, 72, 125, 299, 181, 238, 354, 223, 691, 249, 838, 890, 758, 675, 424, 199, 201, 788, 609, 582, 979, 259, 901, 371, 766, 759, 983, 728, 220, 16, 158, 822, 515, 488, 846, 321, 908, 469, 84, 460, 961, 285, 417, 142, 952, 626, 916, 247, 116, 975, 202, 734, 128, 312, 499, 274, 213, 208, 472, 265, 315, 335, 205, 784, 708, 681, 160, 448, 365, 165, 190, 693, 606, 226, 351, 241, 526, 311, 164, 98, 422, 363, 103, 747, 507, 669, 153, 856, 701, 319, 695, 52))