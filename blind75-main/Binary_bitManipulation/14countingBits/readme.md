### 338. Counting Bits

Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

Example 1:
```
Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
```
Example 2:
```
Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
```
Constraints:

- 0 <= n <= 10^5

*Follow up:*
- It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
- Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>This one is little tricky because it includes DP concept (memoization). Try to utilize the previous calculated result with new ones.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Try to utilize the number calculated from previous by seperating into ranges like [2-3],[4-7] as bits increasing in each range. For each bit it increases in 2 and it's power so do the right shift to utilize the previous result</p>
</details>

---
<details>
  <summary><h3>Show Hint 3</h3></summary>
  <p>Finally odd or even number. DO the mod operation to find the remaining bits.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    res -> ArrayOfSize(n)
    for num -> 0 to n+1
      res[num] = res[num>>1] + i % 2
    return res
  </pre>
</details>
