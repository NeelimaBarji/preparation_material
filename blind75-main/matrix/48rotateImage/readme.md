### 48. Rotate Image (Medium)
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:
```
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
```
Example 2:
```
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
```

Constraints:

- n == matrix.length == matrix[i].length
- 1 <= n <= 20
- -1000 <= matrix[i][j] <= 1000

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>If you notice from the real matrix to rotate matrix it will follow some pattern, if you find it, it will be very easy.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>They given that it would be n x n matrix, so first take transpose of matrix it will be very easy, then reverse every row. Then the matrix will get rotated to 90 degree.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    for row -> 0 to matrixLength
      for col -> row + 1 to matrixLength
        swap(matrix[row][col], matrix[col][row])
    for row -> 0 to matrixLength
      l -> 0
      r -> matrixLength - 1
      while (l lessThan r)
        swap(matrix[row][l], matrix[r])
        l -> l + 1
        r -> r + 1
  </pre>
</details>
