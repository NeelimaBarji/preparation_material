### 5. Longest Palindromic Substring

Given a string s, return the longest palindromic substring in s.

Example 1:
```
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
```
Example 2:
```
Input: s = "cbbd"
Output: "bb"
```

Constraints:

- 1 <= s.length <= 1000
- s consist of only digits and English letters.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Use DP approach in memoization. Try t0 use the previously computed palindrome.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Can also use two pointer approach. By separating loop for odd and even length palindrome.</p>
</details>

---
<details>
  <summary><h3>Show Hint 3</h3></summary>
  <p>For each iteration increase the right pointer and decrease left pointer while both characters are equal and update if the current length of palindrome string is greater than previous one.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    res -> ""
    resultLength -> 0
    for i -> 1 to lengthOf(s)
      l -> 1, r -> i
      //for odd length
      while l greaterThanOrEqualTo 0 and r lessThan lengthOf(s) and s.charAt(l) equals s.charAt(r)
        if r - l + 1 isGreaterThan resultLength then
          res -> s.substring(l to r+1)
          resultLength -> r - l + 1
        l -> l - 1
        r -> r - 1
      //for even length
      l -> i, r -> i + 1
      while l greaterThanOrEqualTo 0 and r lessThan lengthOf(s) and s.charAt(l) equals s.charAt(r)
        if r - l + 1 isGreaterThan resultLength then
          res -> s.substring(l to r + 1)
          resultLength -> r - l + 1
        l -> l - 1
        r -> r - 1
    return res
  </pre>
</details>
