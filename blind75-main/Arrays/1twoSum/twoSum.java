import java.util.HashMap;
import java.util.Map;
import java.util.*;

class Solution {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numToIndex.containsKey(target - nums[i])) {
                return new int[] { numToIndex.get(target - nums[i]), i };
            }
            numToIndex.put(nums[i], i);
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        int[] nums = { 3, 5, -4, 8, 11, 1, -1, 6 };
        System.out.println(Arrays.toString(twoSum(nums, 10)));
    }
}
