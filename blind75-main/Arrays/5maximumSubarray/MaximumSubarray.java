public class Solution {
    public static int maxSubArr(int[] nums) {
        int maxSub = nums[0],
                curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curSum < 0) {
                curSum = 0;
            }
            curSum += nums[i];
            maxSub = Math.max(curSum, maxSub);
        }
        return maxSub;
    }
    // just for testing
    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(maxSubArr(nums));
    }
}