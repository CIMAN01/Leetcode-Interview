
// Similar to LinkedListCycle: Given the head of a LinkedList with a cycle, find the length of the cycle.
class LinkedListCycleLength {

    // Time and Space Complexity: The above algorithm runs in O(N) time complexity and O(1) space complexity.
    public static int findCycleLength(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // find the LinkedList cycle
        while (fast != null && fast.next != null) {
            fast = fast.next.next; // fast pointer
            slow = slow.next; // slow pointer
            // compare pointers
            if (slow == fast) { // found the cycle
                return calculateLength(slow);
            }
        }

        return 0;
    }

    // calculate the length of a cycle
    private static int calculateLength(ListNode slow) {
        ListNode current = slow; // save the slow pointer as another pointer
        int cycleLength = 0; // keep track of the length of the cycle
        // iterate the whole cycle with another pointer until we see the slow pointer again to find the length of the cycle
        do {
            current = current.next;
            cycleLength++;
        } while (current != slow);

        return cycleLength;
    }


    public static void main(String[] args) {
        // create a LinkedList and assign values to it
        ListNode head = new ListNode(1);
        ////////////////////////////////////
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycleLength.findCycleLength(head)); // 4
        //////////////////////////////////////////////////////////////////////////////////////////////
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycleLength.findCycleLength(head)); // 3
    }

}

/* Solution:

We can use the above solution to find the cycle in the LinkedList. Once the fast and slow pointers meet, we can save the slow pointer
and iterate the whole cycle with another pointer until we see the slow pointer again to find the length of the cycle. */