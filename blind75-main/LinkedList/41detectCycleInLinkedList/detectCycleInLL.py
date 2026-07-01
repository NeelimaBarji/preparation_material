# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def hasCycle(self, head: ListNode) -> bool:
        if not head or not head.next:
            return False
        l, r = head, head
        while r and r.next:
            l = l.next
            r = r.next.next
            if l == r:
                return True
        return False
# e1 = ListNode(-4)
# e2 = ListNode(0, e1)
e3 = ListNode(2)
e4 = ListNode(1, e3)
e3.next = e4
print(Solution().hasCycle(e4))
e1 = ListNode(-4)
e2 = ListNode(0, e1)
e3 = ListNode(2, e2)
e4 = ListNode(1, e3)
e1.next = e1
print(Solution().hasCycle(e4))