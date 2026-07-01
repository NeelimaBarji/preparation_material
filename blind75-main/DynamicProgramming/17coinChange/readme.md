### 322. Coin Change

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

Example 1:
```
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
```
Example 2:
```
Input: coins = [2], amount = 3
Output: -1
```
Example 3:
```
Input: coins = [1], amount = 0
Output: 0
```

Constraints:

- 1 <= coins.length <= 12
- 1 <= coins[i] <= 2^31 - 1
- 0 <= amount <= 10^4

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Try to solve it using BreadFirst search approach with Dynammic programming.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Iterate in range of amount + 1 and in each iteration minus it with each coin in arr if it is greater than or equal 0 then store it in dp by checking currmin and currvar amount.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    dp -> arr.lenOf(amount+1).fillWith(amount + 1)
    dp[0] -> 0
    for am -> 0 to amount+1
      for each coin in coins
        if am - coin greaterThanOrEqual 0
          dp[am] -> if dp[am] isLessThan 1 + dp[am - coin] ? dp[am] : 1 + dp[am - coin]
    return if dp[amount] != amount + 1 ? dp[amount] : -1
  </pre>
</details>
