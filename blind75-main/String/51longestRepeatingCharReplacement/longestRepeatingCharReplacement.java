
public class longestRepeatingCharReplacement {
    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> count = new HashMap<>();
        int res = 0,
                l = 0,
                maxFreq = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            count.put(c, 1 + (count.containsKey(c) ? count.get(c) : 0));
            maxFreq = Math.max(maxFreq, count.get(c));
            while (((r - l + 1) - maxFreq) > k) {
                count.put(s.charAt(l), (count.get(s.charAt(l)) - 1));
                l += 1;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABABBA", 2));
        System.out.println(characterReplacement("AABABBA", 1));
        System.out.println(characterReplacement("ABAB", 2));
    }
}