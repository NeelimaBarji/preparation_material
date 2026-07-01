### 70. Climbing Stairs

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:
```
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```
Example 2:
```
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
```

Constraints:

- 1 <= n <= 45

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>This one is very easy just think the output of certain inputs and identify the patterns will hekp you to solve this problem that's all.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    one -> 1
    two -> 1
    for i -> 1 to n - 1
      temp -> one
      one -> one + two
      two -> temp
    return one
  </pre>
</details>
