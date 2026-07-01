public class Solution {

    public String encode(List<String> strs) {
        StringBuilder encodedString = new StringBuilder();
        for (String str : strs) {
            encodedString.append(str.length()).append("#").append(str);
        }
        return encodedString.toString();
    }

    public List<String> decode(String str) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') j++;

            int length = Integer.valueOf(str.substring(i, j));
            i = j + 1 + length;
            list.add(str.substring(j + 1, i));
        }
        return list;
    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("co/'{12}#de");
        System.out.println(encode(list));
        System.out.println(decode("4#leet11#co/'{12}#de"))
    }
}
