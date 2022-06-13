package scaler.queue_2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class assignment_2 {
    public static int[] slidingMaximum(final int[] A, int B) {
        if(B  >= A.length){

            int max =  Integer.MIN_VALUE;
            int i=0;
            while(i<A.length){
                if(max < A[i]){
                    max = A[i];
                }
                i++;
            }
            return new int[]{max};
        }
        else if(B==1){
            return A;
        }
        Deque<Integer> deq = new ArrayDeque<Integer>();
        int idx = 0;
        int[] result = new int [A.length-B+1];
        Arrays.fill(result, 0, result.length-1, 0);

        deq.addFirst(0);

        for(int i =1; i<A.length;i++){
            System.out.println(Arrays.toString(result)+ "i "+i+ " deq: "+deq );

            while(!deq.isEmpty() && A[deq.peekLast()] < A[i]){
                deq.removeLast();
            }
            if(deq.isEmpty() || deq.peekLast() != i) {
                deq.addLast(i);
            }

            if(!deq.isEmpty() && i-B >= deq.peekFirst()) {
                deq.removeFirst();
            }

            if(i<B){
                result[idx] = A[deq.peekFirst()];
            }
             if(i>=B){
                 idx++;
                result[idx] = A[deq.peekFirst()];
            }
            System.out.println(Arrays.toString(result)+ "i "+i+ " deq: "+deq );

        }

        return result;
    }
    //[3, 3, 5, 5, 6, 7]
    public static void main(String[] args) {
        int[] result = slidingMaximum(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1 },2);
        System.out.println(Arrays.toString(result));
    }
}
