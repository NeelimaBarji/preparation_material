### 1143. Longest Common Subsequence
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".

A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:
```
Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
```
Example 2:
```
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
```
Example 3:
```
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
```

Constraints:

- 1 <= text1.length, text2.length <= 1000
- text1 and text2 consist of only lowercase English characters.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Use the bottom up approach in DP. with 2D array. Time Complexity is O(n *m) where n is length of text1, m is the length of text2. Store the maximum of each row + 1 and col + 1 beacuse whether we choose that element or not.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>In each iteration maximum of col + 1 and row + 1 if text1[row] != text2[col] else store 1 + the value in dp at row + 1 and col + 1.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    dp -> Array.lengthOf(text1.length, forEachindex Array.lengthOf(text2.length))
    for row -> text1.length - 1 to 0
      for col -> text2.length - 1 to 0
        if text1[row].equals(text2[col]) then
          dp[row][col] -> 1 + dp[row + 1][col + 1]
        else
          dp[row][col] -> if dp[row][col + 1] isGreaterThan dp[row + 1][col] ? dp[row][col + 1] : dp[row + 1][col]
    return dp[0][0]
  </pre>
</details>
