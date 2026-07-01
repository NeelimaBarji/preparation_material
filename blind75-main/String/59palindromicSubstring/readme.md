### 647. Palindromic Substrings

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

Example 1:
```
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
```
Example 2:
```
Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
```
Constraints:

- 1 <= s.length <= 1000
- s consists of lowercase English letters.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Similar to longest palindromic substring. That's all both are literally same.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    countPalindrome(l, r, s) {
      res -> 0
      while (l greaterThanOrEqualTo 0 and r lessThan lengthOf(s) and s.charAt[l] equals s.charAt[r]
        res -> res + 1
        l -> l - 1
        r -> r + 1
      return res
    }
    isPalindromicSubstring(s) {
      result -> 0
      for idx -> 1 to lengthOf(s)
        result -> result + countPalindrome(idx, idx, s)
        result -> result + countPalindrome(idx, idx + 1, s)
      return result
    }
  </pre>
</details>
