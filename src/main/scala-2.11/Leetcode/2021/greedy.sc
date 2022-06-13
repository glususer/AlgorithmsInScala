def helperMaxArea(i: Int, j:Int, maxArea:Int, height: Array[Int]):Int={
  if(i>=j) maxArea
  else{
    val newArea = math.max((j-i)* math.min(height(i), height(j)), maxArea)
    val (newI, newJ) = if(height(i)< height(j)) (i+1, j) else(i, j-1)
    helperMaxArea(newI,newJ, newArea, height)
  }
}
def maxArea(height: Array[Int]): Int = {
helperMaxArea(0, height.length-1, 0, height)
}

//maxArea(Array(2,3,4,5,18,17,6))

