class Solution:
    def rob(self, nums: list[int]) -> int:
        rob1, rob2 = 0, 0
        r3, r4 = 0, 0
        for i in range(len(nums) - 1):
            temp = max(nums[i] + rob1, rob2)
            t2 = max(nums[i + 1] + r3, r4)
            rob1 = rob2
            rob2 = temp
            r3 = r4
            r4 = t2
        res = max(nums[0], r4, rob2) # edge case
        return res
print(Solution().rob([1,2,3,1]))
print(Solution().rob([1,6,1,1,12,1]))
print(Solution().rob([1,2,3]))
print(Solution().rob([2,3,2]))
print(Solution().rob([6,1,1,1,1,12]))