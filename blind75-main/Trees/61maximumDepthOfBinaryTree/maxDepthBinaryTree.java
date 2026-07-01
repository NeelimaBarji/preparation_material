public class maxDepthBinaryTree {
    static int findDepth(tree node) {
        if (node == null)
            return 0;
        int maxLeft = findDepth(node.left);
        int maxRight = findDepth(node.right);
        return (maxLeft > maxRight ? maxLeft : maxRight) + 1;
    }

    public static void main(String[] args) {
        tree node = new tree(3);
        node.right = new tree(20);
        tree root = node;
        int result = findDepth(root);
        System.out.println(result);
    }
}

class tree {
    int root;
    tree left, right;

    tree() {
    }

    tree(int root) {
        this.root = root;
    }

    tree(int root, tree left, tree right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }
}