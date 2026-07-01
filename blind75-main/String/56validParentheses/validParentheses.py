# leetcode 20
class Solution:
    def valid(self, s: str) -> bool:
        stack = []
        for i in s:
            if i == "{" or i == "(" or i == "[":
                stack.append(i)
            elif len(stack) > 0 and ((stack[len(stack) - 1] == "{" and i == "}") or (stack[len(stack) - 1] == "(" and i == ")") or (stack[len(stack) - 1] == "[" and i == "]")):
                stack.pop()
            else:
                return False
        if len(stack) > 0:
            return False
        else:
            return True
print(Solution().valid("()"))
print(Solution().valid("({)}"))
print(Solution().valid("()[]{}"))
print(Solution().valid("(]"))
# another solution
class Solution2:
    def valid2(self, s: str) -> str:
        stack = []
        closeToOpen = { ")" : "(", "}" : "{", "]" : "["}   
        for c in s:
            if c in closeToOpen:
                if stack and stack[-1] == closeToOpen[c]:
                    stack.pop()
                else:
                    return False
            else:
                stack.append(c)
            
        return True if not stack else False 
    
    