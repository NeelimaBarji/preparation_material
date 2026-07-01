public class reverseBits {
    // you need treat n as an unsigned value
    public static int reverse(int num) {
        // int result = 0;
        // for(int i = 0; i < 32; i++) {
        // result = (result << 1) | (n & 1);
        // n >>= 1;
        // }
        // // or
        num = ((num & 0xffff0000) >>> 16) | ((num & 0x0000ffff) << 16);
        num = ((num & 0xff00ff00) >>> 8) | ((num & 0x00ff00ff) << 8);
        num = ((num & 0xf0f0f0f0) >>> 4) | ((num & 0x0f0f0f0f) << 4);
        num = ((num & 0xcccccccc) >>> 2) | ((num & 0x33333333) << 2);
        num = ((num & 0xaaaaaaaa) >>> 1) | ((num & 0x55555555) << 1);
        return num;
    }

    public static void main(String[] args) {
        System.out.println(reverse(11010)); // this is binary pass
    }
}