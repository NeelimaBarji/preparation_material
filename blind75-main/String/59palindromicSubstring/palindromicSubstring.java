
public class Solution {
    public static int countPalindrome(int l, int r, String s) {
        int res = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            res += 1;
            l -= 1;
            r += 1;
        }
        return res;
    }

    public static int isPalindromeSubSt(String s) {
        int res = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            res += countPalindrome(idx, idx, s);
            res += countPalindrome(idx, idx + 1, s);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromeSubSt("aaab"));
        System.out.println(isPalindromeSubSt("aaa"));
        System.out.println(isPalindromeSubSt("abc"));
    }
}