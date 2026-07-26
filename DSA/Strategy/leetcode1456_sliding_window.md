# LeetCode 1456 — Maximum Number of Vowels in a Substring of Given Length

## Problem
Given a string `s` and an integer `k`, return the maximum number of vowel letters
in any substring of `s` with length `k`.

## Approach: Sliding Window
This is a classic fixed-size sliding window problem, O(n) time:
1. Expand the window by adding `s.charAt(i)` to a running vowel count.
2. Once the window reaches size `k`, record the max count seen so far.
3. Before sliding forward, remove the character leaving the window
   (`s.charAt(i - k + 1)`) from the count if it was a vowel.

## Original Solution
```java
class Solution {
    public int maxVowels(String s, int k) {
        List<Character> vowel = List.of('a','e','i','o','u');
        int count=0;
        int maxcount=0;
        for(int i=0; i< s.length(); i++){
            if(vowel.contains(s.charAt(i))){
                count++;
            }
            if(i>=k-1){
                maxcount = Math.max(count, maxcount);
                if(vowel.contains(s.charAt(i-k+1)))
                    count--;
            }
        }
        return maxcount;
    }
}
```

**Runtime: ~40ms**

## Why It Was Slow
The algorithm's *complexity* is already optimal — O(n) sliding window. The 40ms
came from constant-factor overhead in the vowel check:

- `s.charAt(i)` returns a primitive `char`, but `List<Character>.contains()`
  forces **autoboxing** to `Character` on every call.
- `.contains()` on a `List` does a **linear scan** with `.equals()` — up to 5
  comparisons per call, each with boxing overhead.
- This check runs **twice per iteration** (once for the entering char, once
  for the exiting char), so up to 10 boxed comparisons per loop iteration.

This overhead dwarfs the actual sliding-window bookkeeping.

## Optimized Solution
Replace the vowel lookup with a primitive `switch` statement (no boxing, no
list traversal) and use `toCharArray()` for direct array access:

```java
class Solution {
    private static boolean isVowel(char c) {
        switch (c) {
            case 'a': case 'e': case 'i': case 'o': case 'u':
                return true;
            default:
                return false;
        }
    }

    public int maxVowels(String s, int k) {
        int count = 0;
        int maxcount = 0;
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (isVowel(arr[i])) count++;
            if (i >= k - 1) {
                maxcount = Math.max(count, maxcount);
                if (isVowel(arr[i - k + 1])) count--;
            }
        }
        return maxcount;
    }
}
```

### Why This Is Faster
- `switch` on a primitive `char` compiles to a jump table / simple integer
  comparisons — no boxing, no object allocation, no list traversal.
- `toCharArray()` avoids repeated bounds-checked method calls through
  `String.charAt()`.

## A Note on LeetCode Runtimes
Java runtime numbers on LeetCode are heavily influenced by **JVM startup and
class-loading overhead**, especially for small inputs. A 40ms vs 4ms
difference on a problem like this is often mostly harness noise, not a real
algorithmic difference. Don't over-optimize chasing leaderboard numbers —
correctness and clean O(n) complexity matter most. That said, swapping
`List<Character>.contains()` for a `switch` is a legitimate, meaningful
micro-optimization regardless of the leaderboard noise.
