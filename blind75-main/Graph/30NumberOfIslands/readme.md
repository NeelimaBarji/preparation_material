# [30](https://leetcode.com/problems/number-of-islands/) Number of Islands

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

```
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
```

Example 2:

```
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
```

Constraints:

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 300
- grid[i][j] is '0' or '1'.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Usual DFS algorithm for 2D matrix for prev row & col, next row & col</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Think about the base case for row & col index. And no recursion need for element which is '0' or already visited. Keep track of visited place.</p>
</details>

---
<details>
  <summary><h3>Show ALgorithm</h3></summary>
  <pre>
    dfs:
      if row isLessThan 0 or col isLessThan 0 or row equals ROWLEN or col equals COLLEN
        return
      if grid.at(row,col) equals '0' or isVisited.at(row,col)
        isVisited.at(row,col) -> 0
      dfs -> call row - 1, col
      dfs -> call row, col + 1
      dfs -> call row + 1, col
      dfs -> call row, col - 1
      
  </pre>
</details>
