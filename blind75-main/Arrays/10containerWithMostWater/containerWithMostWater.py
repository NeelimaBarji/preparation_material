# leetcode 11 container with most water
# 1 8 6 2 5 4 8 3 7 -> last 7 from 8-7 7 elements so area is 7x7 = 49
# brute force check every combination is O(n**2)
# class Solution:
#     def maxArea(self, h: list[int]) -> int:
#         res = 0
#         for l in range(len(h)):
#             for r in range(l + 1, len(h)):
#                 area = (r - l) * min(h[l], h[r])
#                 res = max(res, area) 
#         return res

class Solution:
    def maxArea(self, h: list[int]) -> int:
        l, r = 0, len(h) - 1
        res = 0
        
        while l < r:
            area = (r - l) * min(h[l], h[r])
            res = max(res, area)
            if h[l] < h[r]:
                l += 1
            else:
                r -= 1
        return res
print(Solution().maxArea([1,8,6,2,5,4,8,3,7]))