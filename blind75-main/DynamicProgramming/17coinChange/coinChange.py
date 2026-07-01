# leetcode 322 medium 

class Solution:
    def cost(self, coins: list[int], amount: int) -> int:
        dp = [amount + 1] * (amount + 1)
        dp[0] = 0
        for a in range(1, amount + 1):
            for c in coins:
                if a - c >= 0:
                    dp[a] = min(dp[a], 1 + dp[a-c])
        return dp[amount] if dp[amount] != amount+1 else -1
print(Solution().cost([1,3,4,5], 7))
print(Solution().cost([1,2,5], 11))
print(Solution().cost([2], 3))
print(Solution().cost([2,5,3,6], 10))