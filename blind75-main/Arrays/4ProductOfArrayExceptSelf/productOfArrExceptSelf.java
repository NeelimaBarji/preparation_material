import java.util.*;

public class productOfArrExceptSelf {
    public static int[] product1(int[] nums) {
        int Len = nums.length,
                t = 1,
                p = 1;
        int[] res = new int[Len];
        for (int i = 0; i < Len; i++) {
            res[i] = p;
            p *= nums[i];
        }
        for (int i = Len - 1; i > -1; i--) {
            res[i] *= t;
            t *= nums[i];
        }
        return res;
    }
    public static int[] product2(int[] nums) {
        int Len = nums.length,
            t = 1,
            p = 1;
        int[] res = new int[Len];
        Arrays.fill(res, 1);
        for(int i = 0; i < Len; i++) {
            if (i < Len - 1) {
                p *= nums[i];
                res[i+1] *= p;
            }
            if (i > 0) {
                t *= nums[Len - i];
                res[(Len - 1) - i] *= t;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        System.out.println(Arrays.toString(product1(nums)));
        System.out.println(Arrays.toString(product2(nums)));
    }
}