public class NumberOf1Bits {
    public static int hamm(long n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            // keep detecting LSB of n is 1 or 0
            res += n & 1;
            // get next digit of n by right shift rotation
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(hamm(-2));
    }
}