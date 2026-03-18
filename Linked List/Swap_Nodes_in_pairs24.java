class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        dummy.next = head;

        ListNode c1;
        ListNode c2;

        while(temp.next != null && temp.next.next != null)
        {
            c1 = temp.next;
            c2 = temp.next.next;

            c1.next = c2.next;
            c2.next = c1;

            temp.next = c2;
            temp = c1;
        }
        return dummy.next;
    }
}