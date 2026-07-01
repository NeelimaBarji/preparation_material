public class wordSearch {
    static boolean backTrack(char[][] board, String word, int i, int j, int ROWS, int COLS, int idx, int length) {
        if (idx >= length) {
            return true;
        }
        if (i < 0 || j < 0 || i >= ROWS || j >= COLS || board[i][j] != word.charAt(idx)) {
            return false;
        }
        boolean exist = false;
        if (board[i][j] == word.charAt(idx)) {
            board[i][j] += 100;
            exist = (backTrack(board, word, i + 1, j, ROWS, COLS, idx + 1, length) ||
                    backTrack(board, word, i, j + 1, ROWS, COLS, idx + 1, length) ||
                    backTrack(board, word, i - 1, j, ROWS, COLS, idx + 1, length) ||
                    backTrack(board, word, i, j - 1, ROWS, COLS, idx + 1, length));
            board[i][j] -= 100;
        }
        return exist;
    }

    static boolean search(char[][] board, String word) {
        int ROWS = board.length, COLS = board[0].length, length = word.length();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (backTrack(board, word, i, j, ROWS, COLS, 0, length)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word = "ABCB";
        System.out.println(search(board, word));
    }
}