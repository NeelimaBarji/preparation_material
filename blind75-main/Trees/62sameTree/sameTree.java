public class sameTree {

    public static boolean isSameTree(tree p, tree q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    public static void main(String[] args) {
        tree root = new tree(1, new tree(2), new tree(3));
        tree root2 = new tree(1, new tree(2), new tree(3));
        System.out.println(isSameTree(root, root2));
    }
}

class tree {
    int val;
    tree left, right;

    tree() {
    }

    tree(int val) {
        this.val = val;
    }

    tree(int val, tree left, tree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}