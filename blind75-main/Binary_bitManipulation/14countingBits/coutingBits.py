class Solution:
    def cnt(self, x: int) -> int:
        x = (x & (0x55555555)) + ((x >> 1) & (0x55555555))
        x = (x & (0x33333333)) + ((x >> 2) & (0x33333333))
        x = (x & (0x0f0f0f0f)) + ((x >> 4) & (0x0f0f0f0f))
        x = (x & (0x00ff00ff)) + ((x >> 8) & (0x00ff00ff))
        x = (x & (0x0000ffff)) + ((x >> 16) & (0x0000ffff))
        return x
    def counting(self, n: int) -> list[int]:
        res = []
        x = 0
        for i in range(n+1):
            x = (i & (0x55555555)) + ((i >> 1) & (0x55555555))
            x = (x & (0x33333333)) + ((x >> 2) & (0x33333333))
            x = (x & (0x0f0f0f0f)) + ((x >> 4) & (0x0f0f0f0f))
            x = (x & (0x00ff00ff)) + ((x >> 8) & (0x00ff00ff))
            x = (x & (0x0000ffff)) + ((x >> 16) & (0x0000ffff))
            res.append(x)
            x = 0
        return res 
    def solution2(self, n):
        res = [0]*(n+1)
        for i in range(n+1):
            res[i] = res[i >> 1] + i % 2
        return res

print(Solution().counting(5))
print(Solution().solution2(5))