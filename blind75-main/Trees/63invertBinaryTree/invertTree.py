class TreeNode:
    def __init__(self, val = 0, left = None, right = None):
        self.val = val
        self.left = left
        self.right = right
    
class Solution:
    def invertTree(self, root: TreeNode) -> TreeNode:
        if not root:
            return None
        left = root.left
        right = root.right 
        root.left = self.invertTree(right)
        root.right = self.invertTree(left)
        return root
