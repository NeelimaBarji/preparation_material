### 226. Invert Binary Tree (Easy)

Given the root of a binary tree, invert the tree, and return its root.

Example 1:
```
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
```
Example 2:
```
Input: root = [2,1,3]
Output: [2,3,1]
```
Example 3:
```
Input: root = []
Output: []
```

Constraints:

- The number of nodes in the tree is in the range (0, 100).
- -100 <= Node.val <= 100

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Traverse tree swap the left node with right by setting the current left to current right and its vice versa and finally return the root node.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    if root equals null
      return null
    left -> root.left
    right -> root.right
    root.left -> invertTree(right)
    root.right -> invertTree(left)
    return root
  </pre>
</details>
