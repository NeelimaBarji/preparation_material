### 153. Find Minimum in Rotated Sorted Array
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:\
[4,5,6,7,0,1,2] if it was rotated 4 times.\
[0,1,2,4,5,6,7] if it was rotated 7 times.\
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

Example 1:
```
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
```
Example 2:
```
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
```
Example 3:
```
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
```

Constraints:

- n == nums.length
- 1 <= n <= 5000
- -5000 <= nums[i] <= 5000
- All the integers of nums are unique.
- nums is sorted and rotated between 1 and n times.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>This is pretty straight forward. We have to use binary search with little modification. Just try to think on your own to modify the binary search to find minimum in this array.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Usual mid element from binary search. Compare mid element is less than minimum then make it as a new min value then check whether the mid element is greater than left element which means that the left portion is in sorted order not in rotated so the left portions l pointer's value becomes the new min. Then shift l to mid + 1. Else if the mid is not greater than l which means mid pointer sits somewhere in the rotated portion, so now check with the right pointer make it as a new minimum and shift right to mid - 1. Finally, return the min at last.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    l -> 0
    r -> nums.length - 1
    minimum = nums[0]
    while l lessThanOrEqual r
      mid -> (l + r) floordiv 2
      if nums[mid] lessThan minimum then minimum -> nums[mid]
      if nums[mid] greaterThan nums[l] then
        if nums[l] lessThan minimum then minimum -> nums[l]
        l -> mid + 1
      else
        if nums[r] lessThan minimum then minimum -> nums[r]
        r -> mid - 1
    return minimum
  </pre>
</details>
