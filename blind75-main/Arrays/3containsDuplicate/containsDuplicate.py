# leetcode 217

class Solution:
    def containsDuplicate(self, nums: list[int]) -> bool:
        count = dict()
        for i in range(len(nums)):
            if nums[i] in count.keys():
                return True
            count[nums[i]] = 1
        return False

print(Solution().containsDuplicate([1,2,3,1]))
print(Solution().containsDuplicate([1,2,3,4]))
print(Solution().containsDuplicate([1,1,1,3,3,4,3,2,4,2]))