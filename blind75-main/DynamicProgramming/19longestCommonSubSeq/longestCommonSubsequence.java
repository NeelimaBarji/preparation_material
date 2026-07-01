public class longestCommonSubsequence {
    public static int LCS(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        dp[0][0] = 0;
        for (int i = text1.length() - 1; i > -1; i--) {
            for (int j = text2.length() - 1; j > -1; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(LCS("abcde", "ace"));
    }
}