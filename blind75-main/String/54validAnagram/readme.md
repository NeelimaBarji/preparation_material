### 242. Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
```
Input: s = "anagram", t = "nagaram"
Output: true
```
Example 2:
```
Input: s = "rat", t = "car"
Output: false
```

Constraints:

- 1 <= s.length, t.length <= 5 * 10^4
- s and t consist of lowercase English letters.

*Follow up*: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Use the hashmap to identify all the characters in both words have equal occurence. First if length of two strings is different then it won't be anagram so just return false.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    if s.length not equals t.length then return false
    countS -> hashMap(Char, int)
    countT -> hashMap(Char, int)
    for i -> 1 to s.length
      countS[s[i]] -> countS.get(s[i]) > 0 ? 1 + countS.get(s[i]) : 1
      countT[t[i]] -> countT.get(t[i]) > 0 ? 1 + countT.get(t[i]) : 1
    for key in countS
      if countS[key] not equals countT.get(key, 0) then return false
    return true
  </pre>
</details>
