class Solution:
    def insertInterval(self, interval: list[list[int]], newInterval: list[int]) -> list[list[int]]:
        result = []
        for i in range(len(interval)):
            if newInterval[1] < interval[i][0]:
                result.append(newInterval)
                return result + interval[i:]
            elif newInterval[0] > interval[i][1]:
                result.append(interval[i])
            else:
                newInterval[0] = min(newInterval[0], interval[i][0])
                newInterval[1] = max(newInterval[1], interval[i][1])
        result.append(newInterval)
        return result
print(Solution().insertInterval([[1,3], [6,9]], [2,5]))