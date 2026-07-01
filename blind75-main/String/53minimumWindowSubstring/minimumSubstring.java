
public class minimumSubstring {
    public static String minWindow(String s, String t) {
        if (t.equals("")) {
            return "";
        }
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> countT = new HashMap<>();
        int[] res = { -1, -1 };
        int resLen = Integer.MAX_VALUE,
                l = 0,
                have = 0;
        for (int i = 0; i < t.length(); i++) {
            countT.put(t.charAt(i), 1 + (countT.containsKey(t.charAt(i)) ? countT.get(t.charAt(i)) : 0));
        }
        int need = countT.size();
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            window.put(c, 1 + (window.containsKey(c) ? window.get(c) : 0));

            if (countT.containsKey(c) && (int) window.get(c) == (int) countT.get(c)) {
                have += 1;
            }
            while (have == need) {
                if ((r - l + 1) < resLen) {
                    res[0] = l;
                    res[1] = r;
                    resLen = (r - l + 1);
                }
                window.put(s.charAt(l), (window.get(s.charAt(l)) - 1));
                if (countT.containsKey(s.charAt(l)) && window.get(s.charAt(l)) < countT.get(s.charAt(l))) {
                    have -= 1;
                }
                l += 1;
            }
        }
        l = res[0];
        int r = res[1];
        String finRes = resLen != Integer.MAX_VALUE ? s.substring(l, r + 1) : "";
        return finRes;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}