//public int solve(int A) {
//        int t=0;
//        for(int i=31;i>=0;i–){
//            if(((A>>i)&1)==1){
//                t=i;
//                break;
//            }
//        }
//        return ((1<<(t+2))-1)-A;
//    }

def solve(A: Int): Int  = {

  // highest set bit of number
  val t = (31 to 0 by -1).foldLeft(0){case (t,i) =>
  if(((A >>i)&1) == 1 && t == 0)  i
  else t
  }

  ((1<<(t+2))-1)-A
}

solve(10)

8<<2
8<<1
8<<3
10>>3 &1

((1<<(3+2))-1)