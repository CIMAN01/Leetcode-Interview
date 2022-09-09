
// Palindrome LinkedList (medium)
class PalindromicLinkedList {

    // Time complexity: The above algorithm will have a time complexity of O(N). where ‘N’ is the number of nodes in the LinkedList.
    // Space complexity: The algorithm runs in constant space O(1).
    public static boolean isPalindrome(ListNode head) {
        // handle an empty LinkedList
        if (head == null || head.next == null) {
            return true;
        }
        // fast/slow pointers
        ListNode slow = head;
        ListNode fast = head;
        // find middle of the LinkedList
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow is now pointing to the middle node
        ListNode headSecondHalf = reverse(slow); // reverse the second half
        ListNode copyHeadSecondHalf = headSecondHalf; // store the head of reversed part to revert back later

        // search for a palindrome match
        while (head != null && headSecondHalf != null) {
            // compare the first and the second half of the LinkedList
            if (head.value != headSecondHalf.value) {
                break; // not a palindrome
            }
            head = head.next;
            headSecondHalf = headSecondHalf.next;
        }

        reverse(copyHeadSecondHalf); // revert the reverse of the second half back to its original form

        // if both halves match
        if (head == null || headSecondHalf == null) {
            return true;
        }

        return false;
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
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head)); // true
        /////////////////////////////////////////////////////////////////////////////////
        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head)); // false
    }

}

/* Solution:

As we know, a palindrome LinkedList will have nodes values that read the same backward or forward. This means that if we divide the LinkedList into two
halves, the node values of the first half in the forward direction should be similar to the node values of the second half in the backward direction.

As we have been given a Singly LinkedList, we can’t move in the backward direction. To handle this, we will perform the following steps:

    1. We can use the Fast & Slow pointers method similar to Middle of the LinkedList to find the middle node of the LinkedList.

    2. Once we have the middle of the LinkedList, we will reverse the second half.

    3. Then, we will compare the first half with the reversed second half to see if the LinkedList represents a palindrome.

    4. Finally, we will reverse the second half of the LinkedList again to revert and bring the LinkedList back to its original form.                  */