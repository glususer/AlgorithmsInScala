//https://www.scaler.com/academy/mentee-dashboard/class/19122/assignment/problems/5840?navref=cl_tt_lst_nm
def solve(A: Int, B: Int): Int  = {
  if(A < B) (B-A)else{
    if(B > 0)(A-B)
    else {
      (A+B)%(A-B)
    }
  }
}



solve(5,12)
solve(12,5)
solve(4,10)
solve(10,4)
solve(1,5)
solve(5,1)
solve(7,-2)
solve(-2,7)
solve(9,0)
solve(6816621, 8157697  )

