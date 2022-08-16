import java.util.*;

// Triplet Sum to Zero (medium)
class TripletSumToZero {

    // Time complexity: Sorting the array will take O(N * logN). The searchPair() function will take O(N). As we are calling searchPair() for every number in
    // the input array, this means that overall searchTriplets() will take O(N∗logN + N^2) , which is asymptotically equivalent to O(N^2).
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Space complexity: Ignoring the space required for the output array, the space complexity of the above algorithm will be O(N) which is required for sorting.
    public static List<List<Integer>> searchTriplets(int[] arr) {
        Arrays.sort(arr); // first sort the array
        // create a new list of lists
        List<List<Integer>> triplets = new ArrayList<>();
        // iterate through the array taking one number at a time
        for (int i = 0; i < arr.length - 2; i++) { // only need to iterate up to n - 2
            // skip same element to avoid duplicate triplets
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue; // continue with the next iteration in the loop
            }
            // find a pair whose sum is Y + Z == -X
            searchPair(arr, -arr[i], i + 1, triplets); // i+1 because -X is i, so we need to start from the next element
        }

        return triplets;
    }

    // find a pair whose sum is Y + Z == -X
    private static void searchPair(int[] arr, int targetSum, int left, List<List<Integer>> triplets) {
        int right = arr.length - 1;
        // search the array for a match
        while(left < right) {
            // get the current sum
            int currentSum = arr[left] + arr[right];
            // found the triplet
            if(currentSum == targetSum) {
                // add triplet to list
                triplets.add(Arrays.asList(-targetSum, arr[left], arr[right]));
                // move pointers to next/previous elements
                left++;
                right--;
                // handle any duplicates
                while(left < right && arr[left] == arr[left - 1]) {
                    left++; // skip same element to avoid duplicate triplets
                }
                while(left < right && arr[right] == arr[right + 1]) {
                    right--; // skip same element to avoid duplicate triplets
                }
            }
            else if(targetSum > currentSum) {
                left++; // we need a pair with a bigger sum
            }
            else {
                right--; // we need a pair with a smaller sum
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(TripletSumToZero.searchTriplets(new int[] { -3, 0, 1, 2, -1, 1, -2 })); // [[-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]]
        System.out.println(TripletSumToZero.searchTriplets(new int[] { -5, 2, -1, -2, 3 }));       // [[-5, 2, 3], [-2, -1, 3]]
    }

}

/*
Solution:

This problem follows the Two Pointers pattern and shares similarities with Pair with Target Sum. A couple of differences are that the input array is not sorted
and instead of a pair we need to find triplets with a target sum of zero.

To follow a similar approach, first, we will sort the array and then iterate through it taking one number at a time. Let’s say during our iteration we are at number ‘X’,
so we need to find ‘Y’ and ‘Z’ such that X + Y + Z == 0.

At this stage, our problem translates into finding a pair whose sum is equal to “−X” (as from the above equation Y + Z == -X).

Another difference from Pair with Target Sum is that we need to find all the unique triplets. To handle this, we have to skip any duplicate number.
Since we will be sorting the array, so all the duplicate numbers will be next to each other and are easier to skip. */