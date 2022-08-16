import java.util.*;

// Similar Problem to Triplet Sum to Zero
class TripletWithSmallerSum2 {

    // Time complexity: Sorting the array will take O(N * logN) and the searchPair() will take O(N). So, overall
    // searchTriplets() will take O(N * logN + N^2), which is asymptotically equivalent to O(N^2).
    // Space complexity: Ignoring the space required for the output array, the space complexity of the above algorithm will be O(N),
    // which is required for sorting if we are not using an in-place sorting algorithm.
    public static List<List<Integer>> searchTriplets(int[] arr, int target) {
        // first sort the array in ascending order
        Arrays.sort(arr);
        // create a list of lists
        List<List<Integer>> triplets = new ArrayList<>();
        // iterate through array, taking one number at a time
        for (int i = 0; i < arr.length - 2; i++) {
            searchPair(arr, target - arr[i], i, triplets); // Y + Z == target - X
        }

        return triplets;
    }

    // find a pair such that X + Y + Z < target
    private static void searchPair(int[] arr, int targetSum, int first, List<List<Integer>> triplets) {
        int left = first + 1, right = arr.length - 1;
        // search the array for a match
        while (left < right) {
            // found the triplet
            if (arr[left] + arr[right] < targetSum) {
                // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between left and right to get a sum less than the target sum
                for (int i = right; i > left; i--) {
                    triplets.add(Arrays.asList(arr[first], arr[left], arr[i]));
                }
                left++;
            } else {
                right--; // we need a pair with a smaller sum
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(TripletWithSmallerSum2.searchTriplets(new int[] { -1, 0, 2, 3 }, 3)); // [[-1, 0, 3], [-1, 0, 2]]
        System.out.println(TripletWithSmallerSum2.searchTriplets(new int[] { -1, 4, 2, 1, 3 }, 5)); // [[-1, 1, 4], [-1, 1, 3], [-1, 1, 2], [-1, 2, 3]]

    }

}

