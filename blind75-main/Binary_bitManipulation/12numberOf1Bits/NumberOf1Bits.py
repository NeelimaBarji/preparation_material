class Solution:
    def hammingWeight(self, n: int) -> int:
        res = 0
        while n:
            n &= (n - 1)
            res += 1
        return res
n = "00000000000000000000000000001011"
print(Solution().hammingWeight(int(n)))