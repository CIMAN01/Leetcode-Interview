import java.util.*;

// Quadruple Sum to Target (medium)
class QuadrupleSumToTarget {

    // Time complexity: Sorting the array will take O(N * logN). Overall searchQuadruplets() will take O(N * logN + N^3), which is asymptotically equivalent to O(N^3).
    // Space complexity: The space complexity of the above algorithm will be O(N) which is required for sorting.
    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        Arrays.sort(arr); // first sort the array
        // create a new list of lists
        List<List<Integer>> quadruplets = new ArrayList<>();
        // iterate through the array, taking one number at a time (only need to iterate up to n - 3)
        for (int i = 0; i < arr.length - 3; i++) {
            // skip same element to avoid duplicate quadruplets
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue; // continue with the next iteration in the loop
            }
            // only need to iterate up to n - 2
            for (int j = i + 1; j < arr.length - 2; j++) {
                // skip same element to avoid duplicate quadruplets
                if (j > i + 1 && arr[j] == arr[j - 1]) {
                    continue; // continue with the next iteration in the loop
                }
                // search for the quadruplets, whose sum is equal to the given target
                searchPairs(arr, target, i, j, quadruplets);
            }
        }

        return quadruplets;
    }

    // search for the quadruplets, whose sum is equal to the given target
    private static void searchPairs(int[] arr, int targetSum, int first, int second, List<List<Integer>> quadruplets) {
        // create a left and right pointer
        int left = second + 1;
        int right = arr.length - 1;
        // search the array for a match
        while (left < right) {
            int sum = arr[first] + arr[second] + arr[left] + arr[right];
            // found the quadruplet
            if (sum == targetSum) {
                // add to list of quadruplets
                quadruplets.add(Arrays.asList(arr[first], arr[second], arr[left], arr[right]));
                // increment pointers
                left++;
                right--;
                // handle any duplicates
                while (left < right && arr[left] == arr[left - 1]) {
                    left++; // skip same element to avoid duplicate quadruplets
                }
                while (left < right && arr[right] == arr[right + 1]) {
                    right--; // skip same element to avoid duplicate quadruplets
                }
            } else if (sum < targetSum) {
                left++; // we need a pair with a bigger sum
            }
            else {
                right--; // we need a pair with a smaller sum
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[] { 4, 1, 2, -1, 1, -3 }, 1)); // [[-3, -1, 1, 4], [-3, 1, 1, 2]]
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[] { 2, 0, -1, 1, -2, 2 }, 2)); // [[-2, 0, 2, 2], [-1, 0, 1, 2]]
    }

}

/* Solution:

This problem follows the Two Pointers pattern and shares similarities with Triplet Sum to Zero.

We can follow a similar approach to iterate through the array, taking one number at a time.

At every step during the iteration, we will search for the quadruplets similar to
Triplet Sum to Zero whose sum is equal to the given target. */