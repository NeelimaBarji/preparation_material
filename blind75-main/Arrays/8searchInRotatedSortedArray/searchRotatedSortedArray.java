
public class Solution {
    public static int search(int[] nums, int target) {
        int l = 0,
            r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[l]) {
                if ((target >= nums[l]) && (target < nums[mid])) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if ((target <= nums[r]) && (target > nums[mid])) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(search(nums, 0));
    }
}