## 1. Two Sum
Don't open the solution file directly. Instead, I've provided some hints to help solve the problem. Even if you can't solve the problem, I've provided a pseudocode-like structure for the complete solution at the end. Read it, try to implement it on your own, and then compare it with my actual solution to see how you did.

## question 
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.
### Example 1:
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```
```
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
```
```
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
```
Constraints:
- 2 <= nums.length <= 10^4
- -10^9 <= nums[i] <= 10^9
- -10^9 <= target <= 10^9
- Only one valid answer exists.

<details>
  <summary><h2>Show Hint 1</h2></summary>
  <p>The most brute force way is to consider every combination of the array, but there is an optimal way to take combination by fixing a number x.</p>
</details>

---
<details>
  <summary><h2>Show Hint 2</h2></summary>
  <p>If you have a number, let's say 'x,' we know that 'target - x' yields some values. Check whether that number exists in the array</p>
</details>

---
<details>
  <summary><h2>Show Hint 3</h2></summary>
  <p>Instead of still doing it in a brute force manner, try to think of a data structure that can help you solve it in linear time.</p>
</details>

---
<details>
  <summary><h2>Show Hint 4</h2></summary>
  <p>Use hashmap to solve by storing the 'target - x' as a key and index as a value.</p>
</details>

---
<details>
  <summary><h2>PseudoCode</h2></summary>
  <pre>
    hashmap -> map()
    for index, number in array
      difference -> target - x
      if difference is in target
        return [hashmap[difference], index]
    return []
  </pre>
</details>
