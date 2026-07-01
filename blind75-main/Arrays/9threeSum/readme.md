### 15. 3Sum

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:
```
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
```
Example 2:
```
Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
```
Example 3:
```
Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
```
Constraints:

- 3 <= nums.length <= 3000
- -10^5 <= nums[i] <= 10^5

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Three sum is a superset of the problem twosum and the target is zero always and the triplet must not contain duplicates. First fix one number let say 'x' and it's left us with to find two number like twoSum</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>You can use hashmap to speed up the algorithm, but you can also sort the input array to avoid duplicates and also two sum on sorted array becomes faster usig two pointer method</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    result -> []
    nums -> sort(nums)
    for idx, num in nums:
        if idx > 0 and num == nums[i - 1] then continue
        l -> idx + 1
        r -> nums.length - 1
        while l lessThan r
            threeSum -> num + nums[l] + nums[r]
            if threeSum greaterThan 0 then r -= 1
            elif threeSum lessThan 0 then l += 1
            else
                result.append([a, nums[l], nums[r]])
                l += 1
                while nums[l] == nums[l-1] and l lessThan r
                    l += 1
      return result
  </pre>
</details>
