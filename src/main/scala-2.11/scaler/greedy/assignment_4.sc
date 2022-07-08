//There is a row of seats represented by string A. Assume that it contains N seats adjacent to each other.
//There is a group of people who are already seated in that row randomly. i.e. some are sitting together & some are scattered.
//
//An occupied seat is marked with a character 'x' and an unoccupied seat is marked with a dot ('.')
//
//Now your target is to make the whole group sit together i.e. next to each other, without having any vacant seat between them in such a way that the total number of hops or jumps to move them should be minimum.
//
//In one jump a person can move to the adjacent seat (if available).
//
//NOTE: 1. Return your answer modulo 107 + 3.
//ArrayList<Integer> pos = new ArrayList<Integer>();
//for (int i = 0; i < A.length(); i++) {
//if (A.charAt(i) == 'x') {
//pos.add(i);
//}
//}
//int mid = pos.size() / 2;
//int k = 1;
//long ans = 0;
//long mod = 10000003;
//for(int i = 0; i < mid; i++) {
//        ans = (ans + pos.get(mid) - pos.get(i) - k)%mod;
//        k++;
//    }
//k = 1;
//for(int i = mid + 1; i < pos.size(); i++) {
//        ans = (ans+pos.get(i) - ( pos.get(mid) + k))%mod;
//        k++;
//    }
//
//return (int) (ans%mod);
def seats(A: String): Int  = {
  
  A.indices.foldLeft()
}