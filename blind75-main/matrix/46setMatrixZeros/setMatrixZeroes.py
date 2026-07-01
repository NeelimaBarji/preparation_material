class Solution:
    def setZeroes(self, matrix: list[list[int]]) -> None:
        rowBool = False
        oneRow = False
        ROWS = len(matrix)
        COLS = len(matrix[0])
        for row in range(ROWS):
            for col in range(COLS):
                if matrix[row][col] == 0:
                    # matrix[row][0] = 0
                    matrix[0][col] = 0
                    if row > 0:
                        rowBool = True
                    else:
                        oneRow = True
            if rowBool:
                rowBool = False
                matrix[row] = [0] * COLS
        for col in range(COLS):
            if matrix[0][col] == 0:
                for row in range(ROWS):
                    matrix[row][col] = 0
        if oneRow:
            matrix[0] = [0] * COLS
        # for row in range(len(matrix)):
        #     if matrix[row][0] == 0:
        #         matrix[row] = [0] * len(matrix[0])
        print(matrix)
print(Solution().setZeroes([[0,1,2,0],[3,4,5,2],[1,3,1,5]]))
print(Solution().setZeroes([[1,0,1],[1,1,1],[0,1,1]]))
print(Solution().setZeroes([[1,1,1],[1,0,1],[1,1,1]]))
print(Solution().setZeroes([[0,1,1,0],[1,1,1,1],[1,1,1,1]]))
class Solution2:
    def setZero(self, matrix: list[list[int]]) -> None:
        pass
    
# 101 111 011