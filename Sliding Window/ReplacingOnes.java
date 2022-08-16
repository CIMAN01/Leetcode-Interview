
// Longest Sub-array with Ones after Replacement (hard)
class ReplacingOnes {

    // Time: O(N) where ‘N’ is the count of numbers in the input array | Space: O(1) algorithm runs in constant space
    public static int findLength(int[] arr, int k) {
        // keep track of the maximum number of repeating 1s in the current window in a variable called maxOnesCount
        int windowStart = 0, maxLength = 0, maxOnesCount = 0;
        // iterate through the array to add one number at a time in the window
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) { // try to extend the range [windowStart, windowEnd]
            // check if current end pointer is pointing at a 1 and if so increment its corresponding count variable
            if(arr[windowEnd] == 1) {
                maxOnesCount++;
            }
            // current window size is from windowStart to windowEnd, overall we have a maximum of 1s repeating a maximum of 'maxOnesCount' times,
            // this means that we can have a window with 'maxOnesCount' 1s and the remaining are 0s which we should replace with 1s
            // now, if the remaining 0s are more than 'k', it is the time to shrink the window as we are not allowed to replace more than 'k' Os
            if(windowEnd - windowStart + 1 - maxOnesCount > k) { // sliding window - frequency count > k
                // check if current start pointer is pointing at a 1 and if so decrement its corresponding count variable
                if(arr[windowStart] == 1) {
                    maxOnesCount--;
                }
                windowStart++; // shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(ReplacingOnes.findLength(new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 }, 2)); // 6
        System.out.println(ReplacingOnes.findLength(new int[] { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 }, 3)); // 9
    }

}

/*
Solution:

This problem follows the Sliding Window pattern and is quite similar to Longest Substring with same Letters after Replacement.
The only difference is that, for this problem, we only have two characters (1s and 0s) in the input arrays.

Following a similar approach, we’ll iterate through the array to add one number at a time in the window.
We’ll also keep track of the maximum number of repeating 1s in the current window (let’s call it maxOnesCount).

So at any time, we know that we can have a window which has 1s repeating maxOnesCount time, so we should try to replace the remaining 0s.
If we have more than ‘k’ remaining 0s, we should shrink the window as we are not allowed to replace more than ‘k’ 0s.

*/