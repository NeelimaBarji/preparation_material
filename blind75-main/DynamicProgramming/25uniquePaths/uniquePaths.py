class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        def recurse(n,m):
            if n == 1 or m == 1:
                return 1
            else:
                return recurse(n - 1, m) + recurse(n, m - 1)
        res = recurse(n,m)
        return res
    # got the base case now to optimize it because it takes too much fo time to complete
print(Solution().uniquePaths(2, 7))
print(Solution().uniquePaths(3, 7))
print(Solution().uniquePaths(4, 7))
print(Solution().uniquePaths(3, 8))
print(Solution().uniquePaths(3, 9))
print(Solution().uniquePaths(4, 8))
print(Solution().uniquePaths(5, 7))
print(Solution().uniquePaths(6, 7))
print(Solution().uniquePaths(7, 7))
class Solution2:
    def uniquePath(self, m: int, n: int) -> int:
        res = [[0]*n for i in range(m)]
        for row in range(m):
            for column in range(n):
                if row == 0 or column == 0:
                    res[row][column] = 1
                    continue
                res[row][column] = res[row - 1][column] + res[row][column - 1]
        return res
print(Solution2().uniquePath(3,7))
print(Solution2().uniquePath(3,8))
print(Solution2().uniquePath(4,7))
print(Solution2().uniquePath(4,8))
print(Solution2().uniquePath(4,9))
print(Solution2().uniquePath(4,10))
print(Solution2().uniquePath(10,10))
