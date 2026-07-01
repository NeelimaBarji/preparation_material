class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution3:
    # reverse and merge slow and fast pointer
    def solve(self, head: ListNode) -> None:
        slow, fast = head, head.next
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        second = slow.next
        prev = slow.next = None
        while second:
            tmp = second.next
            second.next = prev
            prev = second
            second = tmp
        first, second = head, prev
        while second:
            tmp1, tmp2 = first.next, second.next
            first.next = second
            second.next = tmp1
            first, second = tmp1, tmp2
        res = head
        while res:
            print(res.val)
            res= res.next

l10 = ListNode(10)
l9 = ListNode(9, l10)
l8 = ListNode(8, l9)
l7 = ListNode(7, l8)
l6 = ListNode(6, l7)
l5 = ListNode(5, l6)
l4 = ListNode(4, l5)
l3 = ListNode(3, l4)
l2 = ListNode(2, l3)
l1 = ListNode(1, l2)
print(Solution3().solve(l1))