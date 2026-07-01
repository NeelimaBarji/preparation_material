class Solution:
    def lengthOfLIS(self, nums: list[int]) -> int:
        maxLen = 1
        LenLis = [1] * len(nums)
        for i in range(len(nums) - 1, -1, -1):
            for j in range(i+1, len(nums)):
                if nums[i] < nums[j]:
                    LenLis[i] = max(LenLis[i], 1 + LenLis[j])
                    maxLen = max(maxLen, LenLis[i])                
        return maxLen
print(Solution().lengthOfLIS([10,9,2,5,3,7,101,18]))
print(Solution().lengthOfLIS([4,10,4,3,8,9]))
print(Solution().lengthOfLIS([0,1,0,3,2,3]))
print(Solution().lengthOfLIS([7,7,7,7,7,7,7]))