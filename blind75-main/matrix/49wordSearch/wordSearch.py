class Solution:
    def search(self, board: list[list[str]], word: str) -> bool:
        ROWS, COLS = len(board), len(board[0])
        path = set()
        def backTrack(r, c, idx):
            if idx == len(word):
                return True
            if (r < 0 or c < 0 or r >= ROWS or c >= COLS or word[idx] != board[r][c] or (r,c) in path):
                return False
            path.add((r,c))
            res = (backTrack(r + 1, c, idx + 1) or
                   backTrack(r - 1, c, idx + 1) or
                   backTrack(r, c + 1, idx + 1) or
                   backTrack(r, c - 1, idx + 1))
            path.remove((r,c))
            return res
        for r in range(ROWS):
            for c in range(COLS):
                if backTrack(r, c, 0):
                    return True
        return False
print(Solution().search([["A", "Z", "O", "L"], ["N", "E", "V", "P"], ["W", "Q", "D", "X"]], "LOVE"))