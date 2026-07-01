### 213. House Robber II

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
```
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
```
Example 2:
```
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
```
Example 3:
```
Input: nums = [1,2,3]
Output: 3
```

Constraints:

- 1 <= nums.length <= 100
- 0 <= nums[i] <= 1000

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Previous House Robber is similar to this problem only difference is the houses are in rounded order. Just a little modification.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>If you rob house 1, you cannot rob house n and if you rob for house 2, you can rob house n. Range is house 1 to house n-1 or house 2 to house n.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    rob1 -> 0
    rob2 -> 0
    rob3 -> 0
    rob4 -> 0
    for i -> 1 to nums.length - 1
      temp -> if nums[i] + rob1 > rob2 ? nums[i] + rob1 : rob2
      temp2 -> if nums[i + 1] + rob3 > rob4 ? nums[i] + rob3 : rob4
      rob1 -> rob2
      rob2 -> temp
      rob3 -> rob4
      rob4 -> temp2
    res -> if nums[0] > rob4 ? (if nums[0] > rob2 ? nums[0] : rob2) : rob4
    return res
  </pre>
</details>
