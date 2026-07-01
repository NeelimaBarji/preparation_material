class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def maxDepth(self, root: TreeNode) -> int:
        if (not root):
            return 0
        maxLeft = self.maxDepth(root.left)
        maxRight = self.maxDepth(root.right)
        return max(maxLeft, maxRight) + 1
