
val charToNumMap  = ('A' to 'Z').zip(1 to 26).toMap
val numToChar = (1 to 26).zip('A' to 'Z').toMap

def helper(str:String, acc:Int, power:Int):Int={
  if(str.isEmpty) acc
  else{
    helper(str.tail, acc+math.pow(26, power).toInt*charToNumMap(str.head), power-1)
  }
}

def titleToNumber(A: String): Int  = {
  helper(A, 0, A.length-1)
}

// AA --> 27, AB---> 28, AZ --> 26+26 = 52, ZA-> 677

titleToNumber("ZZZ")

//result = 26*result + s[i] - 'A' + 1
def convertToTitleHelper(A:Int,acc:String = ""):String={
  println(s"A $A  acc $acc")
  if(A == 0) acc
  else{
    val remainder = A%26
    val quotient = A/26
    convertToTitleHelper(if(remainder == 0)quotient-1 else quotient,numToChar.getOrElse(remainder,'Z')+acc)
  }
}
def convertToTitle(A: Int): String  = {
  convertToTitleHelper(A)
}

val actual = convertToTitle(980089)
//val expected = titleToNumber("BAQTZ")

/*def divide(A:Int, acc:String):String={
  if(A <= 1) acc
  else{
    val q = A/26
    val r = A%26
    divide(q,r+","+acc)
  }
}

divide(943566,"").split(",").map(x => numToChar.getOrElse(x.toInt,'Z'))*/





