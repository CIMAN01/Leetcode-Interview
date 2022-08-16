import java.util.HashMap;

// Pair with Target Sum (easy)
class PairWithTargetSum {

    // Time: The time complexity of the above algorithm will be O(N), where ‘N’ is the total number of elements in the given array.
    // Space: The algorithm runs in constant space O(1).
    public static int[] search(int[] arr, int targetSum) {
        // create two pointers
        int left = 0, right = arr.length - 1;
        // iterate over the array
        while(left < right) {
            // get the sum of the current pair
            int currentSum = arr[left] + arr[right];
            // if we get a match, we have found the pair
            if(currentSum == targetSum) {
                return new int[]{left, right};
            }
            // if the sum of the two numbers (two pointers) is smaller than the target sum, we need a pair with a larger sum so increment the start-pointer
            if(targetSum > currentSum) {
                left++; // we need a pair with a bigger sum
            }
            // if the sum of the two numbers (two pointers) is greater than the target sum,we need a pair with a smaller sum so decrement the end-pointer
            else {
                right--; // we need a pair with a smaller sum
            }
        }

        return new int[] { -1, -1 }; // pair not found
    }


    // Time: The time complexity of the above algorithm will be O(N), where ‘N’ is the total number of elements in the given array.
    // Space: The space complexity will also be O(N), as, in the worst case, we will be pushing ‘N’ numbers in the HashTable.
    public static int[] searchAlternate(int[] arr, int targetSum) {
        // create a hashmap to store numbers and their indices
        HashMap<Integer, Integer> nums = new HashMap<>();
        // search for ‘Y’ such that “X + Y == Target" (which is equivalent to “Target−X”) in the HashTable
        for(int i = 0; i < arr.length; i++) {
            // if it is there, we have found the required pair
            if(nums.containsKey(targetSum - arr[i])) {
                return new int[]{nums.get(targetSum - arr[i]), i};
            }
            // otherwise, insert “X” in the HashTable, so that we can search it for the upcoming numbers
            else {
                nums.put(arr[i], i); // put the number and its index in the map
            }
        }

        return new int[] { -1, -1 }; // pair not found
    }


    public static void main(String[] args) {
        int[] result = PairWithTargetSum.search(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]"); // [1, 3]
        result = PairWithTargetSum.search(new int[] { 2, 5, 9, 11 }, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]"); // [0, 2]
        System.out.println("////////////////////////////");
        int[] resultAlternate = PairWithTargetSum.searchAlternate(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.println("Pair with target sum: [" + resultAlternate[0] + ", " + resultAlternate[1] + "]"); // [1, 3]
        resultAlternate = PairWithTargetSum.searchAlternate(new int[] { 2, 5, 9, 11 }, 11);
        System.out.println("Pair with target sum: [" + resultAlternate[0] + ", " + resultAlternate[1] + "]"); // [0, 2]
    }

}