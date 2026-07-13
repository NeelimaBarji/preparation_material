# Two-Pointer Strategy

The two-pointer technique uses two index variables that move through a data structure (usually a sorted array or string) to avoid nested loops, cutting O(n²) brute force down to O(n).

There are three common patterns:

## 1. Opposite Ends (Converging Pointers)

One pointer starts at the beginning, one at the end, and they move toward each other. Classic problem: find two numbers in a *sorted* array that sum to a target.

```python
def two_sum_sorted(arr, target):
    left, right = 0, len(arr) - 1
    while left < right:
        s = arr[left] + arr[right]
        if s == target:
            return [left, right]
        elif s < target:
            left += 1   # need a bigger sum
        else:
            right -= 1  # need a smaller sum
    return []
```

The key insight: because the array is sorted, you can safely discard one element per step.

**Also used for:** valid palindrome check, reversing an array, Container With Most Water, 3Sum.

## 2. Same Direction (Fast and Slow)

Both pointers start at the beginning; the fast one scans, the slow one marks where to write or a position of interest. Classic problem: remove duplicates from a sorted array in place.

```python
def remove_duplicates(arr):
    slow = 0
    for fast in range(1, len(arr)):
        if arr[fast] != arr[slow]:
            slow += 1
            arr[slow] = arr[fast]
    return slow + 1  # length of unique portion
```

**Also used for:** moving zeroes to the end, linked list cycle detection (Floyd's algorithm, where fast moves 2 steps per slow's 1), finding the middle of a linked list.

## 3. Two Sequences

One pointer per array, advancing whichever is "behind." Classic problem: merging two sorted arrays, or checking if one string is a subsequence of another.

```python
def is_subsequence(s, t):  # is s a subsequence of t?
    i = 0
    for ch in t:
        if i < len(s) and s[i] == ch:
            i += 1
    return i == len(s)
```

## How to Recognize a Two-Pointer Problem

Look for:

- Sorted input (or input you can sort)
- Questions about pairs/triplets with a sum condition
- Palindromes
- In-place array modification
- Comparing two sequences

If your brute force is a nested loop comparing pairs, ask: "can sorted order let me eliminate candidates from one end?" — if yes, two pointers likely works.

## Beginner Tips

- Always define what each pointer *means* before coding (e.g., "everything left of `slow` is final").
- Be careful with loop conditions (`left < right` vs `left <= right`).
- Trace a small example by hand to check pointer movement.

## Starter Problems (in order)

1. Valid Palindrome
2. Two Sum II – Input Array Is Sorted
3. Remove Duplicates from Sorted Array
4. Move Zeroes
5. Squares of a Sorted Array
6. Container With Most Water
7. 3Sum
