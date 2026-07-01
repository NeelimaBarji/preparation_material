public class longestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        int maxLen = 1;
        int[] lenLis = IntStream.generate(() -> 1).limit(nums.length).toArray();
        for (int i = nums.length; i > -1; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    lenLis[i] = lenLis[i] < 1 + lenLis[j] ? 1 + lenLis[j] : lenLis[i];
                    maxLen = maxLen > lenLis[i] ? maxLen : lenLis[i];
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 10, 4, 3, 8, 9 };
        System.out.println(lengthOfLIS(nums));
    }
}