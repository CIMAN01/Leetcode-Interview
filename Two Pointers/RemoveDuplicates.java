
// Remove Duplicates (easy)
class RemoveDuplicates {

    // Time Complexity: The time complexity of the above algorithm will be O(N), where ‘N’ is the total number of elements in the given array.
    // Space Complexity: The algorithm runs in constant space O(1).
    public static int remove(int[] arr) {
        // create a pointer to hold the index of the next non-duplicate element
        int nextNonDuplicate = 1;
        // iterate over the array using i as another pointer
        for(int i = 1; i < arr.length; i++) {
            // if we see a non-duplicate number, we move it next to the last non-duplicate number we’ve seen (shift the elements left whenever encountering duplicates)
            if(arr[nextNonDuplicate - 1] != arr[i]) {
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }

        return nextNonDuplicate;
    }


    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(RemoveDuplicates.remove(arr)); // 4
        /////////////////////////////////////////////////
        arr = new int[] { 2, 2, 2, 11 };
        System.out.println(RemoveDuplicates.remove(arr)); // 2
    }

}