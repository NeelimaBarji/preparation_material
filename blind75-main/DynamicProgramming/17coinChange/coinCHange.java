
public class coinCHange {
    public static int coinChange(int[] coins, int amount) {
        int[] arr = IntStream.generate(() -> {
            return amount + 1;
        }).limit(amount + 1).toArray();
        arr[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    arr[i] = arr[i] < 1 + arr[i - coins[j]] ? arr[i] : 1 + arr[i - coins[j]];
                }
            }
        }
        int res = (arr[amount] != amount + 1 ? arr[amount] : -1);
        return res;
    }

    public static void main(String[] args) {
        int[] coins = { 2, 5, 3, 6 };
        int amount = 10;
        System.out.println(coinChange(coins, amount));
    }
}