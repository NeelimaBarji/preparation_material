# leetcode 647 palindromic substring
class Solution:
    def isPalindromicSubSt(self, s: str) -> int:
        # O(n**2) time complexity
        res = 0
        for i in range(len(s)):
            res += self.countPalindrome(i, i , s)
            res += self.countPalindrome(i, i + 1, s)
        return res
    def countPalindrome(self, l: int, r: int, s: str) -> int:
        res = 0
        while l >= 0 and r < len(s) and s[l] == s[r]:
            res += 1
            l -= 1
            r += 1
        return res
print(Solution().isPalindromicSubSt("aaab"))
print(Solution().isPalindromicSubSt("aaa"))
print(Solution().isPalindromicSubSt("abc"))