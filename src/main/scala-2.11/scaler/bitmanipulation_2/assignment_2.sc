//Given an array of positive integers A, two integers appear only once, and all the other integers appear twice.
//Find the two integers that appear only once
def getExponent(n:Int, acc:Int=0):Int={
  if(n==1) acc
  else getExponent(n/2, acc+1)

}

def getBinaryString(x:Int, length:Int):String={
  val binaryStr = x.toBinaryString
  if(length == binaryStr.length) binaryStr
  else (0 until (length-binaryStr.length)).map(_ => "0").mkString("")+x.toBinaryString
}

def solve1(A:Array[Int]):Array[Int] ={
  val xor = A.indices.foldLeft(0){case(xor, i) => xor^A(i)}
  val lowestSetBitOfXOR:Int = getExponent(xor ^(xor & xor-1))+1
  val maxIntBinaryLength =  A.max.toBinaryString.length

 val(set1, set2) =  A.map(num => getBinaryString(num, maxIntBinaryLength)).partition(str => str.takeRight(lowestSetBitOfXOR).head == '0')

  val x = set1.map(x => Integer.parseInt(x,2)).toList.foldLeft(0){case (acc, ele) => acc^ele}
  val y = set2.map(x => Integer.parseInt(x,2)).toList.foldLeft(0){case (acc, ele) => acc^ele}
  if(x<y)Array(x,y) else Array(y,x)
}

solve1(Array(684, 96, 1126, 1288, 330, 1360, 940, 330, 1288, 580, 476, 812, 96, 264, 1360, 684, 476, 1126, 580, 812))

