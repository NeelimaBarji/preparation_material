### 19. Remove Nth Node from End of List

Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
```
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
```
Example 2:
```
Input: head = [1], n = 1
Output: []
```
Example 3:
```
Input: head = [1,2], n = 1
Output: [1]
```

Constraints:

- The number of nodes in the list is sz.
- 1 <= sz <= 30
- 0 <= Node.val <= 100
- 1 <= n <= sz

*Follow up*: Could you do this in one pass?

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>The same two pointer technique also keep track of length of nodes.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    dummy -> ListNode()
    l -> dummy
    r -> head
    while n greaterThan 0 and r not equal null
      r -> r.next
      n -> n - 1
    while r not equal null
      l -> l.next
      r -> r.next
    l.next -> l.next.next
    return dummy.next
  </pre>
</details>
