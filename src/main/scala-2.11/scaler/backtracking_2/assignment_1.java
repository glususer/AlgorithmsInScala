package scaler.backtracking_2;
//The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
//Given an integer A, return all distinct solutions to the n-queens puzzle.
//
//Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
// queen and an empty space respectively.
//The final list should be generated in such a way that the indices of the queens in each list should be in reverse
// lexicographical order.
import org.apache.spark.sql.sources.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class assignment_1 {

    private static boolean isValid(int row, int col, int[] queens){
        for(int i=0;i<row ;i++){
            int j = queens[i];
            if(j == col || (i-row) == (j-col) || (i-row) == (col-j))
                return  false;
        }
        return true;
    }

    private static ArrayList<Integer> getValidPositions(int row, int[] queens, int N){
        ArrayList<Integer> positions = new ArrayList<>();
        for(int c=0 ;  c<N;c++){
            if(isValid(row,c,queens)) {
                positions.add(c);
            }
        }
        return positions;
    }
    private static String [] buildMatrix(int [] col) {
        String [] res = new String[col.length];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < col.length; i++) {
            sb.append(".");
        }
        System.out.println("sb"+sb);
        for(int i = 0; i < col.length; i++) {
            int idx = col[i];
            sb.replace(idx, idx + 1, "Q");
            res[i] = sb.toString();
            sb.replace(idx, idx + 1, "."); //reset
        }
        System.out.println("res"+Arrays.toString(res));
        return res;

    }
    private static void nQueen(int row,int[] queens,int N, List<String []> acc) {
        if (row == N){ // Base
           // System.out.println("queens "+Arrays.toString(queens));
            acc.add(buildMatrix(queens));
        }
        ArrayList<Integer> validPositions = getValidPositions(row, queens, N); // all valid combinations
        for(int pos : validPositions){
            queens[row] = pos;
            nQueen(row+1, queens, N, acc);
            queens[row] = -1;
        }
    }
    public static String[][] solveNQueens(int A) {
        List<String[]> result = new ArrayList<>();
        int[] queens = new int[A]; //column arr
        Arrays.fill(queens, -1);
        nQueen(0,queens ,A,result);
        return result.toArray(new String[result.size()][]);
    }
    public static void main(String[] args) {

        String[][] result =  solveNQueens(5);
        /*for(int i=0;i<result.length;i++){
            for(int j=0;j<result[0].length;j++){
                System.out.print(result[i][j]);
            }
            System.out.println("\n");
        }*/
    }

}
