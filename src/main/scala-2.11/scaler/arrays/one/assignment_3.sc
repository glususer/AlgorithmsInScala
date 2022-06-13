//010
def flip(A: String): Array[Int]  = {
  var l = 0
  var r = 0
  var max = 0
  val res = Array.fill(2)(0)
  var i=0
  var count = 0
  while(i<A.length){
    if(A.charAt(i) == '0') count = count+1
    else count = count-1
    if(count > max){
      max = count
      res(0) = l+1
      res(1) = r+1
    }
    if(count < 0){
      l=i+1
      r = i+1
      count=0
    }
    else r = r+1
    i=i+1
  }
  if(max==0)Array.empty
  else res
}