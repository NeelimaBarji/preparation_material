### 20. Valid Parentheses

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:
```
Input: s = "()"
Output: true
```
Example 2:
```
Input: s = "()[]{}"
Output: true
```
Example 3:
```
Input: s = "(]"
Output: false
```

Constraints:

- 1 <= s.length <= 10^4
- s consists of parentheses only '()[]{}'.

<details>
  <summary><h3>Show Hint 1</h3></summary>
  <p>Use stack, when it is opening bracket push it to the stack and pop when it is the closing bracket and same bracket as opening bracket.</p>
</details>

---
<details>
  <summary><h3>Show Hint 2</h3></summary>
  <p>You can return false it closing bracket didn't match else finally return false if stack length is greater than 0 else return true.</p>
</details>

---
<details>
  <summary><h3>Pseudocode</h3></summary>
  <pre>
    stack -> Stack()
    for each character in s
      if character equals "{" or "(" or "[" then stack.push(character)
      else if stack.length greaterThan 0 and (stackLastPushedElement equals "{" and character equals "}" or stackLastPushedElement equals "[" and character equals "]" or stackLastPushedElement equals "(" and character equals ")"
        stack.pop()
      else return false
    if stack.length greaterThan 0 then return false
    else return true
  </pre>
</details>
