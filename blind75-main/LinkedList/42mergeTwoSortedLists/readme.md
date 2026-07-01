### 21. Merge Two Sorted Lists

You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

Example 1:
```
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
```
Example 2:
```
Input: list1 = [], list2 = []
Output: []
```
Example 3:
```
Input: list1 = [], list2 = [0]
Output: [0]
```

Constraints:

- The number of nodes in both lists is in the range [0, 50].
- -100 <= Node.val <= 100
- Both list1 and list2 are sorted in non-decreasing order.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Use the merge sort approach create an empty node and make the next smallest element.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    dummy -> ListNode()
    tail -> dummy
    while l1 not equals null and l2 not equals null
      if l1.val isLessThan l2.val
        tail.next -> l1
        l1 -> l1.next
      else
        tail.next -> l2
        l2 -> l2.next
    if l1 not equals null
      tail.next -> l1
    else if l2 not equals null
      tail.next -> l2
    return dummy.next
  </pre>
</details>
