class Solution {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int l = 0,
            r = nums.length - 1,
            threeSum = 0;
        List<Integer> r1 = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            l = i + 1;
            r = nums.length - 1;
            while (l < r) {
                threeSum = nums[i] + nums[l] + nums[r];
                if (threeSum > 0) {
                    r -= 1;
                } else if (threeSum < 0) {
                    l += 1;
                } else {
                    r1.add(nums[i]);
                    r1.add(nums[l]);
                    r1.add(nums[r]);
                    res.add(List.copyOf(r1));
                    r1.clear();
                    l += 1;
                    while ((nums[l] == nums[l - 1]) && (l < r)) {
                        l += 1;
                    }
                    threeSum = 0;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { -1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4 };
        System.out.println(threeSum(nums));
    }
}