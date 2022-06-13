package scaler.binarySearchTree_1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class assignment_3 {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) {
       val = x;
       left=null;
       right=null;
      }
  }

  public static TreeNode helper(final int[] A, int start,int end){
      if(start > end) {
          return null;
      }
      else{
          int mid = (end-start)/2+start;
          TreeNode node = new TreeNode(A[mid]);
          //System.out.println("start: "+start+ " mid: "+mid+" end: "+end);
          node.left = helper(A,start,mid-1);
          node.right = helper(A,mid+1,end);
          return node;
      }
  }

  public static void inOrder(TreeNode node){
      if (node == null){
          return;

      }

      // traverse the left child
      inOrder(node.left);

      // traverse the root node
      System.out.print(node.val + "->");

      // traverse the right child
      inOrder(node.right);
    }

    public static TreeNode sortedArrayToBST(final int[] A) {
        return  helper(A, 0, A.length-1);
    }

    public static void main(String[] args) {
        int array[] = { 1,2};
        TreeNode result = sortedArrayToBST(array);
        inOrder(result);

    }
}
