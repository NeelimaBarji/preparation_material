### 49. Group Anagrams

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```
Example 2:
```
Input: strs = [""]
Output: [[""]]
```
Example 3:
```
Input: strs = ["a"]
Output: [["a"]]
```

Constraints:

- 1 <= strs.length <= 10^4
- 0 <= strs[i].length <= 100
- strs[i] consists of lowercase English letters.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Use the hashmap. Think what to store as a key will help you to solve this problem easily.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>The hint is if you use the 26 character length string and use as a key it contains the number of character in each word in array and make it for each word in array if the words in array has same characters in different order then it values will be on same key. Finally return it as a list of list of string.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    List(List(String)) res
    if strs.length equals 0 then return res
    groupAnagram -> Map(String, List(String))
    for each word in strs
      count -> integerArray(26)
      for i -> 1 to word.length 
          count[word[i] - 'a'] += 1
        key -> ArrayToString(count)
        groupAnagram.ifKeyDontExist(Make(key, List(String)))
        groupAnagram.get(key).add(word)
    res.addAll(groupAnagram.values())
    resturn res
  </pre>
</details>
