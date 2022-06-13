package scaler.queue_2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class assignment_3 {
    class Solution {
        Stack< Integer > stack = new Stack < > ();
        Stack< Integer > minStack = new Stack < > ();
        public void push(int x) {
            stack.push(x);
            if(minStack.isEmpty()){
                minStack.push(x);
            }else{
                minStack.push(Math.min(x, minStack.peek()));
            }
        }

        public void pop() {
            if(!stack.isEmpty()){
                stack.pop();
                minStack.pop();
            }

        }

        public int top() {
            if(stack.isEmpty()){
                return  -1;
            }
            else{
                return  stack.peek();
            }
        }

        public int getMin() {
            if(minStack.isEmpty()) {
                return -1;
            }else {
                return minStack.peek();
            }
        }
    }
    public static void main(String[] args) {
    }
}
