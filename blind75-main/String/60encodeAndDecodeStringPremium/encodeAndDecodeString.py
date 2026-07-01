# can solve in LintCode 659 Medium facebook (free platform of leetcode is LintCode)
# Design an algorithm to encode  a list of strings to a straight. The encoded string is then sent over the network 
# and is decoded back to the original list strings. Please encode and decode 
# ["lint", "code", "love", "you"] -> output: ["lint", "code", "love", "you"]
# delimeters 
class Solution:
    def encode(slef, strs):
        res = ""
        for s in strs:
            res += str(len(s)) + "#" + s
        return res
    
    def decode(self, strs):
        res, i = [], 0
        
        while i < len(strs):
            j = i 
            while strs[j] != "#":
                j += 1
            length = int(strs[i:j])
            res.append(strs[j + 1: j + 1 + length])
            i = j + 1 + length
        
        return res
print(Solution().encode(["neet", "co/'{12}#de"]))
print(Solution().decode("4#neet11#co/'{12}#de"))