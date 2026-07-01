class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        subString = {}
        maxLen = 0
        curLen = 0
        l=0
        repeat = 0
        while l < len(s):
            if s[l] in subString:
                if subString[s[l]] < repeat:
                    subString[s[l]] = l
                    curLen = (l - repeat) + 1
                    maxLen = max(maxLen, curLen)
                    l += 1
                    continue
                repeat = max(repeat, subString[s[l]])
                subString[s[l]] = l
                curLen = l - repeat
                maxLen = max(maxLen, curLen)
                l += 1
                repeat += 1
                continue
            subString[s[l]] = l
            curLen = (l - repeat) + 1
            maxLen = max(maxLen, curLen)
            l += 1
        return maxLen
    def solution2(self, s: str) -> int:
        exists = {}
        l = 0
        res = 0
        for r in range(len(s)):
            if s[r] not in exists:
                res = max(res, r - l + 1)
            else:
                if exists[s[r]] < l:
                    res = max(res, r - l + 1)
                else:
                    l = exists[s[r]] + 1
            exists[s[r]] = r
        return res
print(Solution().solution2("abcabcbb"))
print(Solution().lengthOfLongestSubstring("pwwkew"))
print(Solution().lengthOfLongestSubstring("abcadefghijklmnopqrstuv"))
print(Solution().lengthOfLongestSubstring("dvdf"))
print(Solution().lengthOfLongestSubstring("aabaab!bb"))
print(Solution().lengthOfLongestSubstring("abba"))
print(Solution().lengthOfLongestSubstring("tmmzuxt"))