
// Similar Problem to Remove Duplicates (easy)
class RemoveElement {

    // Time Complexity: The time complexity of the above algorithm will be O(N), where ‘N’ is the total number of elements in the given array.
    // Space Complexity: The algorithm runs in constant space O(1).
    public static int remove(int[] arr, int key) {
        // create a pointer that holds the index of the next element which is not 'key'
        int nextElement = 0;
        // iterate over the array using i as another pointer
        for(int i = 0; i < arr.length; i++) {
            // follow a two-pointer approach and shift numbers left upon encountering the ‘key’
            if(arr[i] != key) {
                arr[nextElement] = arr[i];
                nextElement++;
            }
        }

        return nextElement;
    }


    public static void main(String[] args) {
        int[] arr = new int[] { 3, 2, 3, 6, 3, 10, 9, 3 };
        System.out.println(RemoveElement.remove(arr, 3)); // 4
        /////////////////////////////////////////////////////
        arr = new int[] { 2, 11, 2, 2, 1 };
        System.out.println(RemoveElement.remove(arr, 2)); // 2
    }

}

