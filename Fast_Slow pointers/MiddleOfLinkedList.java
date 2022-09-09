
// Middle of the LinkedList (easy)
class MiddleOfLinkedList {

    // Time complexity: The above algorithm will have a time complexity of O(N) where ‘N’ is the number of nodes in the LinkedList.
    // Space complexity: The algorithm runs in constant space O(1).
    public static ListNode findMiddle(ListNode head) {
        // fast/slow pointers
        ListNode slow = head;
        ListNode fast = head;
        // use the Fast & Slow pointers method
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // slow will point at the middle node
    }


    public static void main(String[] args) {
        // create a LinkedList and assign values to it
        ListNode head = new ListNode(1);
        //////////////////////////////////////
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value); // 3
        ////////////////////////////////////////////////////////////////////////////////
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value); // 4
        ////////////////////////////////////////////////////////////////////////////////
        head.next.next.next.next.next.next = new ListNode(7);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value); // 4
    }

}

/* Solution:

One brute force strategy could be to first count the number of nodes in the LinkedList and then find the middle node in the second iteration.
However, can this be done in one iteration instead?

We can use the Fast & Slow pointers method such that the fast pointer is always twice the nodes ahead of the slow pointer.
This way, when the fast pointer reaches the end of the LinkedList, the slow pointer will be pointing at the middle node.                    */