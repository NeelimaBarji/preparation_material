import java.util.*;
// imports are need for your local machine
class containsDuplicate {
    // this below method only
    public static boolean containDuplicate(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (count.containsKey(nums[i])) {
                return true;
            }
            count.put(nums[i], i);
        }
        return false;
    }
    // just some test cases
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        System.out.println(containDuplicate(nums));
        System.out.println(containDuplicate(new int[]{12,3,4,5,6,7,8,9,10,11,12}));
    }
}