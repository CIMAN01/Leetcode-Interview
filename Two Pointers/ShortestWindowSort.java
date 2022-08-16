
// Minimum Window Sort (medium)
class ShortestWindowSort {

    // Time complexity: The time complexity of the above algorithm will be O(N).
    // Space complexity: The algorithm runs in constant space O(1).
    public static int sort(int[] arr) {
        // create and initialize the two pointers
        int low = 0, high = arr.length - 1;
        // find the first number out of sorting order from the beginning
        while (low < arr.length - 1 && arr[low] <= arr[low + 1]) {
            low++;
        }
        // if the array is sorted
        if (low == arr.length - 1) {
            return 0;
        }
        // find the first number out of sorting order from the end
        while (high > 0 && arr[high] >= arr[high - 1]) {
            high--;
        }
        // store the max and min elements of the sub-array, which will be initialized to smallest and largest integer values, respectively
        int subArrayMax = Integer.MIN_VALUE, subArrayMin = Integer.MAX_VALUE;
        // find the maximum and minimum of the sub-array
        for (int i = low; i <= high; i++) {
            subArrayMax = Math.max(subArrayMax, arr[i]);
            subArrayMin = Math.min(subArrayMin, arr[i]);
        }
        // extend the sub-array to include any number which is bigger than the minimum of the sub-array
        while (low > 0 && arr[low - 1] > subArrayMin) {
            low--;
        }
        // extend the sub-array to include any number which is smaller than the maximum of the sub-array
        while (high < arr.length - 1 && arr[high + 1] < subArrayMax) {
            high++;
        }

        return high - low + 1; // high -low + 1 because of zero-based array indexing
    }


    public static void main(String[] args) {
        System.out.println(ShortestWindowSort.sort(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 })); // 5
        System.out.println(ShortestWindowSort.sort(new int[] { 1, 3, 2, 0, -1, 7, 10 })); // 5
        System.out.println(ShortestWindowSort.sort(new int[] { 1, 2, 3 })); // 0
        System.out.println(ShortestWindowSort.sort(new int[] { 3, 2, 1 })); // 3
    }

}

/* Solution:

The problem here is that the smallest number of our sub-array is ‘-1’ which dictates that we need to include more numbers from the beginning of the array to make the
whole array sorted. We will have a similar problem if the maximum of the sub-array is bigger than some elements at the end of the array. To sort the whole array we
need to include all such elements that are smaller than the biggest element of the sub-array.

So our final algorithm will look like:

    1. From the beginning and end of the array, find the first elements that are out of the sorting order. The two elements will be our candidate subarray.

    2. Find the maximum and minimum of this sub-array.

    3. Extend the sub-array from beginning to include any number which is bigger than the minimum of the sub-array.

    4. Similarly, extend the sub-array from the end to include any number which is smaller than the maximum of the sub-array. */