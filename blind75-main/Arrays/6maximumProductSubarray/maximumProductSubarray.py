class Solution:
    def maxProduct(self, nums: list[int]) -> int:
        res = max(nums)
        currMin, currMax = 1, 1
        for n in nums:
            if n == 0:
                currMax, currMin = 1, 1
                continue
            tmp = currMax * n
            currMax = max(n * currMax , n * currMin, n)
            currMin = min(tmp, n * currMin, n)
            res = max(res, currMax)
        return res
    
print(Solution().maxProduct([-3,-1,-1]))
print(Solution().maxProduct([2,3,-2,4]))
print(Solution().maxProduct([-2,0,-1]))
print(Solution().maxProduct([3,-1,4]))
print(Solution().maxProduct([2,-5,-2,-4,3]))
print(Solution().maxProduct([3,-1,4,-1]))
print(Solution().maxProduct([2,-5,-2,-4,-1,3]))