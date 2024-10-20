public class SudokuSolver 
{
        public static void main(String[] args) {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku Board:");
            printBoard(board);
        } else {
            System.out.println("No solution exists!");
        }
    }

    public static boolean solveSudoku(char[][] board) {
        int[] empty = findEmptyLocation(board);
        if (empty == null) {
            return true; // Puzzle solved
        }

        int row = empty[0];
        int col = empty[1];

        // Try digits 1-9
        for (char num = '1'; num <= '9'; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num; // Place the number tentatively

                // Recursively try to solve the rest of the board
                if (solveSudoku(board)) {
                    return true;
                }

                // Undo if placing num didn't lead to a solution
                board[row][col] = '.';
            }
        }
        return false; // Trigger backtracking
    }

    public static boolean isValid(char[][] board, int row, int col, char num) {
        // Check if num is not in the same row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check if num is not in the same column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check if num is not in the 3x3 sub-box
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRowStart + i][boxColStart + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] findEmptyLocation(char[][] board) {
        // Find the next empty cell (denoted by '.')
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    return new int[] {i, j}; // return row, col
                }
            }
        }
        return null; // No empty spaces found
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
