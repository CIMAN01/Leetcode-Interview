import java.util.*;

// No-repeat Substring (hard)
class NoRepeatSubstring {

    // time: O(N) where ‘N’ is the number of characters in the input string | space: O(K) where K is the number of distinct characters in the input string
    public static int findLength(String str) {
        // declare and initialize variables
        int windowStart = 0, maxLength = 0;
        // use a HashMap to remember the last index of each character that has been processed
        Map<Character, Integer> charIndexMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // whenever we get a repeating character we will shrink our sliding window to ensure that we always have distinct characters in the sliding window //
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            char rightChar = str.charAt(windowEnd);
            // if the map already contains the 'rightChar', shrink the window from the beginning so that we have only one occurrence of 'rightChar'
            if(charIndexMap.containsKey(rightChar)) {
                // this is tricky; in the current window, we will not have any 'rightChar' after its previous index
                // and if 'windowStart' is already ahead of the last index of 'rightChar', we'll keep 'windowStart'
                windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
            }
            charIndexMap.put(rightChar, windowEnd); // insert the 'rightChar' into the map
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // remember the maximum length so far
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("aabccbb")); // 3
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abbbb")); // 2
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abccde")); // 3
    }

}