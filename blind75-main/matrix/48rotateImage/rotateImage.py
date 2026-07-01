class Solution:
    def rotate(self, matrix: list[list[int]]) -> list[list[int]]:
        matrixLength = len(matrix)
        for row in range(matrixLength):
            for col in range(row, matrixLength):
                matrix[row][col], matrix[col][row] = matrix[col][row], matrix[row][col]
        for row in range(len(matrix)):
            l, r = 0, matrixLength - 1
            while l < r:
                matrix[row][l], matrix[row][r] = matrix[row][r], matrix[row][l]
                l += 1
                r -= 1
        return matrix
print(Solution().rotate([[1,2,3,4], [5,6,7,8], [9,10,11,12], [13,14,15,16]]))