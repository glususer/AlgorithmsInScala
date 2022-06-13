//You are given a string A of length N consisting of lowercase alphabets. Find the period of the string.
//
//Period of the string is the minimum value of k (k >= 1), that satisfies A[i] = A[i % k] for all valid i.

def calculateZValue(A:Array[Char]):Array[Long]={
  val z = Array.fill(A.length)(0l)
  var l = 0
  var r = 0

  for(i<- A.indices){
    if(i > r){
      l=i
      r=i
      while(r<A.length && A(r)==A(r-l)){
        r=r+1
      }
      z(i) = r-l
      r = r-1
    }else{
      if(i+z(i) <=r){
        z(i) = z(i-l)
      }else{
        l=i
        while(r<=A.length && A(r) == A(r-l)) {
          r = r+1
        }
        z(i) = r-l
        r = r-1
      }
    }
  }
  z
}

def solve(A: String): Int  = {
  val zValue = calculateZValue(A.toCharArray)
  //println(s"zValue ${A.indices.zip(zValue).toList}")
  for(i<-  1 to A.length){
    if(zValue(i) == A.length-i)
      return i
  }
  A.length
 // A.indices.zip(zValue).map{case(i, z_i) => (i,i+z_i)}.find(_._2== A.length).getOrElse(A.length,0)._1
}

def solve2(A:String):Int={
  val arr = A.toCharArray
  var k=1
  var i=1
  while(i<arr.length){
    println(s"i $i k $k arr($i) ${arr(i)} arr(i%k) ${arr(i%k)}")
    if(arr(i) == arr(i%k)) i = i+1
    else if(k != i) k=i
    else {
      i = i+1
      k = i
    }
  }
  k
}
solve2("abca abca ab")