def solve(A: Array[Int]): String  = {
  var l=Integer.MIN_VALUE;
  var r=Integer.MAX_VALUE;
  var root=A(0)
  for(i<- 1 until A.length){
    if(root<A(i))l=root
    else r=root
    if(A(i)<l || A(i)>r) return "NO"
    root=A(i)
  }
  "YES"
}

solve(Array(1,5,6,4))