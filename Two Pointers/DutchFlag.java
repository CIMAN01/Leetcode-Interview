
// Dutch National Flag Problem (medium)
class DutchFlag {

    // Time complexity: The time complexity of the above algorithm will be O(N) as we are iterating the input array only once.
    // Space complexity: The algorithm runs in constant space O(1).
    public static void sort(int[] arr) {
        // all elements < low are 0, all elements > high are 2, and all elements from >= low < i are 1
        int low = 0, high = arr.length - 1;
        // iterate the input array using three pointers (low, mid, high)
        for (int mid = 0; mid <= high;) {
            // check if the element is a 0, 1, or 2
            if (arr[mid] == 0) {
                swap(arr, mid, low);
                // increment 'mid' and 'low' pointers by one
                mid++;
                low++;
            } else if (arr[mid] == 1) {
                mid++; // increment 'mid' pointer by one
            } else { // the case for arr[i] == 2
                swap(arr, mid, high);
                // decrement 'high' pointer only, because after the swap the number at index 'mid' could be 0, 1 or 2
                high--;
            }
        }
    }

    // a simple standard method that swaps elements in an array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[] { 1, 0, 2, 1, 0 };
        ////////////////////////////////////////
        DutchFlag.sort(arr);
        ////////////////////////////////////////
        for (int num : arr) {
            System.out.print(num + " "); // 0 0 1 1 2
        }
        System.out.println();
        ////////////////////////////////////////
        arr = new int[] { 2, 2, 0, 1, 2, 0 };
        ////////////////////////////////////////
        DutchFlag.sort(arr);
        ////////////////////////////////////////
        for (int num : arr) {
            System.out.print(num + " "); // 0 0 1 2 2 2
        }
    }

}

/* Solution:

The brute force solution will be to use an in-place sorting algorithm like Heapsort which will take O(N * logN). Can we do better than this?
Is it possible to sort the array in one iteration?

We can use a Two Pointers approach while iterating through the array. Let’s say the two pointers are called low and high which are pointing to the
first and the last element of the array respectively. So while iterating, we will move all 0s before low and all 2s after high so that in the end,
all 1s will be between low and high. */