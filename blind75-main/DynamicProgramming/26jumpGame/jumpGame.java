public class jumpGame {
    public static boolean jump(int[] nums) {
        int goal = nums.length - 1;
        for (int i = nums.length - 1; i > -1; i--) {
            if ((i + nums[i]) >= goal) {
                goal = i;
            }
        }
        return goal == 0 ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(jump(new int[] { 2, 3, 1, 1, 4 }));
        System.out.println(jump(new int[] { 3, 2, 1, 0, 4 }));
    }
}