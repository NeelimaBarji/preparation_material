### 152. Maximum Product Subarray

Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Example 1:
```
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
```
Example 2:
```
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
```

Constraints:
- 1 <= nums.length <= 2 * 10^4
- -10 <= nums[i] <= 10
- The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>You can solve this problem in several ways, but the easy one method to think that how you solve the product of array except self problem and apply the same method here.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>You can solve it in prefix and postfix multiplication method by taking maximum of both and also make sure that either of products doesn't get to zero.</p>
</details>

---
<details>
  <summary><h3>Show Hint 3</h3></summary>
  <p>Another method is taking minimum and maximum multiplication because the number could be in negative and become positive later. In this method keep track of currentMin and currentMax by taking max and min correspondingly and also update the maximum product with the result.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    result -> maximunm(nums)
    currentMin -> 1
    currentMax -> 1
    for each num in nums
      if num == 0 then currectMin -> 1, currentMax -> 1
        continue
      temp -> currentMax * num
      currentMax -> maximum(num * currentMax, num * currentMin, num)
      currentMin -> minimum(temp, num * currentMin, num)
      result -> maximum(result, currentMax)
    return result
  </pre>
  <h3>OR</h3>
  <pre>
    l -> 0
    r -> nums.length
    pre -> 1
    post -> 1
    while r greaterThan -1 and l lessThan nums.length 
      if pre == 0 then pre -> 1
      if post == 0 then post -> 1
      pre -> pre * nums[l]
      post -> post * nums[r]
      l += 1
      r -=1 
      res -> maximum(pre, post, res)
    return res
  </pre>
</details>
