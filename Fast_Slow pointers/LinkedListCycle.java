
// LinkedList Cycle (easy)
class LinkedListCycle {

    // Time Complexity: Once the slow pointer enters the cycle, the fast pointer will meet the slow pointer in the same loop. Therefore, the
    // time complexity of our algorithm will be O(N) where ‘N’ is the total number of nodes in the LinkedList.
    // Space Complexity: The algorithm runs in constant space O(1).
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // find the LinkedList cycle
        while (fast != null && fast.next != null) {
            fast = fast.next.next; // fast pointer
            slow = slow.next; // slow pointer
            // compare pointers
            if (slow == fast) {
                return true; // found the cycle
            }
        }

        return false;
    }


    public static void main(String[] args) {
        // create a LinkedList and assign values to it
        ListNode head = new ListNode(1);
        //////////////////////////////////////
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head)); // false
        //////////////////////////////////////////////////////////////////////////////
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head)); // true
        //////////////////////////////////////////////////////////////////////////////
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head)); // true
    }

}