//Given a number A, find if it is COLORFUL number or not.
//
//If number A is a COLORFUL number return 1 else, return 0.
//
//What is a COLORFUL Number
//A number can be broken into different contiguous sub-subsequence parts.
//Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245.
//And this number is a COLORFUL number, since product of every digit of a contiguous subsequence is different.
def loop(prefixProduct:Array[Int], i:Int, j:Int, productSet:Set[Int], foundDuplicate:Boolean):Boolean={
  //println(s"prefixProduct ${prefixProduct.toList} i->$i, j->$j, productSet->$productSet foundDuplicate $foundDuplicate")
  if(foundDuplicate) true
  else if(i>=prefixProduct.length && j>=prefixProduct.length) false
  else{
    val productBetweenIandJ = prefixProduct(j)/prefixProduct(i-1)
    val (updatedI, updatedJ) = (if(j==prefixProduct.length-1) i+1 else i,if(j==prefixProduct.length-1)i+1 else j+1)
    if(productSet.contains(productBetweenIandJ))loop(prefixProduct, i, j+1, productSet,true)
    else loop(prefixProduct,updatedI, updatedJ, productSet+productBetweenIandJ, false )
  }
}

def colorful(A: Int): Int  = {
  val prefixProduct = A.toString.toArray.map(_.toInt-'0').scanLeft(1){case (acc, ele) => acc*ele}
  val foundDuplicate = loop(prefixProduct, 1,1,Set.empty, false)
  if(foundDuplicate) 0 else 1
}

colorful(23)

