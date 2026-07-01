### 371. Sum of Two Integers
Given two integers a and b, return the sum of the two integers without using the operators + and -.

Example 1:
```
Input: a = 1, b = 2
Output: 3
```
Example 2:
```
Input: a = 2, b = 3
Output: 5
```

Constraints:

- -1000 <= a, b <= 1000

**Note** : Bit Manipulation can be tough to understand at start. I suggest you to do logic gates on several numbers with mask and without mask and search for how to add, multiply using logic gates. You can do this section at last.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>A and B can be either positive or negative. Logic gates to use in this ^ & << and also mask for negative numbers</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Try to solve it recursive by adding bit by bit using xor and mask for addition and shift the bits to left by using leftshift with mask. Return when second element becomes zero. If the return valus is greater than 2**31 then it is a negative just return it's negative version of that result number.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    addVal(a, b, mask)  {
      if b == 0 then return a;
      return addVal((a^b) & mask, ((a&b)<<1) & mask, mask);
    }
    getSum(a, b) {
      mask -> 0xFFFFFFFF // it's value is 2**32-1
      value -> addVal(a, b, mask)
      if value greaterThan 2^31 then return ~(value ^ mask) else return value;
    }
  </pre>
</details>
