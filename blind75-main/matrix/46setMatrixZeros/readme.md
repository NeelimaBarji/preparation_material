### 63. Set Matrix Zeroes

Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

Example 1:
```
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
```
Example 2:
```
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
```

Constraints:

- m == matrix.length
- n == matrix[0].length
- 1 <= m, n <= 200
- -231 <= matrix[i][j] <= 231 - 1

*Follow up:*
- A straightforward solution using O(mn) space is probably a bad idea.
- A simple improvement uses O(m + n) space, but still not the best solution.
- Could you devise a constant space solution?

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Use Row and column array as a indicator to make which row and which column will be zero. Still it is not O(1) space.</p>
</details>

---
<details>
  <summary>
    <h3>
      Show Hint 2
    </h3>
  </summary>
  <p>Traverse each row and every column, first make the row zero and to make column zero mark it in a first row and then traverse for each column every row to make column zero in place. You have to only use boolean in this method so it is O(1).</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    Rows -> lengthOf(matrix)
    Cols -> lengthOf(matrix[0])
    rowBool -> false
    oneRow -> false
    for row -> 0 to Rows
      for column -> 0 to Cols
        if matrix[row][column] equals 0
          matrix[0][column] = 0
          if row greaterThan 0
            rowBool -> true
          else
            oneRow -> true
      if rowBool
        rowBool -> false
        fillArrays(matrix[row], 0)
    for column -> 0 to Cols
      if matrix[0][column] equals 0
        for row -> 0 to Rows
          matrix[row][column] -> 0
    if oneRow
      fillArrays(matrix[0], 0)
    // don't have to return anything
  </pre>
</details>
