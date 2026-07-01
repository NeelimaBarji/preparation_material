# Best time to buy and sell stock - Google interview question (Leet code 121)
# two pointer concept l,r, l=buy, r = sell 7 > 1 1 - 7 -6 if l is greater than r then move l to r and r to +1 index
# now check again l = 1, r=5, maxprofit=4 update max profit, r = 3 maxprofit = 2no update, r = 6 maxprofit=5 update, r=4 maxprofit=5 no update
# found maxprofit space complexity: O(1), time Complexity : O(n)

class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        l, r = 0, 1
        maxProfit = 0
        while r < len(prices):
            if prices[l] < prices[r]:
                profit = prices[r] - prices[l]
                maxProfit = max(maxProfit, profit)
            else:
                l = r
            r += 1
        return maxProfit

print(Solution().maxProfit([7,1,5,3,6,4]))

