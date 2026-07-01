public class uniquePaths {
    public static int UniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                if (row == 0 || column == 0) {
                    res[row][column] = 1;
                    continue;
                }
                res[row][column] = res[row - 1][column] + res[row][column - 1];
            }
        }
        return res[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(UniquePaths(3, 7));
    }
}