//Given an array of integers, every element appears thrice except for one, which occurs once.
//
//Find that element that does not appear thrice.
//
//NOTE: Your algorithm should have a linear runtime complexity.
//
//Could you implement it without using extra memory?Given an array of integers, every element appears thrice except for one, which occurs once.
//
//Find that element that does not appear thrice.
//
//NOTE: Your algorithm should have a linear runtime complexity.
//
//Could you implement it without using extra memory?
def getBinaryString(x:Int, length:Int):String={
  val binaryStr = x.toBinaryString
  if(length == binaryStr.length) binaryStr
  else (0 until (length-binaryStr.length)).map(_ => "0").mkString("")+x.toBinaryString
}

def addNos(a:List[String], acc:String=""):String={
  if(a.head.isEmpty) acc
  else{
    val binaryDigit = a.map(_.last.toInt).sum%3
    addNos(a.map(_.dropRight(1)),binaryDigit+acc)
  }
}

def singleNumber(A: Array[Int]): Int  = {
  val maxIntBinaryLength =  A.max.toBinaryString.length
  val missingNumInBinary = addNos(A.map(num => getBinaryString(num, maxIntBinaryLength)).toList)
  Integer.parseInt(missingNumInBinary,2)
}

singleNumber(Array(0, 0, 0, 1))