def helper(A:Int, B:Int, C:Int, acc:Int=1):Int={
  println(s"A $A, B $B, C $C, acc $acc")
  if(B==0)acc
  else helper(A,C%B,C, (acc*A)%C)
}
def pow(A: Int, B: Int, C: Int): Int  = {
  if(A == 0) 0
  else if(B ==0) 1
  else helper(A,B-1,C,A%C)
}

pow(71045970,41535484,64735492)

