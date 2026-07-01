# 56. Merge Intervals

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
```
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
```
Example 2:
```
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
```

Constraints:

- 1 <= intervals.length <= 10*4
- intervals[i].length == 2
- 0 <= starti <= endi <= 10*4

<details>
  <summary><h3>Show Hint</h3></summary>
  <p>It is similar to insert intervals problem. First here we sort based on first value of intervals by checking a condition whether last inserted interval last time lesser than current inserting interval's first element then merge it else take the minimum of first on both interval and max on last.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    intervals -> intervals.sort()
    merged -> list()
    for each interval in intervals
      if merged.isEmpty() or merged.lastELemments(1) isLessThan interval.at(0)
        merged.add(intervals)
      else
        merged.getLast(1) -> maximum(merged.getLast(1), interval.at(1))
    return merged.toArray()
  </pre>
</details>
