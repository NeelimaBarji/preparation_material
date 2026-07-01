### 435. Non-Overlapping Intervals (Medium)

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Example 1:
```
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
```
Example 2:
```
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
```
Example 3:
```
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
```

Constraints:

- 1 <= intervals.length <= 10^5
- intervals[i].length == 2
- -5 * 104 <= starti < endi <= 5 * 10^4

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Sort it and keep track of previous end of every interval if it is less than or equal to current start then change previous end else takes the minimum of previous end and make the result + 1. Finally return the result.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    sort(intervals)
    result -> 0
    previousEnd -> intervals.at([0][1])
    for i -> 1 to intervals.length
      start -> intervals.at([i][0]), end -> intervals.at([i][1])
      if start greaterThanOrEqualTo previousEnd
        previousEnd -> end
      else
        result -> result + 1
        previousEnd -> minimum(previousEnd, end)
    return result
  </pre>
</details>
