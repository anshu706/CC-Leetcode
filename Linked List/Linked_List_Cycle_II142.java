public class Linked_List_Cycle_II142 {
    public ListNode detectCycle(ListNode head) {
        ListNode f = head;
        ListNode s = head;

        while(f != null && f.next != null)
        {
            s = s.next;
            f = f.next.next;

            if(s == f)
            {
                ListNode start = head;
                while(start != s)
                {
                    start = start.next;
                    s = s.next;
                }
                return start;
            }
        }
        return null;
    }
}