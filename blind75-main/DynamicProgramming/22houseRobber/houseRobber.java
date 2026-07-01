
class houseRobber {
    public static int rob(int[] nums) {
        int r1 = 0,
                r2 = 0,
                temp = 0,
                Len = nums.length;
        for (int i = 0; i < Len; i++) {
            r1 = nums[i] + r1;
            temp = r1 > r2 ? r1 : r2;
            r1 = r2;
            r2 = temp;
        }
        return r2;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 9, 3, 1 };
        System.out.println(rob(nums));
    }
}