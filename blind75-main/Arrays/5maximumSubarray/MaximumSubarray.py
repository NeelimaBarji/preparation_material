# Maximum subarray (53.Leetcode):
# 0..n-1, j=i...n-1, k=i...j, compute sum really inefficient TC:O(n^3)
# i=0..n-1, j=i..n-1, cursum + num[j] TC: O(n^2) can we do really better
# basically ignore -ve number that has to start the sum ignore if sum becomes to -ve up to that point
# like sliding window O(n) - linear time algorithm
# we only have to return maxSubaaray value not the subarray
class Solution:
    def maxSubArray(self, nums: list[int]) -> int:
        maxSub = nums[0]
        curSum = 0
        for n in nums:
            if curSum < 0:
                curSum = 0
            curSum += n
            maxSub = max(maxSub, curSum)
        return maxSub
print(Solution().maxSubArray([-2, 1, -3, 4, -1, 2, 1, -5, 4]))
print(Solution().maxSubArray([1,2,3,4,5,6,7,8,9,10]))