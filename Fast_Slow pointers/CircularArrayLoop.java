
// Cycle in a Circular Array (hard)
class CircularArrayLoop {

    // Time Complexity: The algorithm will have a time complexity of O(N^2) where ‘N’ is the number of elements in the array. This complexity
    // is due to the fact that we are iterating all elements of the array and trying to find a cycle for each element.
    // Space Complexity: The algorithm runs in constant space O(1).
    public static boolean loopExists(int[] arr) {
        // start from each index of the array to find the cycle
        for (int i = 0; i < arr.length; i++) {
            // if we are moving forward or not
            boolean isForward = arr[i] >= 0;
            // if a number does not have a cycle we will move forward to the next element
            int slow = i, fast = i; // fast/slow pointers

            // if slow or fast becomes '-1' this means we can't find cycle for this number
            do {
                slow = findNextIndex(arr, isForward, slow); // move one step for slow pointer
                fast = findNextIndex(arr, isForward, fast); // move one step for fast pointer
                // as long as fast is not a single element cycle or has changed direction
                if (fast != -1) {
                    fast = findNextIndex(arr, isForward, fast); // move another step for fast pointer
                }
            } while (slow != -1 && fast != -1 && slow != fast);

            // the cycle has been found
            if (slow != -1 && slow == fast) {
                return true;
            }
        }

        return false; // the cycle was not found
    }

    // find the next index for a pointer
    private static int findNextIndex(int[] arr, boolean isForward, int currentIndex) {
        // determine the direction
        boolean direction = arr[currentIndex] >= 0;
        // if there is a change in direction, return -1
        if (isForward != direction) {
            return -1;
        }
        // calculate the next index
        int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
        // check whether the next index is negative
        if (nextIndex < 0) {
            nextIndex += arr.length; // wrap around for negative numbers
        }
        // one element cycle, return -1
        if (nextIndex == currentIndex) {
            nextIndex = -1;
        }

        return nextIndex;
    }


    public static void main(String[] args) {
        System.out.println(CircularArrayLoop.loopExists(new int[] { 1, 2, -1, 2, 2 })); // true
        System.out.println(CircularArrayLoop.loopExists(new int[] { 2, 2, -1, 2 })); // true
        System.out.println(CircularArrayLoop.loopExists(new int[] { 2, 1, -1, -2 })); // false
    }

}

/* Solution:

    This problem involves finding a cycle in the array and, as we know, the Fast & Slow pointer method is an efficient way to do that. We can
    from each index of the array to find the cycle. If a number does not have a cycle we will move forward to the next element.
    There are a couple of additional things we need to take care of:

    1. As mentioned in the problem, the cycle should have more than one element. This means that when we move a pointer forward, if the pointer
       points to the same element after the move, we have a one-element cycle. Therefore, we can finish our cycle search for the current element.

    2. The other requirement mentioned in the problem is that the cycle should not contain both forward and backward movements. We will handle this
       by remembering the direction of each element while searching for the cycle. If the number is positive, the direction will be forward and if
       the number is negative, the direction will be backward. So whenever we move a pointer forward, if there is a change in the direction,
       we will finish our cycle search right there for the current element.                                                                               */