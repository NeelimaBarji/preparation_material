# Reverse Linked List - leetcode 206
# 1->2->3->4->5->NULL ; input -> ouput: 5->4->3->2->1->NULL
class ListNode:
    def __init__(self, val = 0, nexty=None):
        self.val = val
        self.nexty = nexty

class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        prev, curr = None, head
# T: O(n), M(1) : iterative
# T: O(n), M(n) : recursive
        while curr:
            nxt = curr.nexty
            curr.nexty = prev
            prev = curr
            curr = nxt
        
        return prev

    def recurse(self, head: ListNode) -> ListNode:
        if not head:
            return None
        newHead = head
        if head.nexty:
            newHead = self.recurse(head.nexty)
            head.nexty.nexty = head
        head.nexty = None

        return newHead