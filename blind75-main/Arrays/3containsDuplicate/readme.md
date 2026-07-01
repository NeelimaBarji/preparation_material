## 217. Contains Duplicate

Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

Example 1:
```
Input: nums = [1,2,3,1]
Output: true
```

Example 2:
```
Input: nums = [1,2,3,4]
Output: false
```
Example 3:
```
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
```

Constraints:

1 <= nums.length <= 105\
-10^9 <= nums[i] <= 10^9

<details>
  <summary><h3>
    Show Hint 1
  </h3></summary>
  <p>As usual, the most brute-forced way is to use two loops: one inner and one outer loop to find duplicate elements. So, try to think of a somewhat better way. There are many ways to solve this; one is to sort the array, which would take O(nlogn), and then check for contiguous repetitive elements.</p>
</details>

---
<details>
  <summary>
    <h3>
      Show Hint 2
    </h3>
  </summary>
  <p>Another way, I don't wanna say. Try to use a data structure that could solve this problem in a linear time. Just try to think. If you don't get it, then open hint 3</p>
</details>

---
<details>
  <summary>
    <h3>Show Hint 3</h3>
  </summary>
  <p>You can use a HashTable or HashSet to solve this problem in linear time by storing the visited elements in a HashSet or HashTable as keys for each iteration. By checking if the element already exists in the HashSet, you can return true if it does, and false if it doesn't. Finally, after the completion of the loop.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    countSet -> hashSet()
    for each number in array
      if number exists in keyhs(countSet) then
        return true
      countSet.key(number).value(1)
    return false
  </pre>
</details>
