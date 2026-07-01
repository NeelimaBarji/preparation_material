public class mergeTwoSorted {

    static class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public void setNext(Node node) {
            this.next = node;
        }

        public Node getNext() {
            return this.next;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(5);
        Node n3 = new Node(13);
        Node n4 = new Node(14);
        Node n5 = new Node(550);

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);

        Node n1_1 = new Node(2);
        Node n2_1 = new Node(15);
        Node n3_1 = new Node(130);
        Node n4_1 = new Node(220);
        Node n5_1 = new Node(350);

        n1_1.setNext(n2_1);
        n2_1.setNext(n3_1);
        n3_1.setNext(n4_1);
        n4_1.setNext(n5_1);

        // printLinkedList(n1);
        // System.out.println();
        // printLinkedList(n1_1);
        // Node merged = mergeList(n1, n1_1);
        // System.out.println();
        // printLinkedList(merged);

        Node dummy = new Node(0);
        Node tail = dummy;
        while (n1 != null && n1_1 != null) {
            if (n1.val < n1_1.val) {
                tail.next = n1;
                n1 = n1.next;
            } else {
                tail.next = n1_1;
                n1_1 = n1_1.next;
            }
            tail = tail.next;
        }
        if (n1 != null) {
            tail.next = n1;
        }
        if (n1_1 != null) {
            tail.next = n1_1;
        }
        System.out.println();
        printLinkedList(dummy.next);
    }

    public static Node mergeList(Node l1, Node l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            l1.next = mergeList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeList(l1, l2.next);
            return l2;
        }
    }

    public static void printLinkedList(Node node) {
        Node top = node;
        while (top != null) {
            System.out.print(top.val + " ");
            top = top.getNext();
        }
    }
}