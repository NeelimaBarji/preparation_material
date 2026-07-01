class Solution:
    def merge(self, intervals: list[list[int]]) -> list[list[int]]:
        intervals.sort(key = lambda i : i[0])
        result = [intervals[0]]
        for start, end in intervals[1:]:
            lastEnd = result[-1][1]
            if start <= lastEnd:
                result[-1][1] = max(lastEnd, end)
            else:
                result.append([start, end])
        return result
print(Solution().merge([[1,4],[0,0]]))