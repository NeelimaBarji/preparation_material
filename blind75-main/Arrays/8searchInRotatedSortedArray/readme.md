### 33. Search in Rotated Sorted Array

There is an integer array nums sorted in ascending order (with distinct values).\
Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity. 

Example 1:
```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```
Example 2:
```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```
Example 3:
```
Input: nums = [1], target = 0
Output: -1
```

Constraints:

- 1 <= nums.length <= 5000
- -10^4 <= nums[i] <= 10^4
- All values of nums are unique.
- nums is an ascending array that is possibly rotated.
- -10^4 <= target <= 10^4

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>This problem is similar to find minimum in rotated sorted array. Same method, try to solve it with a target.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Calculate mid check whether it equal to target or check whether nums mid element greater than left then we are in left sorted portion check the target is greater than nums[left] and also less than nums[mid] if it is then then shift right to mid - 1 else shift left to mid + 1. If mid is not greater than then check the target is less than nums[right] and also greater than mid so it is in right sorted portion then shift left to mid + 1 else shift right to mid - 1. That's all for the algorithm lastly return -1 at the end of loop if target isn't in the array.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    left -> 0
    right -> nums.length - 1
    while left lessThanOrEqualTo right
      mid -> (left + right) // 2
      if nums[mid] == target then return mid
      elseif nums[mid] lessThanOrEqual nums[left] then
        if target greaterThanOrEqual nums[left] and target lessThan nums[mid] then
          right -> mid - 1
        else
          left -> mid + 1
      else
        if target lessThanOrEqual nums[right] and target greaterThan nums[mid] then
          left -> mid + 1
        else
          right -> mid - 1
    return -1
  </pre>
</details>
