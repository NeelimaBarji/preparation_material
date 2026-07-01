# leetcode 153
# unique elements  return min and return solve it in O(log n)
# can able to use sliding window to search pair of decaresing value where it decreases we can find the minimum value
class Solution:
    def findMin(self, nums: list[int]) -> int:
        res = nums[0]
        l, r = 0, len(nums) - 1
        
        while l <= r:
            if nums[l] < nums[r]:
                res = min(nums[l], res)
            mid = (l + r) // 2
            res = min(res, nums[mid])
            if nums[mid] >= nums[l]:
                l = mid + 1
            else:
                r = mid - 1     
        return res
class Solution2:
    def findMin(self, nums: list[int]) -> int:
        l = 0
        r = len(nums) - 1
        minimum = nums[0]
        while l <= r:
            mid = (l+r) // 2
            if nums[mid] < minimum:
                minimum = nums[mid]
            if nums[mid] > nums[l]:
                if nums[l] < minimum:
                    minimum = nums[l]
                l = mid + 1
            else:
                if nums[r] < minimum:
                    minimum = nums[r]
                r = mid - 1
        return minimum