package scaler.backtracking_2;

public class assignment_2 {
        private static boolean isValidChoiceForC(char [][] board, int row, int col, int c ){
            for(int i = 0; i < 9; i++) {
                if(board[i][col] != '.' && board[i][col] == c) return false; //check row
                if(board[row][i] != '.' && board[row][i] == c) return false; //check column
                if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                        board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
            }
            return true;
        }
        private static boolean isValidSudoku(char[][] board){
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[0].length;j++){
                    if(board[i][j]=='.'){
                        for (char c = '1'; c <= '9'; c++) { // all combinations
                            if (isValidChoiceForC(board, i, j, c )) {
                                board[i][j] = c; //ser
                                if (isValidSudoku(board)) { // recursion
                                    return true;
                                } else board[i][j] = '.'; //unset
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }
        public static void solveSudoku(char[][] A) {
            isValidSudoku(A);
        }

    public static void main(String[] args) {

        char[][] result = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        solveSudoku(result);

        for(int i =0;i<9;i++){
            for(int j =0;j<9;j++){
                System.out.print(result[i][j]);
            }
            System.out.println("");
        }
    int x = '1'-'0';
        System.out.println("x"+x);

    }
}
