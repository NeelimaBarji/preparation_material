# Sliding Window Technique

Sliding window is a technique for solving problems that involve **contiguous subarrays or substrings**, usually asking for a max/min/count that satisfies some condition. It avoids the brute-force O(n²) or O(n³) approach of checking every possible subarray by reusing work from the previous window instead of recomputing from scratch.

## When to spot it

Look for these signals in the problem statement:
- "contiguous subarray/substring"
- "longest/shortest/maximum/minimum... satisfying condition X"
- Involves an array or string, and a brute force would look like nested loops checking every range

Examples: "longest substring without repeating characters," "maximum sum subarray of size k," "smallest subarray with sum ≥ target," "longest substring with at most K distinct characters."

## The core idea

Maintain two pointers, `left` and `right`, that define a window `[left, right]` into the array. Expand the window by moving `right` forward, and shrink it by moving `left` forward — each element enters and leaves the window at most once, so total work is O(n) instead of O(n²).

There are two flavors:

### 1. Fixed-size window
The window size `k` is given upfront.

```java
// Max sum of any subarray of size k
public int maxSumSubarray(int[] nums, int k) {
    int windowSum = 0, maxSum = 0;
    for (int i = 0; i < nums.length; i++) {
        windowSum += nums[i];              // add new element
        if (i >= k - 1) {
            maxSum = Math.max(maxSum, windowSum);
            windowSum -= nums[i - k + 1];  // remove element leaving window
        }
    }
    return maxSum;
}
```

### 2. Variable-size window
The window grows and shrinks based on a condition (this is the more common interview flavor).

```java
// Longest substring without repeating characters
public int lengthOfLongestSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int left = 0, maxLen = 0;
    for (int right = 0; right < s.length(); right++) {
        while (window.contains(s.charAt(right))) {
            window.remove(s.charAt(left));
            left++;                          // shrink from the left
        }
        window.add(s.charAt(right));
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}
```

## The mental template (memorize this shape)

```
left = 0
for right in range(n):
    add nums[right] to window          # expand
    while window is invalid:           # shrink until valid again
        remove nums[left] from window
        left += 1
    update answer using current window # window is now valid
```

Almost every variable-window problem is this skeleton with a different "add," "invalid," and "update" rule.

## How to practice it as a beginner

1. **Start with fixed-size problems** (max sum subarray of size k) to get comfortable with the add/remove bookkeeping.
2. **Move to variable-size problems** where you track a running state (a frequency map, a sum, a count of distinct characters) and shrink the window when that state violates a constraint.
3. **Always ask two questions** for a new problem: *What makes a window "invalid"?* and *What data structure do I need to detect that in O(1)?* (usually a `HashMap<Character, Integer>` or an array-based frequency count for strings).
4. **Classic problems to drill, in order**: Maximum sum subarray of size k → Longest substring without repeating characters → Minimum window substring → Longest substring with at most K distinct characters → Fruits into baskets → Permutation in a string.

Once the add/shrink/update skeleton feels automatic, you'll recognize sliding window problems in interviews almost instantly from the phrasing alone.
