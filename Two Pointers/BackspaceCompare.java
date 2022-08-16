import java.util.Stack;

// Comparing Strings containing Backspaces (medium)
class BackspaceCompare {

    // Time complexity: The time complexity of the above algorithm will be O(M + N) where ‘M’ and ‘N’ are the lengths of the two input strings respectively.
    // Space complexity: The algorithm runs in constant space O(1).
    public static boolean compare(String str1, String str2) {
        // use two pointer approach to compare the strings
        int index1 = str1.length() - 1, index2 = str2.length() - 1;
        // scan strings and check if they match
        while (index1 >= 0 || index2 >= 0) {
            // retrieve and store the index of the next valid character for each string
            int i1 = getNextValidCharIndex(str1, index1);
            int i2 = getNextValidCharIndex(str2, index2);
            // reached the end of both the strings
            if (i1 < 0 && i2 < 0) {
                return true;
            }
            // reached the end of one of the strings
            if (i1 < 0 || i2 < 0) {
                return false;
            }
            // check if the characters are equal
            if (str1.charAt(i1) != str2.charAt(i2)) {
                return false;
            }
            // update the indices (decrement index by i - 1 because we are scanning from right to left)
            index1 = i1 - 1;
            index2 = i2 - 1;
        }

        return true;
    }

    // this method retrieves the index of the next valid character
    private static int getNextValidCharIndex(String str, int index) {
        int backspaceCount = 0;
        // look for a backspace character
        while (index >= 0) {
            // found a backspace character
            if (str.charAt(index) == '#') {
                backspaceCount++;
            }
            // a non-backspace character
            else if (backspaceCount > 0) {
                backspaceCount--;
            } else {
                break; // exit loop once we no longer have any backspaces to handle
            }

            index--; // skip a backspace or a valid character
        }

        return index;
    }


    // Using stacks -> time: O(n) | space: O(n)
    public static boolean compareStringsUsingStack(String str1, String str2) {
        String s1 = getStringUsingStack(str1);
        String s2 = getStringUsingStack(str2);

        return s1.equals(s2);
    }

    // returns a string after applying backspaces
    private static String getStringUsingStack(String str) {
        // create a new stack of characters
        Stack<Character> stack = new Stack<>();
        // convert the string to an array of characters
        char[] chars1 = str.toCharArray();
        // for every current character in the array
        for (char ch : chars1) {
            if (ch != '#') { // if character does not contain a backspace
                stack.push(ch); // push the current character onto the stack
            } else { // if ch == '#'
                if (!stack.empty()) {
                    stack.pop(); // pop the top of the stack as long as the stack is not empty
                }
            }
        }

        return String.valueOf(stack); // get the string representation of the stack
    }


    public static void main(String[] args) {
        System.out.println(BackspaceCompare.compare("xy#z", "xzz#")); // true
        System.out.println(BackspaceCompare.compare("xy#z", "xyz#")); // false
        System.out.println(BackspaceCompare.compare("xp#", "xyz##")); // true
        System.out.println(BackspaceCompare.compare("xywrrmp", "xywrrmu#p")); // true
        System.out.println("-----------------------------------------------------");
        System.out.println(compareStringsUsingStack("xy#z", "xzz#")); // true
        System.out.println(compareStringsUsingStack("xy#z", "xyz#")); // false
        System.out.println(compareStringsUsingStack("xp#", "xyz##")); // true
        System.out.println(compareStringsUsingStack("xywrrmp", "xywrrmu#p")); // true
    }

}

/* Solution:

To compare the given strings, first, we need to apply the backspaces. An efficient way to do this would be from the end of both the strings.

We can have separate pointers, pointing to the last element of the given strings. We can start comparing the characters
pointed out by both the pointers to see if the strings are equal.

If, at any stage, the character pointed out by any of the pointers is a backspace (’#’), we will skip and
apply the backspace until we have a valid character available for comparison. */