class Solution:
    def nonOverLap(self, intervals: list[list[int]]) -> int:
        intervals.sort()
        result = 0
        prevEnd = intervals[0][1]
        for start, end in intervals[1:]:
            if start >= prevEnd:
                prevEnd = end
            else:
                result += 1
                prevEnd = min(end, prevEnd)
        return result
print(Solution().nonOverLap([[1,2],[2,3],[3,4],[1,3]]))