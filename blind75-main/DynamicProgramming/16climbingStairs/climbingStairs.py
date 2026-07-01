# Climbing Stairs - Leetcode 70 (Dynammic programming)
class Solution:
    def climbStairs(self, n: int) -> int:
        one, two = 1, 1
        for i in range(n - 1):
            temp = one
            one = one + two
            two = temp
        return one
    
print(Solution().climbStairs(5))
print(Solution().climbStairs(4))    
print(Solution().climbStairs(3))    
print(Solution().climbStairs(2))