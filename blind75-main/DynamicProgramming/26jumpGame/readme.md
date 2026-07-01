### 55. Jump Game

You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Example 1:
```
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```
Example 2:
```
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
```

Constraints:

- 1 <= nums.length <= 10^4
- 0 <= nums[i] <= 10^5

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>It is one of easiest Dynamic programming problem. It is easy to come up with linear solution. Try to use bottom up approach, Iterate array in reverse, start from goal.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Set the goal to nums.length - 1 and if index + current number at index greater than goal set the goal to that index. If goal equals 0 then return true else false.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    length -> nums.length
    goal -> length - 1
    for index -> length - 1 to 0
      if index + nums[index] greaterThanOrEqual goal then goal = index
    if goal equals 0 then return true else false
  </pre>
</details>
