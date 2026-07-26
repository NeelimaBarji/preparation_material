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



## 1. Separate "solving" from "explaining" as two passes
After you solve a problem, don't move on. Go back through your own code line by line and narrate:

"This loop runs n times, so that's O(n)."
"Inside it, I'm doing a .contains() on a list of size 5 — that's a constant, so it doesn't change the O(n) overall, but it does add a real constant-factor cost."
"I'm not using extra data structures that scale with input, so space is O(1)."

Doing this as a second pass, out loud (even alone), is what builds the reflex. Most people only think about complexity while solving, then never say it — so the muscle for producing it under interview pressure never forms.

## 2. Use a repeatable checklist while narrating
Every time, walk through:

How many times does each loop/recursive call run, and does it depend on n, k, or something else?
Are any operations inside the loop not O(1)? (list .contains(), string concatenation, sorting inside a loop, etc.)
What extra memory did I allocate — arrays, hash maps, recursion stack depth?
Could the answer be different for best/worst/average case?

This is exactly the gap in your maxVowels solution — the loop was correctly O(n), but the hidden O(1)-with-a-big-constant cost from List<Character>.contains() is the kind of thing this checklist catches and that interviewers listen for.

## 3. Practice on solutions you already know cold
Don't just do this on new problems — redo it on problems you solved days or weeks ago. The goal is fluency, not novelty. Pick 3 old solutions a week and re-derive complexity from scratch without looking at old notes.

## 4. Explain recursive and DP solutions with the recurrence, not just "it feels like O(n²)"
For recursion: write the recurrence relation (e.g., T(n) = T(n-1) + O(n)) and solve it, even roughly. For DP: state the number of states × cost per state (e.g., "n×k states, O(1) transition, so O(nk)"). Interviewers at senior level often push on why, not just what.

## 5. Record yourself or explain to someone/something else
Say it out loud to a rubber duck, a friend, or even into a voice memo, then listen back. You'll catch hand-wavy spots ("this is roughly O(n) I think") that you wouldn't catch just thinking it internally. This also builds the exact muscle used in a live interview, where you have to produce this analysis under a stranger's gaze in real time — silent understanding doesn't transfer automatically to spoken clarity under pressure.
