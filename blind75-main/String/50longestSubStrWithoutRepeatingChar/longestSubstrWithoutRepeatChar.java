
public class longestSubstrWithoutRepeatChar {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> subString = new HashMap<>();
        int maxLen = 0,
                curLen = 0,
                l = 0,
                repeat = 0,
                strLen = s.length();
        while (l < strLen) {
            if (subString.containsKey(s.charAt(l))) {
                if (subString.get(s.charAt(l)) < repeat) {
                    subString.put(s.charAt(l), l);
                    curLen = (l - repeat) + 1;
                    maxLen = Math.max(maxLen, curLen);
                    l += 1;
                    continue;
                }
                repeat = Math.max(repeat, subString.get(s.charAt(l)));
                subString.put(s.charAt(l), l);
                curLen = l - repeat;
                maxLen = Math.max(maxLen, curLen);
                l += 1;
                repeat += 1; // 1 2 4
                continue;
            }
            subString.put(s.charAt(l), l);
            curLen = (l - repeat) + 1;
            maxLen = Math.max(maxLen, curLen);
            l += 1;
        }
        return maxLen;
    }

    public static int Sol2(String s) {
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> exists = new HashMap<Character, Integer>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (exists.containsKey(s.charAt(i))) {
                j = Math.max(j, exists.get(s.charAt(i)) + 1);
            }
            exists.put(s.charAt(i), i);
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aabaab!bb"));
    }
}