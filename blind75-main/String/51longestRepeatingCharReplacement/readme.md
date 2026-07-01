### 424. Longest Repeating Character Replacement

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:
```
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
```
Example 2:
```
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.
```

Constraints:

- 1 <= s.length <= 10^5
- s consists of only uppercase English letters.
- 0 <= k <= s.length

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Similar to longest substring without repeating character can use sliding window and hashtable in similar order to obtain the result.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Frequency of letter and letter as key value in hashtable calculate maxfrequency at each iteration. if window length - maxfrequency greater than K then reduce the size of window also keep track of maximum size of window in result.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    count -> {}
    res -> 0
    l -> 0
    maxFrequency -> 0
    for r -> 1 to s.length()
      count[s[r]] -> if count.get(s[r]) exists then count.get(s[r]) else 0
      maxFrequency -> if maxFrequency > count[s[r]] then maxFrequency else count[s[r]]
      while r - l + 1 - maxFrequency > k then
        count[s[l]] -> count.get(s[l]) - 1
        l -> l + 1
      res -> if res > r - l + 1 then res else r - l + 1
    return res
  </pre>
</details>
