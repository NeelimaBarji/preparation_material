### 91. Decode Ways

A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:
```
Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
```
Example 2:
```
Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
```
Example 3:
```
Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
```

Constraints:

- 1 <= s.length <= 100
- s contains only digits and may contain leading zero(s).

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Use memoization concept. Initiate dp with s.length and its value to 1. Else DFS, but anyway you have to use memoization.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>If char at s is 0 then make the dp[i] = 0 else dp[i] = dp[i + 1]. And also check i th char is 1 or i th char is 2 then i + 1 th char should be 0-6 then dp[i] += dp[i + 2]. return dp[0].</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    dp -> { s.length : 1 }
    for i -> s.length - 1 to 0
      if characterIn s at i th position equals "0"
        dp[i] -> 0
      else
        dp[i] -> dp[i + 1]
      if i + 1 is lessThan s.length and charIn s at i th position equals "1" or charIn s at i th position equals "2" and charIn s at i + 1 th postion in "0123456"
        dp[i] -> dp[i] + dp[i + 2]
      return dp[0]
  </pre>
</details>
