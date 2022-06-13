package scaler.queue_2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
//A.foldLeft(queue, freqMap, "") { case ((q, fMap, str), ch) =>
//
//    freqMap.update(ch, freqMap.getOrElse(ch, 0) + 1)
//    if (fMap.getOrElse(ch, 0) == 1) q.enqueue(ch)
//
//    while (q.nonEmpty && fMap.getOrElse(q.head, 0) > 1)
//      q.dequeue()
//
//    if (q.isEmpty) (q, fMap, str + "#")
//    else (q, fMap, str + q.head)
//  }._3
class assignment_1 {
    public static String solve(String A) {
        // initialize HashMap
        HashMap<Character, Integer> freqMap = new HashMap<Character, Integer>();
        // initialize simple Queue
        Queue<Character> q = new LinkedList<Character>();
        // initialize output string
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<A.length();i++){
            Character ch = A.charAt(i);
            freqMap.put(ch,freqMap.getOrDefault(ch,0)+1);
            if(freqMap.getOrDefault(ch,0) == 1) {
                q.add(ch);
            }

            while(!q.isEmpty() && freqMap.getOrDefault(q.peek(),0)>1) {
                q.remove();
            }
            if(q.isEmpty()) {
                sb.append("#");
            }
            else{
                sb.append(q.peek());
            }
        }
            return sb.toString();
    }
    public static void main(String[] args) {
        String result = solve("abadbc");
        System.out.println(result);
    }
}
