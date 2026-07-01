### 139. Word Break

Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
```
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
```
Example 2:
```
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
```
Example 3:
```
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
```

Constraints:

- 1 <= s.length <= 300
- 1 <= wordDict.length <= 1000
- 1 <= wordDict[i].length <= 20
- s and wordDict[i] consist of only lowercase English letters.
- All the strings of wordDict are unique.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Make the array with length of s + 1 which stores boolean values either true of false. if the string and worddict array satisfies the condition return true or false.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>The condition is if idx + length of the word lessthan length of string and s[idx to idx + length of word] == word means then arrat[idx] = array[idx to idx + length of word]. finally return array[0]</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    dp -> Array.ofLength(s.length() + 1).fill(false)
    dp[s.length()] -> true
    for i -> 1 to s.length()
      if (i + word.length()) lessThanOrEqual s.length() and s[i to i + word.length()] equals word
        dp[i] = dp[i + word.length()]
      if dp[i]
        break
    return dp[0]
  </pre>
</details>
