### 191. Number of 1 Bits

Write a function that takes the binary representation of an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).

Note:

Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input will be given as a signed integer type. It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3, the input represents the signed integer. -3.

Example 1:
```
Input: n = 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
```
Example 2:
```
Input: n = 00000000000000000000000010000000
Output: 1
Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
```
Example 3:
```
Input: n = 11111111111111111111111111111101
Output: 31
Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
```

Constraints:

- The input must be a binary string of length 32.
 

*Follow up*: If this function is called many times, how would you optimize it?

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Try to think how bits work in each incretion of their unit place. How to get their digit place. If you know how the number could be in each bit you can calculate whether there will be 1 or 0. I know it sounds confusing just open hint 2.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Now say the input is 8(1000 in binary). Any number greater than 1 has atleast 1 one in it. If you see the binary representation of 8 1 in 4th place which indicates 8 remaining 0's in 3,2 and 1st place which indicates 4, 2, 1 respectively. if you note that everything is in two power so that we could divide the number by 2 everytime for each digitplace in bit so. 8 % 2 is 0 add it to result and divide by to by right shift it by 1 8 >> 1 = 4. now 4 % 2 = 0 add it to (res = 0) then 4 >> 1 = 2, 2 % 2 = 0 + res (res = 0) then 2 >> 1 = 1 now 1 % 2 = 1 + res (res = 1) 1 >> 1 = 0 loop terminates. At the end 8 has 1 bit</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    res -> 0
    while n greaterThan 0
      res -> res + n % 2
      n -> n >> 1
    return res
  </pre>
</details>
