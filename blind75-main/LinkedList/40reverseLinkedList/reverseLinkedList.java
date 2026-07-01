
//  * Definition for singly-linked list.
// class ListNode {
//     int val;
//     ListNode next;

//     ListNode() {
//     }

//     ListNode(int val) {
//         this.val = val;
//     }

//     ListNode(int val, ListNode next) {
//         this.val = val;
//         this.next = next;
//     }
// }

public class reverseLinkedList {
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

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next_node = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next_node;
        }
        return prev;
    }

    public static void printLinkedList(ListNode node) {
        ListNode top = node;
        while (top != null) {
            System.out.print(top.val + " ");
            top = top.next;
        }
    }

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n1 = new ListNode(1);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        ListNode reversed = reverseList(n1);
        System.out.println();
        printLinkedList(reversed);
    }
}