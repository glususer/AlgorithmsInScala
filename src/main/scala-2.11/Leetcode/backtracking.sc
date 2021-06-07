//https://leetcode.com/problems/sequential-digits/
def countDigits(num:Int,digits:Int):Int={
  num match{
    case x if(x < 10) =>digits
    case _ => countDigits(num/10, digits+1)
  }
}

def sequentialDigits(low: Int, high: Int): List[Int] = {
  val startDigits = countDigits(low,1)
  val endDigits = if (countDigits(high,1) > 9) 9 else (countDigits(high,1))
  println(s"startDigits $startDigits, endDigits $endDigits")
  val lst = (1 to 9)
  val digitRange = startDigits to endDigits
  (for{
    x<- digitRange
    y = lst.sliding(x,1).map(_.mkString).toList.map(_.toInt)
      .filter(num => num >= low && num <= high)
  }yield y).flatten.toList
}

sequentialDigits(100000000,1000000000)

//https://leetcode.com/problems/additive-number/
def isAdditiveNumber(num: String): Boolean = {
 def helper
}
