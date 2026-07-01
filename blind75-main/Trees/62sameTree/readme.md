### 100. Same Tree (Easy)

Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
```
Input: p = [1,2,3], q = [1,2,3]
Output: true
```
Example 2:
```
Input: p = [1,2], q = [1,null,2]
Output: false
```
Example 3:
```
Input: p = [1,2,1], q = [1,1,2]
Output: false
```

Constraints:

- The number of nodes in both trees is in the range (0, 100).
- -104 <= Node.val <= 10^4

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>It is like a tree traversal, but here it is 2 tree. if both nodes of tree becomes null then return true and if any of the tree node becomes null or values of the node doesn't equal then return false finally call it in dual recurse.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    if p is null and q is null
      return true
    if p is null or q is null or p.val notEqualTo q.val
      return false
    return (isSameTree(p.left, q.left) and (isSameTree(p.right, q.right)))
  </pre>
</details>
