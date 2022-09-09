
// Happy Number (medium)
class HappyNumber {

    // Time Complexity: The algorithm will have a time complexity of O(logN).
    // Space Complexity: The algorithm runs in constant space O(1).
    public static boolean find(int num) {
        // fast/slow pointers
        int slow = num, fast = num;
        // find the cycle
        do {
            slow = findSquareSum(slow); // move one step
            fast = findSquareSum(findSquareSum(fast)); // move two steps
        } while (slow != fast); // found the cycle

        return slow == 1; // see if the cycle is stuck on the number '1' (happy number)
    }

    // find the sum of the square of all the digits of a number
    private static int findSquareSum(int num) {
        int sum = 0, digit;
        // process each digit (from right to left) in num to find the sum of their squares
        while (num > 0) {
            digit = num % 10; // grab the (right-most) digit from num
            sum += digit * digit; // add to sum the square of that digit
            num /= 10; // remove the (right-most) digit from num
        }

        return sum;
    }


    public static void main(String[] args) {
        System.out.println(HappyNumber.find(23)); // true
        System.out.println(HappyNumber.find(12)); // false
    }

}

/* Solution:

The process, as defined, to find out if a number is a happy number or not, always ends in a cycle. If the number is a happy number, the process will be stuck in
a cycle on number ‘1,’ and if the number is not a happy number then the process will be stuck in a cycle with a set of numbers. As we saw in Example-2 while
determining if ‘12’ is a happy number or not, our process will get stuck in a cycle with the following numbers:
89 -> 145 -> 42 -> 20 -> 4 -> 16 -> 37 -> 58 -> 89

We saw in the LinkedList Cycle problem that we can use the Fast & Slow pointers method to find a cycle among a set of elements. As we have described above, each number
will definitely have a cycle. Therefore, we will use the same fast & slow pointer strategy to find the cycle and once the cycle is found, we will see if the cycle is
stuck on number ‘1’ to find out if the number is happy or not. */
