//You are given a string A consisting of 1's and 0's. Now the task is to make the string consisting of only 1's. But you are allowed to perform only the following operation:
//
//Take exactly B consecutive string elements and change 1 to 0 and 0 to 1.
//Each operation takes 1 unit time, so you have to determine the minimum time required to only make the string of 1's. If not possible, return -1.

//int ans = 0;
//		int [] counter = new int [A.length()];
//		int count =0;
//		for(int i=0; i< A.length(); i++) {
//			count = count + counter[i];
//			if(A.charAt(i) == ‘0’ && count %2 ==0 || A.charAt(i)  == ‘1’ && count %2 ==1) {
//				count = 1 + count;
//				if(i <=A.length()-B) {
//					ans ++;
//					if(i+B < A.length()) {
//						counter[i+B] = -1;
//					}
//				}else {
//					return -1;
//				}
//			}
//		}
//		return ans;
def solve(A: String, B: Int): Int  = {
  val counter = Array.fill(A.length)(0)
  var count = 0
  A.indices.foldLeft(0){case (ans, i) =>
  count = count+counter(i)
    if(A.charAt(i) == '0' && count%2==0 || A.charAt(i) == '1' && count%2==1){
      count = count+1
      if(i<=A.length-B) {
        if(i+B<A.length)counter(i+B) = -1
        ans+1
      }else -1
    }else ans
  }
}

solve("011",3)