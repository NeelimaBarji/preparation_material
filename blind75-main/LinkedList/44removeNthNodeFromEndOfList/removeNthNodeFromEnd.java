
//  Definition for singly-linked list.

class removeNthNodeFromEnd {
    // static class ListNode {
    // private int val;
    // private Node next;

    // public Node(int val) {
    // this.val = val;
    // }

    // public void setNext(Node node) {
    // this.next = node;
    // }

    // public Node getNext() {
    // return this.next;
    // }
    // }
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode l = dummy;
        // dummy.next = head;
        ListNode r = head;
        while (n > 0 && r != null) {
            r = r.next;
            n -= 1;
        }
        while (r != null) {
            l = l.next;
            r = r.next;
        }
        l.next = l.next.next;
        return dummy.next;
    }
}