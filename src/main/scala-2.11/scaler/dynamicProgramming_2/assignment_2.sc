//Find out the number of A digit positive numbers, whose digits on being added equals to a given number B.
//Note that a valid number starts from digits 1-9 except the number 0 itself. i.e. leading zeroes are not allowed.
//Since the answer can be large, output answer modulo 1000000007

def solve(A: Int, B: Int): Int  = {
  val count = Array.fill(A+1)(Array.fill(B+1)(0))
  //count.foreach(row => println(row.toList))
  val mod = 1000000007
  for(i<- 1 to A){
    for(j<- 1 to B){
      if(i==1) count(i)(j) = if(j > 9) 0 else 1
      else{
        for{d<-0 to 9} {
          if(j>=d) {
        //    println(s"i $i j $j d $d {i-1} ${i-1} {j-d} ${j-d}")
            count(i)(j) = (count(i)(j)%mod+(if (j-d>=0) count(i-1)(j-d)%mod else 0))%mod
          }
        }
      }
    }
  }
  //count.foreach(row => println(row.toList))
  count(A)(B)
}

solve(2,2)