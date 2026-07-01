
public class longPalindromSubSt {
    public static String longestPalindrome(String s) {
        String res = "";
        int resLen = 0,
                l = 0,
                r = 0;
        for (int i = 0; i < s.length(); i++) {
            l = i;
            r = i;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if ((r - l + 1) > resLen) {
                    res = s.substring(l, r + 1);
                    resLen = r - l + 1;
                }
                l -= 1;
                r += 1;
            }
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if ((r - l + 1) > resLen) {
                    res = s.substring(l, r + 1);
                    resLen = r - l + 1;
                }
                l -= 1;
                r += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }
}