
class Solution {
    private static boolean valid(String s) {
        List<Character> stack = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                stack.add(s.charAt(i));
            } else if (stack.size() > 0 && ((stack.get(stack.size() - 1) == '{' && s.charAt(i) == '}')
                    || (stack.get(stack.size() - 1) == '(' && s.charAt(i) == ')')
                    || (stack.get(stack.size() - 1) == '[' && s.charAt(i) == ']'))) {
                stack.remove(stack.size() - 1);
            } else {
                return false;
            }
        }
        return stack.size() > 0 ? false : true;
    }

    public static void main(String[] args) {
        System.out.println(valid("()"));
        System.out.println(valid("()[]{}"));
        System.out.println(valid("(]"));

    }
}