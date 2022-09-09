
// Rearrange a LinkedList (medium)
class RearrangeList {

    // Time Complexity: The above algorithm will have a time complexity of O(N) where ‘N’ is the number of nodes in the LinkedList.
    // Space Complexity: The algorithm runs in constant space O(1).
    public static void reorder(ListNode head) {
        // handle an empty LinkedList
        if (head == null || head.next == null) {
            return;
        }
        // fast/slow pointers
        ListNode slow = head, fast = head;
        // find the middle of the LinkedList
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow is now pointing to the middle node
        ListNode headSecondHalf = reverse(slow); // reverse the second half
        ListNode headFirstHalf = head;

        // rearrange to produce the LinkedList in the required order
        while (headFirstHalf != null && headSecondHalf != null) {
            // headFirstHalf's next node points to headSecondHalf's current node (link first half to second half)
            ListNode temp = headFirstHalf.next;
            headFirstHalf.next = headSecondHalf;
            headFirstHalf = temp;
            // headSecondHalf's next node points to headFirstHalf's current node (link second half to first half)
            temp = headSecondHalf.next;
            headSecondHalf.next = headFirstHalf;
            headSecondHalf = temp;
        }
        // set the next of the last node to 'null'
        if (headFirstHalf != null) {
            headFirstHalf.next = null;
        }
    }

    // reverse the second half of a LinkedList
    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        // reverse the nodes
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }


    public static void main(String[] args) {
        // create a LinkedList and assign values to it
        ListNode head = new ListNode(2);
        //////////////////////////////////////
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        ///////////////////////////////////////////////////////
        RearrangeList.reorder(head);
        // print the re-ordered list
        while (head != null) {
            System.out.print(head.value + " "); // 2 12 4 10 6 8
            head = head.next;
        }
    }

}

/* Solution:

This problem shares similarities with Palindrome LinkedList. To rearrange the given LinkedList we will follow the following steps:

    1. We can use the Fast & Slow pointers method similar to Middle of the LinkedList to find the middle node of the LinkedList.

    2. Once we have the middle of the LinkedList, we will reverse the second half of the LinkedList.

    3. Finally, we’ll iterate through the first half and the reversed second half to produce a LinkedList in the required order.        */