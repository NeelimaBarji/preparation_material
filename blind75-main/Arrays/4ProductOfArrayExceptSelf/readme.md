### 238. Product of Array Except Self

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].\
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.\
You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
```
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
```
Example 2:
```
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```

Constraints:

- 2 <= nums.length <= 10^5
- -30 <= nums[i] <= 30
- The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

**Follow up**: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

**note** : I have provided two solution both of them are similar but you can solve it in single pass of an array

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>In this problem, every number should get multiplied by every other number in the array except itself. So, we could solve it in linear time by using two individual loops, one after another, creating two arrays: a prefix array that multiplies everything from the start, and a suffix array that multiplies everything from the end, except itself. Finally, multiply both and set the result in the result array.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Try to solve it using only result array as the same approach in hint 1.</p>
</details>

---
<details>
  <summary><h3>Show Hint 3</h3></summary>
  <p>Create two pointers to perform multiplication and store the previous value. In the first forward loop, set the first element of the result array to the value pointed to by the forward pointer, and then multiply the forward pointer with the current element. Perform the same operation in the backward loop, but in reverse order, using the same result array.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    result -> []length(nums)
    pre -> 1
    suf -> 1
    length -> length(nums)
    for i -> 0 to length -1
      result[i] -> pre
      pre -> pre * nums[i]
    for i -> length - 1 to 0
      result[i] -> result[i] * suf
      suf -> suf * nums[i]
    return result
  </pre>
</details>
