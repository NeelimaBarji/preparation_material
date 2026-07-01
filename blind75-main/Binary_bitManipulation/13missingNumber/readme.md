### 268. Missing Number

Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example 1:
```
Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
```
Example 2:
```
Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
```
Example 3:
```
Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
```

Constraints:

- n == nums.length
- 1 <= n <= 104
- 0 <= nums[i] <= n
- All the numbers of nums are unique.

*Follow up*: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>They gave that every number should be in range from 0 to len(arr). You can sum the array and minus it from the actual sum of n (n*(n-1) // 2), but try to solve it in bit manipulation. You will solve it in O(1) space.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>To do it in a binary way is little bit tough. For example let's take arr=[0,1,2,3,5] which length is 5 and we know that the missing number is 4. take an int var result and do the xor operation for each index with result and xor each number in that position with result and finally xor the length of the array with result return it.</p>
</details>

---
<details>
  <summary><h3>Show Hint 3</h3></summary>
  <p>previous example. res = 0 i = 0, num[i] = 0, res ^= 0 res ^= 0 (res = 0), i=1, num[i]=1 res^=1 res^= 1 (res = 0), i=2, num[i]=2 res ^= 2 res ^= 2 (res=0), i=3, num[i]=3 res^=3 res^=3 (res = 0), i=4, num[i]=5 res ^= 4 (res = 4) res ^= 5 (res = 1). After loop res ^= num.length res ^= 5 (res = 4). This happens because whenever two same elements got xor it will be 0 so that's why we xor the index and number so we find the inverse of number and when we xor with length it will return the missing number in that range.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    res -> 0
    for i -> 0 to arr.length
      res -> res ^ i
      res -> res ^ arr[i]
    res -> res ^ arr.length
    return res
  </pre>
</details>
