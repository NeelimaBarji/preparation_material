class Solution:
    def wordBreak(self, s: str, wordDict: list[str]) -> bool:
        dp = [False] * (len(s) + 1)
        dp[len(s)] = True
        for i in range(len(s) - 1, -1, -1):
            for word in wordDict:
                if (i + len(word)) <= len(s) and s[i: i + len(word)] == word:
                    dp[i] = dp[i + len(word)]
                if dp[i]:
                    break
        return dp[0]
print(Solution().wordBreak("catsandog", ["cats","dog","sand","and","cat"]))
print(Solution().wordBreak("leetcode", ["leet","code"]))
print(Solution().wordBreak("applepenapple", ["apple","pen"]))
print(Solution().wordBreak("cars", ["car", "ca", "rs"]))