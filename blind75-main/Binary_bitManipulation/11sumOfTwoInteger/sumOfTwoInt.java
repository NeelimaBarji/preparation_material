
class Solution {
    private static int addVal(int a, int b, int mask) {
        if (b == 0) {
            return a;
        }
        return addVal((a ^ b) & mask, ((a & b) << 1) & mask, mask);
    }

    public static int getSum(int a, int b) {
        int mask = 0xFFFFFFFF;
        int val = addVal(a, b, mask);
        if (val > Math.pow(2, 31)) {
            return ~(val ^ mask);
        } else {
            return val;
        }
    }

    public static void main(String[] args) {
        System.out.println(getSum(-1, 1));
    }
}
