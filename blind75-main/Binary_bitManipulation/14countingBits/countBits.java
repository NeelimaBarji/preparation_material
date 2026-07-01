import java.util.Arrays;

public class countBits {
    private static int cnt(int x) {
        x = (x & (0x55555555)) + ((x >> 1) & (0x55555555));
        x = (x & (0x33333333)) + ((x >> 2) & (0x33333333));
        x = (x & (0x0f0f0f0f)) + ((x >> 4) & (0x0f0f0f0f));
        x = (x & (0x00ff00ff)) + ((x >> 8) & (0x00ff00ff));
        x = (x & (0x0000ffff)) + ((x >> 16) & (0x0000ffff));
        return x;
    }

    public static int[] countBit(int n) {
        // List<Integer> res = new ArrayList<>();
        int res[] = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = cnt(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBit(5)));
    }
}