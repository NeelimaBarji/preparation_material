public class findMinInRotatedSortedArr {
    private static int findIt(int[] nums) {
        int res = nums[0];
        int l = 0,
                r = nums.length - 1,
                mid = 0;

        while (l <= r) {
            if (nums[l] < nums[r]) {
                res = res < nums[l] ? res : nums[l];
            }
            mid = Math.floorDiv((l + r), 2);
            res = res < nums[mid] ? res : nums[mid];
            if (nums[mid] >= nums[l]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 4, 5, 1, 2 };
        System.out.println(findIt(nums));
    }
}
