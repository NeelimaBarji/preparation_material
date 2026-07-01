
class Solution:
    def addVal(self, a: int, b: int, mask: int) -> int:
        if b == 0:
            return a
        return self.addVal((a^b) & mask, ((a&b)<<1) & mask, mask)
    def getSum(self, a: int, b: int) -> int:
        mask = 0xFFFFFFFF
        val = self.addVal(a, b, mask)
        if val > 2**31:
            return ~(val ^ mask)
        else:
            return val

print(Solution().getSum(-1,1))