class Solution:
    def combine(self, nums: list[int], target: int) -> list[list[int]]:
        dp = []
        res = []
        def backtrack(i, total):
            if total == target:
                if dp in res:
                    return
                res.append(dp.copy())
                return
            if i == len(nums):
                return
            if total + nums[i] <= target:
                total += nums[i]
                dp.append(nums[i])
                backtrack(i, total)
                tomin = dp.pop()
                total -= tomin
            backtrack(i+1, total)
        backtrack(0,0)
        return res
print(Solution().combine([2,3,5], 8))
print(Solution().combine([2,3,6,7], 7))
print(Solution().combine([8,7,4,3], 11))
print(Solution().combine([2,5,3,6], 10))
print(Solution().combine([5,7,5,6,5,5,5,6,7], 5))
print(Solution().combine([1,2,3], 4))