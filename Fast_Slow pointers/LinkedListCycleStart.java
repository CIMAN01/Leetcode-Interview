
// Start of LinkedList Cycle (medium)
class LinkedListCycleStart {

    // Time Complexity: As we know, finding the cycle in a LinkedList with ‘N’ nodes and also finding the length of the cycle requires O(N). Also, as we saw in the
    // above algorithm, we will need O(N) to find the start of the cycle. Therefore, the overall time complexity of our algorithm will be O(N).
    // Space Complexity: The algorithm runs in constant space O(1).
    public static ListNode findCycleStart(ListNode head) {
        int cycleLength = 0;
        // fast/slow pointers
        ListNode slow = head;
        ListNode fast = head;
        // find the LinkedList cycle
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // compare pointers
            if (slow == fast) { // found the cycle
                cycleLength = calculateCycleLength(slow);
                break;
            }
        }

        return findStart(head, cycleLength);
    }

    // calculate the length of a cycle
    private static int calculateCycleLength(ListNode slow) {
        ListNode current = slow; // save the slow pointer as another pointer
        int cycleLength = 0; // keep track of the length of the cycle
        // // iterate the whole cycle with another pointer until we see the slow pointer again to find the length of the cycle
        do {
            current = current.next;
            cycleLength++;
        } while (current != slow);

        return cycleLength;
    }

    // find the start of the cycle given its length
    private static ListNode findStart(ListNode head, int cycleLength) {
        // create two pointers, and initialize them to point to the start of the LinkedList
        ListNode pointer1 = head, pointer2 = head;
        // move pointer2 ahead by 'cycleLength' nodes
        while (cycleLength > 0) {
            pointer2 = pointer2.next;
            cycleLength--;
        }
        // increment both pointers until they meet at the start of the cycle
        while (pointer1 != pointer2) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        // pointer2 will have completed one loop in the cycle when both pointers meet, so their meeting point will be the start of the cycle
        return pointer1;
    }


    public static void main(String[] args) {
        // assign values
        ListNode head = new ListNode(1);
        //////////////////////////////////////
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        //////////////////////////////////////////////////////
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value); // 3
        /////////////////////////////////////////////////////////////////////////////////////////////////
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value); // 4
        /////////////////////////////////////////////////////////////////////////////////////////////////
        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value); // 1
    }

}

/* Solution:

If we know the length of the LinkedList cycle, we can find the start of the cycle through the following steps:

    1. Take two pointers. Let’s call them pointer1 and pointer2.

    2. Initialize both pointers to point to the start of the LinkedList.

    3. We can find the length of the LinkedList cycle using the approach discussed in LinkedList Cycle.
       Let’s assume that the length of the cycle is ‘K’ nodes.

    4. Move pointer2 ahead by ‘K’ nodes.

    5. Now, keep incrementing pointer1 and pointer2 until they both meet.

    6. As pointer2 is ‘K’ nodes ahead of pointer1, which means, pointer2 must have completed one loop in the cycle when both pointers meet.
       Their meeting point will be the start of the cycle.                                                                                                 */