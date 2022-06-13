package scaler.linked_list_3;

public class homework_1 {

    class ListNode {
        int val;
        ListNode right, down;
        ListNode(int x) {
            val = x;
            right = down = null;
        }
    }

    public static ListNode getMiddleNode(ListNode A){
        if(A.right == null) {
            return A;
        }
        else {
            ListNode slow = A;
            ListNode fast = A.right;
            while (fast != null && fast.right != null) {
                slow = slow.right;
                fast = fast.right.right;
            }
            return slow;
        }
    }

    public static ListNode merge(ListNode A, ListNode B){
        ListNode head;
        ListNode ans ;
        if(A == null){
            return B;
        }
        else if (B==null){
            return A;
        }
        else{
            if(A.val <= B.val){
                head = A;
                ans = A;
                A = A.down;
            }
            else {
                head = B;
                ans = B;
                B = B.down;
            }

            while(A!= null && B !=null){
                if(A.val <= B.val) {
                    ans.down = A;
                    A = A.down;
                    ans = ans.down;
                }
                else {
                    ans.down = B;
                    B = B.down;
                    ans = ans.down;
                }
            }
            if(A != null) {
                ans.down = A;
            }
            if(B !=null){
                ans.down = B;
            }
            return head;
        }
    }

    ListNode flatten(ListNode root) {
        if(root == null || root.right == null) {
            return root;
        }
        ListNode middleNode = getMiddleNode(root);
        ListNode first = root;
        ListNode second = middleNode.right;
        middleNode.right = null;

        return merge(flatten(first),flatten(second));
    }
}
