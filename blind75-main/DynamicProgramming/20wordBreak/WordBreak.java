public class WordBreak {
    public static boolean wordBreak(String s, String[] wordDict) {
        Boolean[] dp = new Boolean[s.length() + 1];
        Arrays.fill(dp, Boolean.FALSE);
        dp[s.length()] = true;
        for (int i = s.length() - 1; i > -1; i--) {
            for (String wrd : wordDict) {
                if ((i + wrd.length()) <= s.length() && s.substring(i, i + wrd.length()).equals(wrd)) {
                    dp[i] = dp[i + wrd.length()];
                }
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String[] wordDict = { "leet", "code" };
        System.out.println(wordBreak("leetcode", wordDict));
        System.out.println(wordBreak("cars", new String[] { "car", "ca", "rs" }));
        System.out.println(wordBreak("catsandog", new String[] { "cats", "sand", "and", "dog", "cat" }));
    }
}