
// Maximum Sum Subarray of Size K (easy)
class MaxSumSubArrayOfSizeK {

    // Brute force solution -> O(N * K)
    public static int findMaxSumSubArray2(int k, int[] arr) {
        int maxSum = 0, windowSum;

        for(int i = 0; i <= arr.length - k; i++) {
            windowSum = 0; // reset current sum

            for(int j = i; j < i + k; j++) {
                windowSum += arr[j]; // calculate sum of current sub-array
            }

            maxSum = Math.max(maxSum, windowSum); // compare and store max sub-array
        }

        return maxSum;
    }

    // Optimal solution -> O(N)
    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSum = 0, windowSum = 0;
        int windowStart = 0;
        // iterate through the array to process each element a time in the window
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // add the next element
            // slide the window, we don't need to slide if we've not hit the required window size of 'k'
            if(windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart]; // subtract the element going out
                windowStart++; // slide the window ahead
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSumSubArrayOfSizeK.findMaxSumSubArray(3, new int[]{2, 1, 5, 1, 3, 2})); // 9
        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSumSubArrayOfSizeK.findMaxSumSubArray(2, new int[]{2, 3, 4, 1, 5})); // 7
    }

}


