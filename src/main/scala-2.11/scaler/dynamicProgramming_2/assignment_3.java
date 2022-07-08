//Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the
// row below.
//Adjacent numbers for jth number of row i is jth and (j+1)th numbers of row i+1 is

package scaler.dynamicProgramming_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//2
//3,4
//6,5,7
//4,1,8,3
public class assignment_3 {
    public static int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        int rows = a.size();
        if (rows == 1) {
            return a.get(0).get(0);
        }
        ArrayList<Integer> prevList = new ArrayList<>(1);
        ArrayList<Integer> currentList = null;
        prevList.add(a.get(0).get(0));
        for (int i = 1; i < rows; i++) {
            int col = a.get(i).size();
            currentList = new ArrayList<>(col);
            for (int j = 0; j < col; j++) {
                int currentValue = a.get(i).get(j);
                int above = prevList.size() > j ? prevList.get(j) : Integer.MAX_VALUE;
                int diagonalAbove = (j - 1) >= 0 ? prevList.get(j - 1) : Integer.MAX_VALUE;
                currentList.add(Math.min(above, diagonalAbove) + currentValue);
            }
            prevList = currentList;
        }
        return Collections.min(currentList);
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        a.add(new ArrayList<Integer>(Arrays.asList(2)));
        a.add(new ArrayList<Integer>(Arrays.asList(3, 4)));
        a.add(new ArrayList<Integer>(Arrays.asList(6, 5, 7)));
        a.add(new ArrayList<Integer>(Arrays.asList(4, 1, 8, 3)));

        int result = minimumTotal(a);
        System.out.println("result " + result);
    }
}
