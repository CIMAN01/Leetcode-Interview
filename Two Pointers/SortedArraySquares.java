
// Squaring a Sorted Array (easy)
class SortedArraySquares {

    // Time complexity: The time complexity of the above algorithm will be O(N) as we are iterating the input array only once.
    // Space complexity: The space complexity of the above algorithm will also be O(N) which is the space that will be used for the output array.
    public static int[] makeSquares(int[] arr) {
        // create and initialize needed variables
        int[] squares = new int[arr.length];
        int highestSquareIdx = arr.length - 1;
        int left = 0, right = arr.length - 1;
        // iterate over the array
        while(left <= right) {
            // use two pointers starting at both the ends of the input array
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];
            // whichever pointer gives us the bigger square we add it to the result array and move to the next/previous number according to the pointer
            if(leftSquare > rightSquare) {
                squares[highestSquareIdx--] = leftSquare;
                left++;
            } else {
                squares[highestSquareIdx--] = rightSquare;
                right--;
            }
        }

        return squares;
    }


    public static void main(String[] args) {
        int[] result = SortedArraySquares.makeSquares(new int[] { -2, -1, 0, 2, 3 });
        for (int num : result)
            System.out.print(num + " "); // 0 1 4 4 9
        System.out.println();
        /////////////////////////////////////////////////////////////////////////////
        result = SortedArraySquares.makeSquares(new int[] { -3, -1, 0, 1, 2 });
        for (int num : result)
            System.out.print(num + " ");  // 0 1 1 4 9
        System.out.println();
    }

}
