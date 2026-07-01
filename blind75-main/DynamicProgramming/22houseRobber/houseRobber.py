# House Robber (leetcode 198) 
class Solution:
    def rob(self, nums: list[int]) -> int:
        rob1, rob2 = 0, 0

        # [rob1 , rob2, n, n+1, ....]
        for n in nums:
            temp = max(n + rob1, rob2)
            rob1 = rob2
            rob2 = temp
        return rob2
print(Solution().rob([1,2,3,1]))
print(Solution().rob([1,6,1,1,12,1]))
print(Solution().rob([1,2,3]))
print(Solution().rob([2,3,2]))
print(Solution().rob([6,1,1,1,1,12]))
