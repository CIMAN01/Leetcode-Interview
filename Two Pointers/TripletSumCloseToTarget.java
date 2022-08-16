import java.util.*;

// Triplet Sum Close to Target (medium)
class TripletSumCloseToTarget {

    // Time complexity: Sorting the array will take O(N * logN). Overall searchTriplet() will take O(N * logN + N^2), which is asymptotically equivalent to O(N^2).
    // Space complexity: The space complexity of the above algorithm will be O(N) which is required for sorting.
    public static int searchTriplet(int[] arr, int targetSum) {
        // handle edge cases
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(arr); // first sort the array
        // create and initialize variable that holds the smallest difference
        int smallestDifference = Integer.MAX_VALUE;
        // iterate over the array, taking one number at a time
        for (int i = 0; i < arr.length - 2; i++) {
            // create and initialize left and right pointers
            int left = i + 1, right = arr.length - 1;
            // at every step, we will save the difference between the triplet and the target number, so that in the end, we can return the triplet with the closest sum
            while (left < right) {
                // comparing the sum of three numbers to the 'targetSum' can cause overflow so, we will try to find a target difference
                int targetDiff = targetSum - arr[i] - arr[left] - arr[right];
                //  we've found a triplet with an exact sum
                if (targetDiff == 0) {
                    return targetSum - targetDiff; // return sum of all the numbers
                }
                // the second part of the above 'if' is to handle the smallest sum when we have more than one solution
                if (Math.abs(targetDiff) < Math.abs(smallestDifference)
                        || (Math.abs(targetDiff) == Math.abs(smallestDifference) && targetDiff > smallestDifference)) {
                    smallestDifference = targetDiff; // save the closest and the biggest difference
                }
                // if targetDiff is greater than the sum of triplets, increment left pointer to increase the sum so that the difference moves closer to zero
                if (targetDiff > 0) {
                    left++; // we need a triplet with a bigger sum
                }
                // if targetDiff is smaller than the sum of triplets, decrement right pointer to decrease the sum  so that the difference moves closer to zero
                else {
                    right--; // we need a triplet with a smaller sum
                }
            }
        }

        return targetSum - smallestDifference;
    }


    public static void main(String[] args) {
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{-2, 0, 1, 2}, 2)); // 1
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{-3, -1, 1, 2}, 1)); // 0
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{1, 0, 1, 1}, 100)); // 3
    }

}