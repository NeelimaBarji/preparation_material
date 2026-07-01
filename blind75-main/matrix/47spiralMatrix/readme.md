### 54. Spiral Matrix

Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
```
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
```
Example 2:
```
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
```

Constraints:

- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 10
- -100 <= matrix[i][j] <= 100

<details>
  <summary>
    <h3>
      Show Hint 1
    </h3>
  </summary>
  <p>Do it like a simulation. You have to use top, right, bottom, left. Initiate top and left with 0 and right and bottom with 1. Loop and left less than or equal to right and same with top and bottom.</p>
</details>

---
<details>
  <summary>
    <h3>
      Show Hint 2
    </h3>
  </summary>
  <p>If you see the way first you have to go boundary by boundary and then inwards. on each iteration from left to right and then increment top, next top to bottom and then decrement right, next right to left and then decrement bottom, finally bottom to top and then increment left.</p>
</details>

---
<details>
  <summary>
    <h3>
      Pseudocode
    </h3>
  </summary>
  <pre>
    res -> list()
    left -> 0
    top -> 0
    right -> lengthOf(matrix[0])
    bottom -> lengthOf(matrix)
    while left lessThanOrEqualTo right and top lessThanOrEqualTo bottom
      for i -> left to right
        res.add(matrix[top][i])
      top -> top + 1
      for i -> top to bottom
        res.add(matrix[i][right])
      right -> right - 1
      if sizeOf(res) equals (lengthOf(matrix[0]) * lengthOf(matrix))
        break
      for i -> right to left
        res.add(matrix[bottom][i])
      bottom -> bottom - 1
      for i -> bottom to top
        res.add(matrix[i][left])
      left -> left + 1
    return res
  </pre>
</details>
