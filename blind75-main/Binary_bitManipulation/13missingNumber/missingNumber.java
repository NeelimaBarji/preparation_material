
public class missingNumber {
    public static int missing(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= i;
            res ^= nums[i];
        }
        res ^= nums.length;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 2, 3, 5 };
        System.out.println(missing(nums));
    }
}