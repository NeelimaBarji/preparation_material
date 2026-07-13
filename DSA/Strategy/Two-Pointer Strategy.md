The Two-Pointer Strategy is a classic technique used to optimize array or string problems. Instead of using nested loops (which often 
results in a slow $O(n^2)$ runtime), you maintain two separate index pointers that traverse the data structure simultaneously.
By moving the pointers based on specific conditions, you can often solve the problem in a single pass—bringing the runtime down to a 
much faster $O(n)$.

# How It Works: The Two Main Styles
Depending on the problem, your pointers will usually move in one of two patterns:

## 1. Opposite Ends (Converging)
You place one pointer at the very beginning (left = 0) and one at the very end (right = array.length - 1). 
They move toward each other until they meet.

Best for: Sorted arrays, finding pairs, or reversing elements.

Classic Problem: Two Sum when the input array is already sorted. If the sum of the elements at left and right is too small, 
you increment left to get a larger value. If the sum is too big, you decrement right to get a smaller value.

## 2. Slow & Fast (Sliding Window / Runner)
Both pointers start at the same side, but they move at different speeds or under different conditions.

Best for: Linked lists (finding cycles or the midpoint) or removing duplicates in-place.

Classic Problem: Detecting a cycle in a linked list. The "fast" pointer moves two steps at a time while the "slow" pointer moves one.
If there is a loop, the fast pointer will eventually lap the slow pointer and they will meet.

### A Concrete Example: Pair Sum
Let's look at how the Opposite Ends style works visually.

Problem: Find if there are two numbers in a sorted array that add up to a target of 13.


Array:  [ 2,  4,  7,  9,  11, 15 ]
          ↑                   ↑
        Left                 Right
        (idx 0)              (idx 5)

Step 1: Left points to 2, Right points to 15.$2 + 15 = 17$.17 is greater than our target (13). Because the array is sorted, 
we know 15 is too large to pair with anything else to make 13. We move Right inward.

Step 2: Left points to 2, Right points to 11.$2 + 11 = 13$.Match found! We return the indices.If we had used a nested loop, we might have checked every single 
combination ($2+4$, $2+7$, $2+9$, etc.). With two pointers, we found it instantly because the sorted nature of the array allowed us to s
afely skip unnecessary checks.

The Checklist: When to use it?You should immediately think of the two-pointer approach if your problem has these traits:The data structure 
is linear (an array, string, or linked list).The array is sorted (or you can sort it without ruining the required output).You are looking 
for pairs, triplets, or sub-arrays that match a specific criteria.




