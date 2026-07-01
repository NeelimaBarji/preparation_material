
public class groupAnagrams {
    public static List<List<String>> groupAnagram(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0)
            return res;
        Map<String, List<String>> groupAnagram = new HashMap<>();
        for (String word : strs) {
            int[] count = new int[26];
            for (int i = 0; i < word.length(); i++) {
                count[word.charAt(i) - 'a'] += 1;
            }
            String key = new String(Arrays.toString(count));
            groupAnagram.computeIfAbsent(key, k -> new ArrayList<>());
            groupAnagram.get(key).add(word);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] suma = { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println(groupAnagram(suma));
    }
}