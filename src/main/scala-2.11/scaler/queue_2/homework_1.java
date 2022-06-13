package scaler.queue_2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class homework_1 {

    public static int[] maxInSubArrK(int[] A, int B){
        Deque<Integer> deq = new ArrayDeque<Integer>();
        int idx = 0;
        int[] result = new int [A.length-B+1];
        Arrays.fill(result, 0, result.length-1, 0);

        deq.addFirst(0);

        for(int i =1; i<A.length;i++){

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
         //   System.out.println(Arrays.toString(result)+ "i "+i+ " deq: "+deq );

        }
        return result;
    }
//2, 5, -1, 7, -3, -1, -2 =>
    public static int[] minInSubArrK(int[] A, int B){
        Deque<Integer> deq = new ArrayDeque<Integer>();
        int idx = 0;
        int[] result = new int [A.length-B+1];
        Arrays.fill(result, 0, result.length-1, 0);

        deq.addFirst(0);

        for(int i =1; i<A.length;i++){

            while(!deq.isEmpty() && A[deq.peekLast()] > A[i]){
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
            //System.out.println(Arrays.toString(result)+ "i "+i+ " deq: "+deq );

        }
        return result;
    }

    public static int solve(int[] A, int B) {
        int mod = 1000000000+7;
        if(B  >= A.length){
            int currentMax =  Integer.MIN_VALUE;
            int currentMin = Integer.MAX_VALUE;
            int i=0;

            while(i<A.length){
                currentMax = Math.max(currentMax, A[i]);
                currentMin = Math.min(currentMin,A[i]);
                i++;
            }

            return (currentMax+currentMin)%mod;
        }
        else if(B==1){
            int i=0;
            int result = 0;

            while(i<A.length){
                result = (result + (A[i]*2%mod))%mod;
                i++;
            }
            return result;
        }
        int[] maxArray = maxInSubArrK(A, B);
        int[]minArray  = minInSubArrK(A,B);
        long result = 0;

        for(int i=0;i<maxArray.length;i++){
            long currentSum = (maxArray[i]+minArray[i])%mod;
            result  = (result + currentSum)%mod;
            result = result%mod;
        }
        return (int)(result+mod)%mod;
    }
    //[3, 3, 5, 5, 6, 7]
    public static void main(String[] args) {
        int result = solve(new int[]{2, 5, -1, 7, -3, -1, -2 },4);
        System.out.println(result);
    }
}
