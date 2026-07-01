public class invertTree {
    static treeNode invertTreeNodes(treeNode root) {
        if (root == null)
            return null;
        treeNode left = root.left;
        treeNode right = root.right;
        root.left = invertTreeNodes(right);
        root.right = invertTreeNodes(left);
        return root;
    }
}

class treeNode {
    int val;
    treeNode left, right;

    treeNode() {
    }

    treeNode(int val) {
        this.val = val;
    }

    treeNode(int val, treeNode left, treeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}