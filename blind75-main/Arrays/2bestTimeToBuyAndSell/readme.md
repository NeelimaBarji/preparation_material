## 121. Best Time to Buy and Sell stock

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:
```
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
```
Example 2:
```
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 ```

Constraints:

1 <= prices.length <= 10^5\
0 <= prices[i] <= 10^4

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>To gain maximum profit, you have to buy at the minimum price and sell when it's at the maximum price. So, think of the problem as tracking those minimum and maximum values.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>Try solving it using the two-pointer concept.</p>
</details>

---
<details>
  <summary><h3>Show Hint 3</h3></summary>
  <p>Keep track of the minimum using one pointer and the maximum using another pointer. Also, track the maximum profit; update it only if the profit increases by changing the pointer. If the price at the 'r' pointer becomes less than the price at the 'l' pointer, shift 'r' to 'l' and increment 'r'. If the profit increases by doing this, update the max profit; otherwise, do not update it.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    l -> 0
    r -> 1
    maxProfit -> 0
    for [i -> 1 to length(prices)]
      if prices[l] < prices[r]
           newProfit -> prices[r] - prices[l]
           maxProfit -> if newProfit > maxProfit then newProfit else maxProfit
      else
           l -> r
      r ++
    return maxProfit
  </pre>
</details>
