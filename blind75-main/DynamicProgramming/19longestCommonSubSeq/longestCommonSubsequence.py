# leetcode 1143 longest common subsequence
# 2dp bottom up dynammic programming
class Solution:
    def subsequence(self, text1: str, text2: str) -> int:
        dp = [[0 for j in range(len(text2) + 1)] for i in range(len(text1) + 1)]
        # TC : O(n * m)same memory conplexity
        for i in range(len(text1) - 1, -1, -1):
            for j in range(len(text2) - 1, -1, -1):
                if text1[i] == text2[j]:
                    dp[i][j] = 1 + dp[i + 1][j + 1]
                else:
                    dp[i][j] = max(dp[i][j + 1], dp[i + 1][j])
        return dp[0][0]
print(Solution().subsequence("abcde", "aeb"))
print(Solution().subsequence("abcde", "ace"))
print(Solution().subsequence("aee", "e"))
print(Solution().subsequence("ae", "eeee"))
print(Solution().subsequence("aaee", "ae"))