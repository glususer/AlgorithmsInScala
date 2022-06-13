def numSetBits(A: Int): Int  = {
  println(s"A ${A.toBinaryString}")
  A.toBinaryString.count(_ =='1')
}

numSetBits(11)