import java.lang.Math;

public class bestTimeToBuyAndSell {
    private static int maxProfit(int[] prices) {
        int l = 0,
                r = 1,
                maxprofit = 0,
                len = prices.length,
                profit = 0;
        while (r < len) {
            if (prices[l] < prices[r]) {
                profit = prices[r] - prices[l];
                maxprofit = Math.max(maxprofit, profit);
            } else {
                l = r;
            }
            r += 1;
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        int[] nums = { 7, 1, 5, 3, 6, 4 };
        System.out.println(maxProfit(nums));
    }
}
