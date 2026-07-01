### 11. Container With Most Water

You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Example 1:
```
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
```
Example 2:
```
Input: height = [1,1]
Output: 1
```

Constraints:

- n == height.length
- 2 <= n <= 10^5
- 0 <= height[i] <= 10^4

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>You can solve it in brute force O(n^2), which is not efficient. Try to solve it using two pointer method.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Always move the minimum hight pointer either left or right(increment or decrement) and also keep track of maximum from start to end.</p>
</details>

---
<details>
  <summary><h3>Show Hint 3</h3></summary>
  <p>Calculate amount of water at each step by getting the differnce of right - left and multiply it with the minimum height(either height[left] or height[right]).</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    left -> 0
    right -> height.length - 1
    result -> 0
    while left lessThan right
      area -> (right - left) * (if height[left] lessThan height[right] then height[left] else height[right])
      result -> if result greaterThan area then result else area
      if height[left] isLessThan height[right] then left += 1 else right -= 1
    return result
  </pre>
</details>
