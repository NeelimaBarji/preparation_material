# leetcode 238
class Solution:
    def product(self, nums: list[int]) -> list[int]:
        numsLen = len(nums)
        res = [1] * numsLen
        t = 1
        p = 1
        for i in range(numsLen):
            if i < numsLen - 1:
                p *= nums[i]
                res[i+1] *= p
            if i > 0:
                t *= nums[numsLen - i]
                res[(numsLen - 1) - i] *= t
        return res


print(Solution().product([1,2,3,4]))
print(Solution().product([-1,1,0,3,-3]))
print(Solution().product([0,0]))

class Solution2:
    def product(self, nums: list[int]) -> list[int]:
        res = [1] * len(nums)
        t = 1
        p = 1
        length = len(nums)
        for i in range(length):
            res[i] = p
            p *= nums[i]
        for i in range(length - 1, -1, -1):
            res[i] *= t
            t *= nums[i]
        return res

print(Solution2().product([1,2,3,4]))
print(Solution2().product([-1,1,0,3,-3]))
print(Solution2().product([0,0]))