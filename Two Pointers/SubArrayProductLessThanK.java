import java.util.*;

// Sub-arrays with Product Less than a Target (medium)
class SubArrayProductLessThanK {

    // Time complexity: The main for-loop managing the sliding window takes O(N), but creating sub-arrays can take up to O(N^2) in the worst case.
    // Therefore overall, our algorithm will take O(N^3).
    // Space complexity: Ignoring the space required for the output list, the algorithm runs in O(N)space which is used for the temp list.
    public static List<List<Integer>> findSubArrays(int[] arr, int target) {
        // create a new list of lists
        List<List<Integer>> result = new ArrayList<>();
        // create needed variables and initialize them
        int product = 1, left = 0;
        // iterate over array
        for (int right = 0; right < arr.length; right++) {
            // calculate/update the product by multiplying the product with the current element
            product *= arr[right];
            // while product is not less than target (while product is larger than target we need to make it smaller)
            while (product >= target && left < arr.length) {
                product /= arr[left++]; // update product by removing left-most element (make product smaller) and then increment left pointer to slide window forward
            }
            // create a new linked list
            List<Integer> tempList = new LinkedList<>();
            // since the product of all numbers from left to right is less than the target therefore, all sub-arrays from left to right will have a
            // product less than the target too; to avoid duplicates, we will start with a sub-array containing only arr[right] and then extend it
            for (int i = right; i >= left; i--) {
                tempList.add(0, arr[i]);
                result.add(new ArrayList<>(tempList));
            }
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(SubArrayProductLessThanK.findSubArrays(new int[] { 2, 5, 3, 10 }, 30)); // [[2], [5], [2, 5], [3], [5, 3], [10]]
        System.out.println(SubArrayProductLessThanK.findSubArrays(new int[] { 8, 2, 6, 5 }, 50)); // [[8], [2], [8, 2], [6], [2, 6], [5], [6, 5]]
    }

}

/*
Solution:

This problem follows the Sliding Window and the Two Pointers pattern and shares similarities with Triplets with Smaller Sum with two differences:

    1. In this problem, the input array is not sorted.

    2. Instead of finding triplets with sum less than a target, we need to find all sub-arrays having a product less than the target.

The implementation will be quite similar to Triplets with Smaller Sum. */