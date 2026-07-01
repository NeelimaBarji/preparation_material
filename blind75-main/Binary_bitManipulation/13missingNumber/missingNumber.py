class Solution:
    def miss(self, nums: list[int]) -> int:
        res = 0
        for i, elem in enumerate(nums):
            res ^= i
            res ^= elem
        res ^= len(nums)
        return res
print(Solution().miss([0,1,2,3,5]))