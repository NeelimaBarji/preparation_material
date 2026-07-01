
class climbingStairs {
    public static int ClimbStairs(int n) {
        int one = 1,
                two = 1,
                temp;
        for (int i = 0; i < n - 1; i++) {
            temp = one;
            one = one + two;
            
            two = temp;
        }
        return one;
    }

    public static void main(String[] args) {
        System.out.print(ClimbStairs(5));
    }
}