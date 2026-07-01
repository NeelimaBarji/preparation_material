### 300. Longest Increasing Subsequence

Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:
```
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
```
Example 2:
```
Input: nums = [0,1,0,3,2,3]
Output: 4
```
Example 3:
```
Input: nums = [7,7,7,7,7,7,7]
Output: 1
```

Constraints:

- 1 <= nums.length <= 2500
- -10^4 <= nums[i] <= 10^4

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Use the brute force way to solve it in O(n^2) complexity. By using additional array of size n to keep track of.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    maxLength -> 1
    lenList -> Array.ofSize(nums.length).fill(1)
    for i -> nums.length - 1 to 0
      for j -> i + 1 to nums.length
        if nums[i] isLessThan nums[j] then
          lenList[i] -> if lenList[i] isGreaterThan 1 + lenList[j] ? lenList[i] : lenList[j]
          maxLength -> if lenList[i] is GreaterThan  maxLength ? lenList[i] : maxLength
    return maxLength
  </pre>
</details>
