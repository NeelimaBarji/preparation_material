
class Solution:
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
        groupAnagram = collections.defaultdict(list)
        forIndex = ord('a')
        for word in strs:
            count = [0] * 26
            for ch in word:
                count[ord(ch) - forIndex] += 1
            groupAnagram[tuple(count)].append(word)
        return list(groupAnagram.values())
print(Solution().groupAnagrams(["eat", "tea", "tan", "ate", "nat", "bat"]))