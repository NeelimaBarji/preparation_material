
public class houseRobber2 {
    public static int robbing(int[] nums) {
        int rob1 = 0,
                rob2 = 0,
                rob3 = 0,
                rob4 = 0,
                temp1 = 0,
                temp2 = 0,
                res;
        for (int i = 0; i < nums.length - 1; i++) {
            temp1 = Math.max(nums[i] + rob1, rob2);
            rob1 = rob2;
            rob2 = temp1;
            temp2 = Math.max(nums[i + 1] + rob3, rob4);
            rob3 = rob4;
            rob4 = temp2;
        }
        res = Math.max(nums[0], Math.max(rob2, rob4));
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 6, 1, 1, 1, 1, 12 };
        System.out.println(robbing(nums));
    }
}