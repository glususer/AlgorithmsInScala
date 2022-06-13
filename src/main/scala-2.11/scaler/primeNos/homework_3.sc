val charToNumMap  = ('A' to 'Z').zip(1 to 26).toMap

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

titleToNumber("BA")