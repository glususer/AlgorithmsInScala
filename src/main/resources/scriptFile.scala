A.indices.foldLeft(0){case (interRowSum,i) =>
   val rowSum =  A.head.indices.foldLeft(0){case (intraRowSum,j) =>
    val sum = helper(i,j,A(i)(j),A.length, A.head.length)
      intraRowSum+sum
    }
    (interRowSum+rowSum)
  }