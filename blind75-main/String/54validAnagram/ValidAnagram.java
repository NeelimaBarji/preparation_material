class Solution {
    public static boolean isAnagram(String s, String t) {
        // more optimal way
        // if (s.length() != t.length())
        // return false;
        // int[] count = new int[128];
        // for (final char c : s.toCharArray())
        // ++count[c];
        // for (final char c : t.toCharArray())
        // if (--count[c] < 0)
        // return false;
        // return true;

        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> countS = new HashMap<>();
        Map<Character, Integer> countT = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int count = countS.containsKey(s.charAt(i)) ? countS.get(s.charAt(i)) : 0;
            countS.put(s.charAt(i), 1 + count);
            int count2 = countT.containsKey(t.charAt(i)) ? countT.get(t.charAt(i)) : 0;
            countT.put(t.charAt(i), 1 + count2);
        }
        for (Character key : countS.keySet()) {
            if (!countS.get(key).equals(countT.get(key))) {

                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("aacc", "ccac"));
    }
}
