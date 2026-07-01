import org.w3c.dom.Node;

public class reorderList {
    // public class ListNode {
    // int val;
    // ListNode next;

    // ListNode() {
    // }

    // ListNode(int val) {
    // this.val = val;
    // }

    // ListNode(int val, ListNode next) {
    // this.val = val;
    // this.next = next;
    // }
    // }
    static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public void setNext(ListNode node) {
            this.next = node;
        }

        public ListNode getNext() {
            return this.next;
        }
    }

    public static void ReorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = slow.next;
        ListNode prev = slow.next = null;
        while (second != null) {
            ListNode tmp = second.next;
            second.next = prev;
            prev = second;
            second = tmp;
        }
        ListNode first = head;
        second = prev;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }

    public static void main(String[] args) {
        ListNode l5 = new ListNode(5);
        ListNode l4 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l1 = new ListNode(1);
        l1.setNext(l2);
        l2.setNext(l3);
        l3.setNext(l4);
        l4.setNext(l5);
        ReorderList(l1);
        printLinkedList(l1);
    }

    public static void printLinkedList(ListNode node) {
        ListNode top = node;
        while (top != null) {
            System.out.print(top.val + " ");
            top = top.getNext();
        }
    }
}