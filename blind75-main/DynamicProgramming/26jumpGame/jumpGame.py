# leetcode 55
class Solution:
    def jumpGame(self, nums: list[int]) -> bool:
        goal = len(nums) - 1
        for i in range(len(nums) - 1, -1, -1):
            if i + nums[i] >= goal:
                goal = i
        return True if goal == 0 else False
print(Solution().jumpGame([2,3,1,1,4]))
print(Solution().jumpGame([3,2,1,0,4]))