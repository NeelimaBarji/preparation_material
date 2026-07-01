### 62. Unique Paths

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:
```
Input: m = 3, n = 7
Output: 28
```
Example 2:
```
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
```

Constraints:

- 1 <= m, n <= 100

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>There are m rows and n columns. We have to find a number of paths from 1st row and 1st col to mth row and nth col. If you draw the ways from 2row 2col by increase one by one you can see the pattern of it. Yes you could do it in recursion it is so easy, but it is not optimized if you debug the recursion program you can see why it is happening but you can use memoization in it.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Instead of memoization in recursion, you can done with loops with a time complexity of O(m * n) by iterating each row and column. If row or column equals 0 then matrix[row][column] = 0 else matrix[row][column] = matrix[row - 1][column] + matrix[row][column - 1].</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    matrix -> ArrayOfSize(m'row, n'col)
    for row -> 1 to m
      for column -> 1 to n
        if row equals 0 or column equals 0 then
          matrix[row][column] = 1
          continue
        matrix[row][column] = matrix[row - 1][column] + matrix[row][column - 1]
    return matrix
  </pre>
</details>
