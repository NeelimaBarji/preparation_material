public class maximumProductSubarr {
    public static int maxPro(int[] nums) {
        int res = nums[0],
                currMin = Math.min(1, nums[0]),
                currMax = Math.max(1, nums[0]),
                prevMin,
                prevMax;
        for (int i = 1; i < nums.length; i++) {
            prevMin = currMin;
            prevMax = currMax;
            currMax = Math.max(nums[i] * prevMax, Math.max(nums[i] * prevMin, nums[i]));
            currMin = Math.min(nums[i] * prevMax, Math.min(prevMin * nums[i], nums[i]));
            res = Math.max(res, currMax);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 4, 5, 1, 2 };
        System.out.println(maxPro(nums));
    }
}