### 39. Combination Sum

Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:

```
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
```

Example 2:

```
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
```

Example 3:

```
Input: candidates = [2], target = 1
Output: []
```

Constraints:

-   1 <= candidates.length <= 30
-   2 <= candidates[i] <= 40
-   All elements of candidates are distinct.
-   1 <= target <= 40

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Guaranteed every elements are unique. We can use the same element n times to get the target try to solve it in backtracking approach. I suggest you to draw the tree for each decision will make it easy.</p>
</details>

---

<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>If you use tree structure like (for ex: [2,3,6,7]) we can either choose 2 or not to choose if we choose 2 again we can choose 2 or 3. After completed tree structure you can analyze it with how we can use recursion on it with base condition and result target condition.</p>
</details>

---

<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    backtrack(i, total, dp, res, nums)
      if total == target then //result condition
        if dp isIn res then
          return
        res.add(dp)
      if i == nums.length then return // another edge condition
      if total + nums[i] <= target then
        total -> total + nums[i]
        dp.add(nums[i])
        backtrack(i, total, dp, res, nums) // can choose same element
        toMinus -> dp.pop()
        total -> total - toMinus
      backtrack(i + 1, total, dp, res, nums) // can choose different element
    mainfunc() //nums input array
      dp -> Array(int)
      res -> Array(Array(int))
      backtrack(0, 0, dp, res, nums)
      return res
  </pre>
</details>
