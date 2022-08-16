import java.util.*;

// Triplets with Smaller Sum (medium)
class TripletWithSmallerSum {

    // Time complexity: Sorting the array will take O(N * logN). The searchPair() will take O(N). So, overall searchTriplets() will take O(N * logN + N^2),
    // which is asymptotically equivalent to O(N^2).
    // Space complexity: Ignoring the space required for the output array, the space complexity of the above algorithm will be O(N) which is required for
    // sorting if we are not using an in-place sorting algorithm.
    public static int searchTriplets(int[] arr, int target) {
        int count = 0;
        // first sort the array in ascending order
        Arrays.sort(arr);
        // iterate through array, taking one number at a time
        for (int i = 0; i < arr.length - 2; i++) {
            count += searchPair(arr, target - arr[i], i); // Y + Z == target - X
        }

        return count;
    }

    // find a pair such that X + Y + Z < target
    private static int searchPair(int[] arr, int targetSum, int first) {
        int count = 0;
        int left = first + 1, right = arr.length - 1;
        // search the array for a match
        while (left < right) {
            // found the triplet
            if (arr[left] + arr[right] < targetSum) {
                // since all the element between left and right index have the value less than the target value,
                // which gives us the required sum, we add all these triplets in the count
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between left and right to get a sum less than the target sum
                count += right - left;
                left++;
            } else {
                right--; // we need a pair with a smaller sum
            }
        }

        return count;
    }


    public static void main(String[] args) {
        System.out.println(TripletWithSmallerSum.searchTriplets(new int[] { -1, 0, 2, 3 }, 3)); // 2
        System.out.println(TripletWithSmallerSum.searchTriplets(new int[] { -1, 4, 2, 1, 3 }, 5)); // 4
    }

}

/*
Solution:

This problem follows the Two Pointers pattern and shares similarities with Triplet Sum to Zero.
The only difference is that, in this problem, we need to find the triplets whose sum is less than the given target.
To meet the condition i != j != k we need to make sure that each number is not used more than once.

Following a similar approach, first we can sort the array and then iterate through it, taking one number at a time.
Let’s say during our iteration we are at number ‘X’, so we need to find ‘Y’ and ‘Z’ such that X + Y + Z < target.
At this stage, our problem translates into finding a pair whose sum is less than “target - X” (as from the above equation Y + Z == target - X).
We can use a similar approach as discussed in Triplet Sum to Zero.  */