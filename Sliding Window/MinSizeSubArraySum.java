
// Smallest Subarray with a given sum (easy)
class MinSizeSubArraySum {

    // time: O(N) / O(N + N) | space: O(1)
    public static int findMinSubArray(int S, int[] arr) {
        int windowSum = 0, minLength = Integer.MAX_VALUE;
        int windowStart = 0;
        // add-up elements from the beginning of the array until their sum becomes greater than or equal to S
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // add the next element in the sliding window
            // shrink the window as small as possible until the 'windowSum' is smaller than 'S'
            while(windowSum >= S) { // inner while loop processes each element only once
                // check if the current window length is the smallest so far, and if so, remember its length
                minLength = Math.min(minLength, windowEnd - windowStart + 1); // plus one to offset zero based indexing
                // subtract the first element of the window from the current sum to shrink the sliding window
                windowSum -= arr[windowStart];
                windowStart++; // slide the window ahead
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int result = MinSizeSubArraySum.findMinSubArray(7, new int[]{2, 1, 5, 2, 3, 2});
        System.out.println("Smallest sub-array length: " + result); // 2

        result = MinSizeSubArraySum.findMinSubArray(7, new int[]{2, 1, 5, 2, 8});
        System.out.println("Smallest sub-array length: " + result); // 1

        result = MinSizeSubArraySum.findMinSubArray(8, new int[]{3, 4, 1, 1, 6});
        System.out.println("Smallest sub-array length: " + result); // 3
    }

}