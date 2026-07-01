class Solution:
    def solution1(self, n: int) -> int:
        mask, result = 1, 0
        for i in range(32):
            result <<= 1
            if mask & n:
                result |= 1
            mask <<= 1
        return result
    def reverse(self, num: int) -> int:
        num = ((num & 0xffff0000) >> 16) | ((num & 0x0000ffff) << 16)
        num = ((num & 0xff00ff00) >> 8) | ((num & 0x00ff00ff) << 8)
        num = ((num & 0xf0f0f0f0) >> 4) | ((num & 0x0f0f0f0f) << 4)
        num = ((num & 0xcccccccc) >> 2) | ((num & 0x33333333) << 2)
        num = ((num & 0xaaaaaaaa) >> 1) | ((num & 0x55555555) << 1)
        return num
print(Solution().reverse(11111111111111111111111111111101))