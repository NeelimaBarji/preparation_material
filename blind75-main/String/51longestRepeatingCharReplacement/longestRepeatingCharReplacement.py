# leetcode 424
class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        count = {}
        res = 0
        l = 0
        maxFreq = 0
        for r in range(len(s)):
            count[s[r]] = 1 + count.get(s[r], 0)
            maxFreq = max(maxFreq, count[s[r]])
            
            while (r - l + 1) - maxFreq > k:
                count[s[l]] -= 1
                l += 1
            res = max(res, r - l + 1)
        return res
print(Solution().characterReplacement("ABABBA", 2))
print(Solution().characterReplacement("ABABBA", 1))
print(Solution().characterReplacement("ABAB", 2))